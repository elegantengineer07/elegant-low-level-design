package systems.solidprinciples;

/*
Dependency Inversion Principle (DIP)
Definition: High-level modules should not depend on low-level modules; both should depend on abstractions.
Explanation: Instead of a class depending on concrete implementations, it should depend on interfaces or abstractions, making the code more flexible and easier to modify.
Example: A PaymentService should depend on an IPaymentProcessor interface, not directly on specific implementations like PayPalPaymentProcessor or StripePaymentProcessor.
 */


// Violates DIP
// codekarle
class GmailClient {
    void sendEmail(String text) {
        // send text
    }
}

class EmailService1 {
    private GmailClient gmailClient;

    // hard coded to gmail which is a low level
    public EmailService1() {
        this.gmailClient = new GmailClient();
    }

    public void sendEmail() {
        gmailClient.sendEmail("orderplaced");
    }
}


// Follows DIP
interface EmailClient2 {
    void sendEmail2(String text);
}

class GmailClient2 implements EmailClient2 {
    @Override
    public void sendEmail2(String text) {
        //
    }
}

class OutlookClient2 implements EmailClient2 {
    @Override
    public void sendEmail2(String text) {
        //
    }
}

class EmailService2 {
    private EmailClient2 emailClient2;
    public EmailService2(EmailClient2 emailClient2) {
        this.emailClient2 = emailClient2;
    }

    public void sendEmail2(String text) {
        emailClient2.sendEmail2(text);
    }
}



public class DIP {
    public static void main(String args[]) {
        EmailService1 emailService1 = new EmailService1(); // completely dependant on GMailClient
        emailService1.sendEmail();


        EmailService2 emailService2 = new EmailService2(new GmailClient2()); // gmail
        EmailService2 emailService3 = new EmailService2(new OutlookClient2()); // outlook
    }
}


