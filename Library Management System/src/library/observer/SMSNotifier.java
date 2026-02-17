package library.observer;

public class SMSNotifier implements LibraryObserver {
    private String phoneNumber;
    
    public SMSNotifier(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    @Override
    public void update(String message) {
        System.out.println("[SMS SENT] To: " + phoneNumber);
        System.out.println("Message: " + message);
    }
}
