import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

interface BookManager {
    void addNewBook();
    void deleteBook();
    void displayBookList();
    void saveBooksToFile();
}

class BookStore {
    private int id;
    private String bookName;
    private int year;
    private int price;
    private String author;

    public BookStore(int id, String bookName, int year, int price, String author) {
        this.id = id;
        this.bookName = bookName;
        this.year = year;
        this.price = price;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public String getBookName() {
        return bookName;
    }

    public int getYear() {
        return year;
    }

    public int getPrice() {
        return price;
    }

    public String getAuthor() {
        return author;
    }
}

public class ShowBookList implements BookManager {
    private List<BookStore> books;
    private Scanner scanner;

    public ShowBookList() {
        books = new ArrayList<>();
        scanner = new Scanner(System.in);
        books = BookFileManager.readBooksFromFile(); // This method should handle file reading
    }

    @Override
    public void addNewBook() {
        int id = getValidatedInt("Enter book ID (positive integer): ", 1, Integer.MAX_VALUE);
        scanner.nextLine(); // Consume any leftover newline
        String bookName = getValidatedString("Enter book name: ");
        int year = getValidatedInt("Enter year of publication (between 1500 and 2025): ", 1500, 2025);
        int price = getValidatedInt("Enter price (positive integer): ", 1, Integer.MAX_VALUE);
        scanner.nextLine(); // Consume any leftover newline
        String author = getValidatedString("Enter author name: ");

        books.add(new BookStore(id, bookName, year, price, author));
        System.out.println("Book added successfully!");
    }

    private int getValidatedInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                if (input >= min && input <= max) {
                    return input;
                } else {
                    System.out.println("Input out of bounds. Please try again.");
                }
            } else {
                scanner.next(); // consume the invalid input
                System.out.println("Invalid input. Please enter an integer.");
            }
        }
    }

    private String getValidatedString(String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine();
            if (!input.trim().isEmpty()) {
                return input;
            } else {
                System.out.println("Invalid input. Please do not leave this blank.");
            }
        }
    }

    @Override
    public void deleteBook() {
        int id = getValidatedInt("Enter book ID to delete: ", 1, Integer.MAX_VALUE);
        scanner.nextLine(); // Consume newline character
        boolean removed = books.removeIf(book -> book.getId() == id);
        if (removed) {
            System.out.println("Book removed successfully.");
        } else {
            System.out.println("No book found with ID: " + id);
        }
    }

    @Override
    public void displayBookList() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            System.out.println("Book List:");
            books.forEach(book -> System.out.println(book.getId() + ": " + book.getBookName() + " (" + book.getYear() + ") - $" + book.getPrice() + " by " + book.getAuthor()));
        }
    }

    @Override
    public void saveBooksToFile() {
        BookFileManager.writeBooksToFile(books); // This method should handle file writing
        System.out.println("Books saved successfully!");
    }

    public void showMenu() {
        System.out.println("Select an option:");
        System.out.println("1. Add new book");
        System.out.println("2. Show all books");
        System.out.println("3. Save books to file");
        System.out.println("4. Delete a book");
        System.out.println("0. Exit");
    }

    public void processSelection(int choice) {
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
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option! Please select a valid number from the menu.");
        }
    }

    public static void main(String[] args) {
        ShowBookList bookList = new ShowBookList();
        int choice;

        while (true) {
            bookList.showMenu();
            System.out.print("Enter your choice: ");
            choice = bookList.scanner.nextInt();
            bookList.scanner.nextLine(); // Consume newline character

            bookList.processSelection(choice);
        }
    }
}