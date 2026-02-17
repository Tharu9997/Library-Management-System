package library.model;

public class FictionBook extends Book {
    private String genre;
    
    public FictionBook(String bookId, String title, String author,
                      String isbn, double price, String genre) {
        super(bookId, title, author, isbn, price);
        this.genre = genre;
    }
    
    @Override
    public String getType() {
        return "Fiction";
    }
    
    @Override
    public double calculateLateFee(int daysLate) {
        // Fiction: Rs. 30 per day
        return daysLate * 30.0;
    }
    
    public String getGenre() { return genre; }
}
