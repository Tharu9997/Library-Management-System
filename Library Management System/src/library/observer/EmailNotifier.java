package library.observer;

public class EmailNotifier implements LibraryObserver {
    private String email;
    
    public EmailNotifier(String email) {
        this.email = email;
    }
    
    @Override
    public void update(String message) {
        System.out.println("[EMAIL SENT] To: " + email);
        System.out.println("Message: " + message);
    }
}
