package library.model;

import java.time.LocalDate;

public class Borrowing {
    private String borrowingId;
    private Member member;
    private Book book;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private boolean returned;
    private LocalDate returnDate;
    
    public Borrowing(String borrowingId, Member member, Book book,
                    int borrowDays) {
        this.borrowingId = borrowingId;
        this.member = member;
        this.book = book;
        this.borrowDate = LocalDate.now();
        this.dueDate = borrowDate.plusDays(borrowDays);
        this.returned = false;
    }
    
    public String getBorrowingId() { return borrowingId; }
    public Member getMember() { return member; }
    public Book getBook() { return book; }
    public LocalDate getBorrowDate() { return borrowDate; }
    public LocalDate getDueDate() { return dueDate; }
    public boolean isReturned() { return returned; }
    public LocalDate getReturnDate() { return returnDate; }
    
    public void setReturned(boolean returned) { 
        this.returned = returned;
        if(returned) {
            this.returnDate = LocalDate.now();
        }
    }
    
    public double calculateFine() {
        if(!returned) return 0;
        long daysLate = java.time.temporal.ChronoUnit.DAYS
            .between(dueDate, returnDate);
        if(daysLate > 0) {
            return book.calculateLateFee((int)daysLate);
        }
        return 0;
    }
    
    @Override
    public String toString() {
        return String.format("[%s] %s borrowed '%s' on %s, Due: %s", 
            borrowingId, member.getName(), book.getTitle(), 
            borrowDate, dueDate);
    }
}
