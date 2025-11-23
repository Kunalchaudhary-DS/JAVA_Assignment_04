import java.io.*;
import java.util.*;

class Book {
    int bookId;
    String title, author, category;
    boolean isIssued;

    Book(int bookId, String title, String author, String category) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.category = category;
        this.isIssued = false;
    }

    void display() {
        System.out.println("ID: " +bookId);
        System.out.println("Title: " +title);
        System.out.println("Author: " +author);
        System.out.println("Category: " +category);
        System.out.println("Issued: " +(isIssued ? "Yes" : "No"));
    }
}

class Member {
    int memberId;
    String name, email;
    List<Integer> issuedBooks = new ArrayList<>();

    Member(int memberId, String name, String email) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
    }

    void display() {
        System.out.println("Member ID: " + memberId);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Issued Books: " + issuedBooks);
    }
}

class LibraryManager {

    Map<Integer, Book> books = new HashMap<>();
    Map<Integer, Member> members = new HashMap<>();

    Scanner sc = new Scanner(System.in);

    File bookFile = new File("books.txt");
    File memberFile = new File("members.txt");

    public static void main(String[] args) {
        LibraryManager lib = new LibraryManager();
        lib.loadFiles();
        lib.menu();
        lib.saveFiles();
        System.out.println("Data saved. Exiting...");
    }

    void menu() {
        while (true) {
            System.out.println("\nCity Library Digital Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Add Member");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Search Books");
            System.out.println("6. Sort Books");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");

            String ch = sc.nextLine();

            switch (ch) {
                case "1": addBook(); 
                break;
                case "2": addMember(); 
                break;
                case "3": issueBook();
                break;
                case "4": returnBook(); 
                break;
                case "5": searchBooks(); 
                break;
                case "6": sortBooks(); 
                break;
                case "7": return;
                
                default: System.out.println("Invalid choice!");
            }
        }
    }

    void addBook() {
        try {
            System.out.print("Enter Book ID: ");
            int id = Integer.parseInt(sc.nextLine());

            System.out.print("Enter Title: ");
            String t = sc.nextLine();

            System.out.print("Enter Author: ");
            String a = sc.nextLine();

            System.out.print("Enter Category: ");
            String c = sc.nextLine();

            books.put(id, new Book(id, t, a, c));
            System.out.println("Book added!");
        } catch (Exception e) {
            System.out.println("Invalid input.");
        }
    }

    void addMember() {
        try {
            System.out.print("Enter Member ID: ");
            int id = Integer.parseInt(sc.nextLine());

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Email: ");
            String email = sc.nextLine();

            members.put(id, new Member(id, name, email));
            System.out.println("Member added!");
        } catch (Exception e) {
            System.out.println("Invalid input.");
        }
    }

    void issueBook() {
        try {
            System.out.print("Enter Book ID: ");
            int bId = Integer.parseInt(sc.nextLine());

            System.out.print("Enter Member ID: ");
            int mId = Integer.parseInt(sc.nextLine());

            Book b = books.get(bId);
            Member m = members.get(mId);

            if (b == null || m == null) {
                System.out.println("Invalid IDs.");
                return;
            }
            if (b.isIssued) {
                System.out.println("Already issued!");
                return;
            }

            b.isIssued = true;
            m.issuedBooks.add(bId);

            System.out.println("Book issued!");
        } catch (Exception e) {
            System.out.println("Error issuing book.");
        }
    }

    void returnBook() {
        try {
            System.out.print("Enter Book ID: ");
            int bId = Integer.parseInt(sc.nextLine());

            System.out.print("Enter Member ID: ");
            int mId = Integer.parseInt(sc.nextLine());

            Book b = books.get(bId);
            Member m = members.get(mId);

            if (b == null || m == null) {
                System.out.println("Invalid IDs.");
                return;
            }

            b.isIssued = false;
            m.issuedBooks.remove(Integer.valueOf(bId));

            System.out.println("Book returned!");
        } catch (Exception e) {
            System.out.println("Error returning book.");
        }
    }

    void searchBooks() {
        System.out.print("Search keyword: ");
        String key = sc.nextLine().toLowerCase();

        for (Book b : books.values()) {
            if (b.title.toLowerCase().contains(key) ||
                    b.author.toLowerCase().contains(key) ||
                    b.category.toLowerCase().contains(key)) {

                System.out.println();
                b.display();
            }
        }
    }

    void sortBooks() {
        List<Book> list = new ArrayList<>(books.values());

        System.out.println("1. By Title");
        System.out.println("2. By Author");
        String ch = sc.nextLine();

        if (ch.equals("1")) {
            list.sort(Comparator.comparing(b -> b.title.toLowerCase()));
        } else if (ch.equals("2")) {
            list.sort(Comparator.comparing(b -> b.author.toLowerCase()));
        }

        for (Book b : list) {
            System.out.println();
            b.display();
        }
    }

    void loadFiles() {
        try {
            if (!bookFile.exists()) bookFile.createNewFile();
            if (!memberFile.exists()) memberFile.createNewFile();

            BufferedReader br1 = new BufferedReader(new FileReader(bookFile));
            String line;

            while ((line = br1.readLine()) != null) {
                String[] p = line.split(",");
                int id = Integer.parseInt(p[0]);
                books.put(id, new Book(id, p[1], p[2], p[3]));
                books.get(id).isIssued = Boolean.parseBoolean(p[4]);
            }
            br1.close();

            BufferedReader br2 = new BufferedReader(new FileReader(memberFile));

            while ((line = br2.readLine()) != null) {
                String[] p = line.split(",");
                Member m = new Member(Integer.parseInt(p[0]), p[1], p[2]);

                if (p.length > 3 && !p[3].isEmpty()) {
                    for (String s : p[3].split(";"))
                        m.issuedBooks.add(Integer.parseInt(s));
                }
                members.put(m.memberId, m);
            }
            br2.close();

        } catch (Exception ignored) {}
    }

    void saveFiles() {
        try {
            BufferedWriter bw1 = new BufferedWriter(new FileWriter(bookFile));
            for (Book b : books.values()) {
                bw1.write(b.bookId + "," + b.title + "," + b.author + "," + b.category + "," + b.isIssued);
                bw1.newLine();
            }
            bw1.close();

            BufferedWriter bw2 = new BufferedWriter(new FileWriter(memberFile));
            for (Member m : members.values()) {
                StringBuilder sb = new StringBuilder();
                for (int id : m.issuedBooks)
                    sb.append(id).append(";");

                bw2.write(m.memberId + "," + m.name + "," + m.email + "," + sb);
                bw2.newLine();
            }
            bw2.close();
        } catch (Exception ignored) {}
    }
}
