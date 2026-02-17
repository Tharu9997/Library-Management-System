package library.model;

public abstract class Book {
    private String bookId;
    private String title;
    private String author;
    private String isbn;
    private double price;
    private boolean available;
    
    public Book(String bookId, String title, String author, 
                String isbn, double price) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.price = price;
        this.available = true;
    }
    
    // Abstract methods
    public abstract String getType();
    public abstract double calculateLateFee(int daysLate);
    
    // Getters and Setters
    public String getBookId() { return bookId; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getISBN() { return isbn; }
    public double getPrice() { return price; }
    public boolean isAvailable() { return available; }
    
    public void setAvailable(boolean available) { 
        this.available = available; 
    }
    
    @Override
    public String toString() {
        return String.format("[%s] %s by %s - Rs. %.2f", 
            bookId, title, author, price);
    }
}
