

import java.util.Scanner;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class ShowBookList {
    private List<BookStore> books;
    private static final Scanner scanner = new Scanner(System.in);

    public ShowBookList() throws IOException {
        books = BookFileManager.readBooksFromFile();
    }

    public void addBook(int id, String bookname, int year, int price, String author) {
        books.add(new BookStore(id, bookname, year, price, author));
        System.out.println("Book added successfully!");
    }

    public void deleteBook() {
        System.out.print("Enter book ID to delete: ");
        int id = getIntInput();
        BookStore toRemove = null;
        for (BookStore book : books) {
            if (book.getid() == id) {
                toRemove = book;
                break;
            }
        }
        if (toRemove != null) {
            books.remove(toRemove);
            System.out.println("Book removed successfully!");
        } else {
            System.out.println("No book found with ID: " + id);
        }
    }

    public void displayBookList() {
        System.out.println("Book List:");
        for (BookStore book : books) {
            System.out.println(book.getid() + ": " + book.getBookname() + " (" + book.getYear() + ") - $" + book.getPrice() + " by " + book.getAuthor());
        }
    }

    public void showMenu() {
        System.out.println("Select an option:");
        System.out.println("1. Add new book");
        System.out.println("2. Show all books");
        System.out.println("3. Save books to file");
        System.out.println("4. Delete a book");
        System.out.println("0. Exit");
    }

    public void processSelection(int choice) throws IOException {
        switch (choice) {
            case 1:
                addNewBook();
                break;
            case 2:
                displayBookList();
                break;
            case 3:
                saveBooksToFile();
                break;
            case 4:
                deleteBook();
                break;
            case 0:
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("Invalid option!");
        }
    }

    public void addNewBook() {
        System.out.println("Enter book details:");
        System.out.print("ID: ");
        int id = getIntInput();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Year: ");
        int year = getIntInput();
        System.out.print("Price: ");
        int price = getIntInput();
        System.out.print("Author: ");
        String author = scanner.nextLine();

        addBook(id, name, year, price, author);
    }

    public static int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input! Please enter an integer.");
            scanner.next();
        }
        int input = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        return input;
    }

    public void saveBooksToFile() throws IOException {
        BookFileManager.writeBooksToFile(books);
        System.out.println("Books saved to file successfully!");
    }

    public static void main(String[] args) {
        try {
            ShowBookList bookList = new ShowBookList();
            int choice;

            do {
                bookList.showMenu();
                System.out.print("Enter your choice: ");
                choice = getIntInput();
                bookList.processSelection(choice);
            } while (choice != 0);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}