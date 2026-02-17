# Library Management System

## Project Overview

The Library Management System is an interactive Java application demonstrating three critical design patterns: **Factory Pattern**, **Singleton Pattern**, and **Observer Pattern**. The system manages library operations in Sri Lanka including book inventory management, member registrations, borrowing transactions, and automated notifications with dynamic late fee calculations.

## Features

- **Add Book:** Register new books (TextBook, Fiction, ReferenceBook) with unique attributes using the Factory Pattern
- **Register Member:** Capture member information including ID, name, email, phone, and address
- **Borrow Book:** Automatically calculate borrowing periods and manage book availability
- **Return Book:** Process book returns with automatic late fee calculation based on book type and days overdue
- **View Data:** Display all books, members, and borrowing records from the centralized Singleton
- **System Statistics:** View total books, members, active borrowings, and library asset value
- **Automatic Notifications:** Email and SMS notifications sent automatically via Observer Pattern for book operations

## Design Patterns Implemented

### 1. Factory Pattern (Creational)
The `BookFactory` class encapsulates all book creation logic. Different book types (TextBook, FictionBook, ReferenceBook) are created through a single static method based on type parameters. This allows new book types to be added without modifying existing code.

### 2. Singleton Pattern (Creational)
The `LibraryManager` class ensures only one instance manages all library data—books, members, borrowings, and observers. The private constructor prevents instantiation, and getInstance() provides global access to the single instance, ensuring data consistency across all operations.

### 3. Observer Pattern (Behavioral)
The `LibraryObserver` interface defines a contract for notification handlers. `EmailNotifier` and `SMSNotifier` implementations automatically receive and process notifications when books are borrowed or returned, demonstrating loose coupling and flexibility.

## Project Structure

```
Library Management System/
├── library.model/
│   ├── Book.java (abstract)
│   ├── TextBook.java
│   ├── FictionBook.java
│   ├── ReferenceBook.java
│   ├── Member.java
│   └── Borrowing.java
├── library.factory/
│   └── BookFactory.java
├── library.service/
│   └── LibraryManager.java
├── library.observer/
│   ├── LibraryObserver.java (interface)
│   ├── EmailNotifier.java
│   └── SMSNotifier.java
└── LibraryManagementSystem.java (main)
```

## Book Types & Late Fees

- **TextBook:** Rs. 50 per day overdue
- **FictionBook:** Rs. 30 per day overdue
- **ReferenceBook:** Rs. 75 per day overdue

## How to Run

1. **Compile:** `javac *.java`
2. **Execute:** `java LibraryManagementSystem`
3. **Select Option:** Choose from 1-9 menu options
4. **Follow Prompts:** Enter book/member information dynamically
5. **View Results:** Check console for confirmations and notifications

## Menu Options

1. Add Book - Register new book to library
2. Register Member - Enroll new library member
3. Borrow Book - Process book borrowing transaction
4. Return Book - Process book return and calculate fine
5. View All Books - Display complete book inventory
6. View All Members - Display all registered members
7. View All Borrowings - Display active borrowing transactions
8. System Statistics - View library summary statistics
9. Exit - Quit the system

## Requirements

- Java 8 or higher
- No external dependencies required

## Key Classes

- **Book (abstract):** Base class defining book contract
- **BookFactory:** Creates book objects based on type
- **LibraryManager (Singleton):** Central data management
- **LibraryObserver (interface):** Notification contract
- **EmailNotifier/SMSNotifier:** Concrete notification implementations
- **Borrowing:** Tracks lending transactions with fee calculation

## Assessment

Developed for ICT431-2: Object Oriented Design Patterns and Principles at Uva Wellassa University. Demonstrates practical implementation of Creational and Behavioral design patterns in a real-world library management scenario.
