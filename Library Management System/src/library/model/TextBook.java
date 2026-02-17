package library.model;

public class TextBook extends Book {
    private String subject;
    private String educationLevel;
    
    public TextBook(String bookId, String title, String author,
                   String isbn, double price, String subject,
                   String educationLevel) {
        super(bookId, title, author, isbn, price);
        this.subject = subject;
        this.educationLevel = educationLevel;
    }
    
    @Override
    public String getType() {
        return "TextBook";
    }
    
    @Override
    public double calculateLateFee(int daysLate) {
        // TextBooks: Rs. 50 per day
        return daysLate * 50.0;
    }
    
    public String getSubject() { return subject; }
    public String getEducationLevel() { return educationLevel; }
}
