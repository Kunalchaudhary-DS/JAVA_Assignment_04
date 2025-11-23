# City Library Digital Management System

A Java-based console application built to digitize the operations of a city library.  
The system manages books, members, and transactions using **File Handling** and the **Java Collections Framework**, ensuring efficient searching, sorting, and persistent storage.

---

## Features
- Add new books and members  
- Issue and return books  
- Automatic file storage for all records (`books.txt`, `members.txt`)  
- Search books by title, author, or category  
- Sort books using Comparable (title) and Comparator (author/category)  
- Track issued books for each member  
- Collections used throughout the system:
  - `Map` for books & members  
  - `List` for issued books  
  - `Set` for unique categories  
  - `Queue` (optional) for waiting lists  

---

## Project Objectives
- Use Java File Handling for persistent storage  
- Use Collections Framework (List, Set, Map, Queue)  
- Implement sorting with Comparable & Comparator  
- Work with BufferedReader, BufferedWriter, FileReader, FileWriter  
- Use Generics for type-safe collection handling  

---

## Learning Outcomes (COs)
- **CO4.1:** Apply Java File Handling for real-world data  
- **CO4.2:** Implement Collection Framework for dynamic record management  
- **CO4.3:** Demonstrate sorting/searching using Comparable/Comparator  
- **CO4.4:** Integrate I/O with Collections for a real application  

---

## Class Overview

### **Book Class**
**Attributes**
- `bookId`  
- `title`  
- `author`  
- `category`  
- `isIssued`  

**Methods**
- `displayBookDetails()`  
- `markAsIssued()`  
- `markAsReturned()`  

---

### **Member Class**
**Attributes**
- `memberId`  
- `name`  
- `email`  
- `issuedBooks` (List of book IDs)

**Methods**
- `displayMemberDetails()`  
- `addIssuedBook(int id)`  
- `returnIssuedBook(int id)`  

---

### **LibraryManager Class**
**Attributes**
- `Map<Integer, Book> books`  
- `Map<Integer, Member> members`  

**Key Operations**
- `addBook()`  
- `addMember()`  
- `issueBook()`  
- `returnBook()`  
- `searchBooks()`  
- `sortBooks()`  
- `loadFromFile()`  
- `saveToFile()`  

---

## File Handling Requirements
- `books.txt` → Stores all books  
- `members.txt` → Stores all members  
- `FileReader` / `FileWriter` for text storage  
- `BufferedReader` / `BufferedWriter` for faster I/O  
- `FileInputStream` / `FileOutputStream` (optional binary storage)  
- Files must auto-create if not found  

---

## Collections Used
| Collection | Purpose |
|-----------|----------|
| **List** | Stores issued book IDs for members |
| **Set** | Stores unique categories |
| **Map** | Stores books and members using ID keys |
| **Queue (optional)** | Waiting list for popular books |
| **Comparable** | Sort books by title |
| **Comparator** | Sort books by author/category |

---

## How to Run

1. Compile the program:
2. Run the application:

---

## Sample Interaction

Welcome to City Library Digital Management System

1.Add Book
2.Add Member
3.Issue Book
4.Return Book
5.Search Books
6.Sort Books
7.Exit

Enter your choice: 1
Enter Book Title: Java Programming Mastery
Enter Author: John Smith
Enter Category: Programming
Book added successfully with ID: 101

---

## Future Enhancements
- Add admin login system  
- Add due dates & fine calculation  
- Create a GUI version using JavaFX  
- Store data using JSON or databases  

---

## License
This project is open for learning and personal use.
