package library.model;

public class ReferenceBook extends Book {
    private String category;
    
    public ReferenceBook(String bookId, String title, String author,
                        String isbn, double price, String category) {
        super(bookId, title, author, isbn, price);
        this.category = category;
    }
    
    @Override
    public String getType() {
        return "Reference";
    }
    
    @Override
    public double calculateLateFee(int daysLate) {
        // Reference: Rs. 75 per day (non-borrowable concept)
        return daysLate * 75.0;
    }
    
    public String getCategory() { return category; }
}
