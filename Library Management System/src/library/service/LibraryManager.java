package library.service;

import library.model.*;
import library.observer.*;
import java.util.*;

public class LibraryManager {
    private static LibraryManager instance;
    private List<Book> books;
    private List<Member> members;
    private List<Borrowing> borrowings;
    private List<LibraryObserver> observers;
    
    private LibraryManager() {
        books = new ArrayList<>();
        members = new ArrayList<>();
        borrowings = new ArrayList<>();
        observers = new ArrayList<>();
    }
    
    public static LibraryManager getInstance() {
        if(instance == null) {
            instance = new LibraryManager();
        }
        return instance;
    }
    
    public void addBook(Book book) {
        books.add(book);
        notifyObservers("Book added: " + book.getTitle());
    }
    
    public void registerMember(Member member) {
        members.add(member);
        notifyObservers("Member registered: " + member.getName());
    }
    
    public Borrowing borrowBook(String borrowingId, String memberId,
                               String bookId, int days) {
        Member member = findMember(memberId);
        Book book = findBook(bookId);
        
        if(member != null && book != null && book.isAvailable()) {
            Borrowing borrowing = new Borrowing(borrowingId, 
                                               member, book, days);
            book.setAvailable(false);
            borrowings.add(borrowing);
            notifyObservers("Book borrowed: " + book.getTitle() + 
                          " by " + member.getName());
            return borrowing;
        }
        return null;
    }
    
    public void returnBook(String borrowingId) {
        for(Borrowing borrowing : borrowings) {
            if(borrowing.getBorrowingId().equals(borrowingId)) {
                borrowing.setReturned(true);
                borrowing.getBook().setAvailable(true);
                double fine = borrowing.calculateFine();
                String message = "Book returned: " + 
                               borrowing.getBook().getTitle();
                if(fine > 0) {
                    message += " | Fine: Rs. " + String.format("%.2f", fine);
                }
                notifyObservers(message);
                return;
            }
        }
    }
    
    private Member findMember(String memberId) {
        for(Member m : members) {
            if(m.getMemberId().equals(memberId)) return m;
        }
        return null;
    }
    
    private Book findBook(String bookId) {
        for(Book b : books) {
            if(b.getBookId().equals(bookId)) return b;
        }
        return null;
    }
    
    public void addObserver(LibraryObserver observer) {
        observers.add(observer);
    }
    
    private void notifyObservers(String message) {
        for(LibraryObserver observer : observers) {
            observer.update(message);
        }
    }
    
    public List<Book> getAvailableBooks() {
        List<Book> available = new ArrayList<>();
        for(Book b : books) {
            if(b.isAvailable()) available.add(b);
        }
        return available;
    }
    
    public void displayBooks() {
        System.out.println("\n=== ALL BOOKS ===");
        for(Book b : books) {
            System.out.println(b + " [" + 
                (b.isAvailable() ? "AVAILABLE" : "BORROWED") + "]");
        }
    }
    
    public void displayMembers() {
        System.out.println("\n=== ALL MEMBERS ===");
        for(Member m : members) {
            System.out.println(m);
        }
    }
    
    public void displayBorrowings() {
        System.out.println("\n=== ALL BORROWINGS ===");
        for(Borrowing b : borrowings) {
            if(!b.isReturned()) System.out.println(b);
        }
    }
    
    public void displayStatistics() {
        System.out.println("\n=== LIBRARY STATISTICS ===");
        System.out.println("Total Books: " + books.size());
        System.out.println("Total Members: " + members.size());
        System.out.println("Active Borrowings: " + 
            borrowings.stream().filter(b -> !b.isReturned()).count());
        double totalValue = books.stream()
            .mapToDouble(Book::getPrice).sum();
        System.out.println("Total Book Value: Rs. " + 
            String.format("%.2f", totalValue));
    }
}
