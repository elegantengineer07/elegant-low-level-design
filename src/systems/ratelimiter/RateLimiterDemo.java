package systems.ratelimiter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

enum RateLimiterType {
    FIXED,
    TOKENBUCKET
}

interface RateLimiter {
    boolean allowRequest(String userId);
}

class RateLimitConfig {

    // generic config
    private final int maxRequests;

    // fixed window config
    private final long timeWindowMillis;

    // token bucket config
    private final int bucketSize;
    private final double refillRate;

    public RateLimitConfig(int maxRequests, long timeWindowMillis, int bucketSize, double refillRate) {
        this.maxRequests = maxRequests;
        this.timeWindowMillis = timeWindowMillis;
        this.bucketSize = bucketSize;
        this.refillRate = refillRate;
    }

    public int getMaxRequests() {
        return maxRequests;
    }

    public long getTimeWindowMillis() {
        return timeWindowMillis;
    }

    public int getBucketSize() {
        return bucketSize;
    }

    public double getRefillRate() {
        return refillRate;
    }

}

class UserTokenBucket {
    private int tokens;
    private long lastRefillTime;

    public UserTokenBucket(int tokens, long lastRefillTime) {
        this.tokens = tokens;
        this.lastRefillTime = lastRefillTime;
    }

    public long getLastRefillTime() {
        return lastRefillTime;
    }

    public int getTokens() {
        return tokens;
    }

    public void setTokens(int tokens) {
        this.tokens = tokens;
    }

    public void setLastRefilTime(long lastRefillTime) {
        this.lastRefillTime = lastRefillTime;
    }
}
// Token Bucket RateLimiter
class TokenBucketRateLimiter implements RateLimiter {

    private final RateLimitConfig config;

    // For each userId, We need to maintain the currenttokens, etc
    Map<String, UserTokenBucket> userBuckets;

    public TokenBucketRateLimiter(RateLimitConfig config) {
        this.config = config;
        this.userBuckets = new ConcurrentHashMap<>();
    }

    @Override
    public synchronized boolean allowRequest(String userId) {
        long currentTime = System.currentTimeMillis();
        userBuckets.putIfAbsent(userId, new UserTokenBucket(config.getBucketSize(), System.currentTimeMillis()));

        UserTokenBucket userBucket = userBuckets.get(userId);

        double tokensToAdd = (((currentTime - userBucket.getLastRefillTime()))/1000.0)*config.getRefillRate();
        userBucket.setTokens((int)Math.min(config.getBucketSize(), tokensToAdd + userBucket.getTokens()));
        userBucket.setLastRefilTime(currentTime);

        if(userBucket.getTokens() >= 1) {
            userBucket.setTokens(userBucket.getTokens() - 1);
            return true;
        }

        return false;
    }

    
}

class FixedWindowRateLimiter implements RateLimiter {
    // start and the end of the window for each user ID 
    // how many requests have been answered per user;

    private  Map<String, AtomicInteger> userRequestCount;
    private  Map<String, Long> userWindowStart;
    private final RateLimitConfig config;

    public FixedWindowRateLimiter(RateLimitConfig config) {
        this.config = config;
        this.userRequestCount = new ConcurrentHashMap<>();
        this.userWindowStart = new ConcurrentHashMap<>();
    }

    @Override
    public boolean allowRequest(String userId) {
        Long currentTime = System.currentTimeMillis();
        Long startTime = userWindowStart.getOrDefault(userId, currentTime);

        System.out.printf("[FixedWindow] User: %s | CurrentTime: %d | WindowStart: %d | WindowSize(ms): %d%n",
                userId, currentTime, startTime, config.getTimeWindowMillis());

        if(currentTime - startTime < config.getTimeWindowMillis()) {
            int count = userRequestCount.getOrDefault(userId, new AtomicInteger(0)).incrementAndGet();
            System.out.printf("[FixedWindow] User: %s | RequestCount in Window: %d | MaxAllowed: %d%n",
                    userId, count, config.getMaxRequests());
            boolean allowed = count <= config.getMaxRequests();
            System.out.printf("[FixedWindow] User: %s | Request %s (within window)%n", userId, allowed ? "ALLOWED" : "DENIED");
            return allowed; // can be satisfied;
        } else {
            userWindowStart.put(userId, currentTime);
            userRequestCount.put(userId, new AtomicInteger(1));
            System.out.printf("[FixedWindow] User: %s | New window started at %d. RequestCount reset to 1. Request ALLOWED.%n",
                    userId, currentTime);
            return true;
        }
    }

}

class RateLimiterFactory {
    public static RateLimiter getRateLimiter(RateLimiterType rateLimiterType, RateLimitConfig config) {
        switch(rateLimiterType) {
            case TOKENBUCKET:
                return new TokenBucketRateLimiter(config);
            case FIXED:
                return new FixedWindowRateLimiter(config);
            default:
                throw new IllegalArgumentException("Not implemented");
        }
    }
}

// 1 -> 10s - 20s

public class RateLimiterDemo {
    
    /**
     * Test the Token Bucket Rate Limiter for a given userId.
     * This method explains each line, prints the output, and also outputs the state of the user's token bucket after each request.
     */
    private static void testTokenBucketRateLimiter(RateLimiter rateLimiter, String userId) {
        System.out.println("Testing Token Bucket Rate Limiter for user: " + userId); // Explain: Announce which user is being tested.
        System.out.println("Making 10 requests..."); // Explain: Announce the number of requests to be made.


        for (int i = 1; i <= 10; i++) {
            // Explain: Attempt to allow a request for the user.
            boolean allowed = rateLimiter.allowRequest(userId);

            // Output the result of the request.
            System.out.printf("Request %d: %s%n", i, allowed ? "ALLOWED" : "DENIED");

            // Explain: Add a small delay between requests to see the token refill effect.
            try {
                Thread.sleep(500); // 500ms delay between requests
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        // Explain: After all requests, print a summary.
        System.out.println("Test complete. See above for request results and token bucket state after each request.");
    }

        /**
     * Test the Fixed Window Rate Limiter for a given userId.
     * This method explains each line, prints the output, and also outputs the state of the user's window after each request.
     */
        private static void testFixedWindowRateLimiter(RateLimiter rateLimiter, String userId) {
            System.out.println("Testing Fixed Window Rate Limiter for user: " + userId); // Announce which user is being tested.
            System.out.println("Making 10 requests..."); // Announce the number of requests to be made.

            for (int i = 1; i <= 10; i++) {
                // Attempt to allow a request for the user.
                boolean allowed = rateLimiter.allowRequest(userId);

                // Output the result of the request.
                System.out.printf("Request %d: %s%n", i, allowed ? "ALLOWED" : "DENIED");

                // Add a small delay between requests to see the window effect.
                try {
                    Thread.sleep(500); // 500ms delay between requests
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }

            // After all requests, print a summary.
            System.out.println("Test complete. See above for request results and window state after each request.");
        }
    
    public static void main(String args[]) {
        System.out.println("Rate Limiter by Elegant Engineer");

        // Create RateLimit Config
        int maxRequests = 2;
        long timeWindowMillis = 10000; // 10 seconds;
        double refillRate = 0.0; // 1 token per second
        int bucketSize = 2;

        // create config;
        RateLimitConfig config = new RateLimitConfig(maxRequests, timeWindowMillis, bucketSize, refillRate);

        RateLimiter tokenBucket = RateLimiterFactory.getRateLimiter(RateLimiterType.TOKENBUCKET, config);
        RateLimiter fixedWindowBuxket = RateLimiterFactory.getRateLimiter(RateLimiterType.FIXED, config);

        // Test the token bucket rate limiter
        testTokenBucketRateLimiter(tokenBucket, "leo_messi");
        testFixedWindowRateLimiter(fixedWindowBuxket, "cr7");

    }
}
