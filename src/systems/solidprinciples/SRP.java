package systems.solidprinciples;

/*
Single Responsibility Principle (SRP):
Definition: A class should have only one reason to change, meaning it should have only one responsibility.
Explanation: Each class should focus on a single functionality, making it easier to maintain and modify without affecting unrelated functionalities.
Example: A class handling both user authentication and logging should be split into two separate classes, one for authentication and another for logging.
Aim: Achieve loosely coupled code.
 */


// Without SRP
class UserService {
    public void registerUser(String username, String password) {
        // Logic to registerUser
    }

    public void sendEmail(String email, String message) {
        // Logic to sendEmail
    }

    public void logUserActivity(String username, String activity) {
        // Logic to log user activity
    }
}

// With SRP
class UserRegistrationService {
    public void registerUser(String username, String password) {
        // Logic to registerUser
    }
}

class EmailService {
    public void sendEmail(String email, String message) {
        // Logic to sendEmail
    }
}

public class SRP {
}
