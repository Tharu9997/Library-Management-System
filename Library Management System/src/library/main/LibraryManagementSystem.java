package library.main;

import library.factory.*;
import library.model.*;
import library.observer.*;
import library.service.*;
import java.util.List;
import java.util.Scanner;

public class LibraryManagementSystem {
    private static LibraryManager manager;
    private static Scanner scanner;

    public static void main(String[] args) {
        manager = LibraryManager.getInstance();
        scanner = new Scanner(System.in);

        // Setup observers
        manager.addObserver(new EmailNotifier("library@system.com"));
        manager.addObserver(new SMSNotifier("0771234567"));

        System.out.println("\n" + repeatString("=", 50));
        System.out.println("   SRI LANKA LIBRARY MANAGEMENT SYSTEM");
        System.out.println(repeatString("=", 50));
        System.out.println("[SYSTEM] LibraryManager initialized");

        int choice;
        do {
            displayMenu();
            choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    addBookMenu();
                    break;
                case 2:
                    registerMemberMenu();
                    break;
                case 3:
                    borrowBookMenu();
                    break;
                case 4:
                    returnBookMenu();
                    break;
                case 5:
                    manager.displayBooks();
                    break;
                case 6:
                    manager.displayMembers();
                    break;
                case 7:
                    manager.displayBorrowings();
                    break;
                case 8:
                    manager.displayStatistics();
                    break;
                case 9:
                    System.out.println("\nThank you for using Library Management System!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 9);
    }

    private static void displayMenu() {
        System.out.println("\n" + repeatString("-", 50));
        System.out.println("1. Add Book");
        System.out.println("2. Register Member");
        System.out.println("3. Borrow Book");
        System.out.println("4. Return Book");
        System.out.println("5. View All Books");
        System.out.println("6. View All Members");
        System.out.println("7. View All Borrowings");
        System.out.println("8. System Statistics");
        System.out.println("9. Exit");
        System.out.println(repeatString("-", 50));
    }

    private static void addBookMenu() {
        System.out.println("\n=== ADD BOOK ===");
        System.out.println("Book Types:");
        System.out.println("1. TextBook");
        System.out.println("2. Fiction");
        System.out.println("3. Reference");

        String type = getStringInput("Select type (1-3): ");
        String id = getStringInput("Book ID: ");
        String title = getStringInput("Title: ");
        String author = getStringInput("Author: ");
        String isbn = getStringInput("ISBN: ");
        double price = getDoubleInput("Price (Rs.): ");

        Object extraParam = null;
        if (type.equals("1")) {
            String subject = getStringInput("Subject: ");
            String level = getStringInput("Education Level: ");
            extraParam = new String[] { subject, level };
        } else if (type.equals("2")) {
            extraParam = getStringInput("Genre: ");
        } else if (type.equals("3")) {
            extraParam = getStringInput("Category: ");
        }

        Book book = BookFactory.createBook(type, id, title, author,
                isbn, price, extraParam);
        if (book != null) {
            manager.addBook(book);
            System.out.println("✓ Book added successfully!");
        }
    }

    private static void registerMemberMenu() {
        System.out.println("\n=== REGISTER MEMBER ===");
        String id = getStringInput("Member ID: ");
        String name = getStringInput("Full Name: ");
        String email = getStringInput("Email: ");
        String phone = getStringInput("Phone: ");
        String address = getStringInput("Address: ");

        Member member = new Member(id, name, email, phone, address);
        manager.registerMember(member);
        System.out.println("✓ Member registered successfully!");
    }

    private static void borrowBookMenu() {
        System.out.println("\n=== BORROW BOOK ===");

        List<Book> available = manager.getAvailableBooks();
        if (available.isEmpty()) {
            System.out.println("No books available!");
            return;
        }

        System.out.println("Available Books:");
        for (Book b : available) {
            System.out.println(b);
        }

        String borrowId = getStringInput("Borrowing ID: ");
        String memberId = getStringInput("Member ID: ");
        String bookId = getStringInput("Book ID: ");
        int days = getIntInput("Borrow Duration (days): ");

        Borrowing borrowing = manager.borrowBook(borrowId, memberId,
                bookId, days);
        if (borrowing != null) {
            System.out.println("✓ Book borrowed successfully!");
            System.out.println("Due Date: " + borrowing.getDueDate());
        } else {
            System.out.println("✗ Borrowing failed!");
        }
    }

    private static void returnBookMenu() {
        System.out.println("\n=== RETURN BOOK ===");
        manager.displayBorrowings();

        String borrowId = getStringInput("Borrowing ID to return: ");
        manager.returnBook(borrowId);
        System.out.println("✓ Book returned successfully!");
    }

    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    private static double getDoubleInput(String prompt) {
        System.out.print(prompt);
        double value = scanner.nextDouble();
        scanner.nextLine();
        return value;
    }

    private static String repeatString(String str, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(str);
        }
        return sb.toString();
    }
}
