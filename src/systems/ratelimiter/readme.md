## Design a Rate Limiter

### Intro 
- Allows a certain maximum number of reqs within a time window based on userID or IP
- Various algorithms can be chosen - Token Bucket, Leaky Bucket Fixed Window, Sliding Window


### Delivery Workflow 
- Clarify and Gather Requirements 
- Identify Entities, Prop & Methods
- Class Diagram or Implementation 
- Design Patterns, Multi threading as required


### Step 1: Clarify and Gather Requirements 
- Limit based on UserId - Limiting Key
- 100 requests/minute - Max Request/Time
- Single node (not distributed)
- Thread-safe
- Support for Multiple algorithms 

### Step 2: Identify Classes, etc 
- RateLimiter (Interface) -> allowRequest()
- TokenBucketRateLimiter (Class) 
- FixedWindowRateLimiter 
- RateLimitConfig
