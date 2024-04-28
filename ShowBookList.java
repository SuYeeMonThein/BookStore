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
    private boolean bookAdded = false; // This flag will check if any book has been added

    public ShowBookList() {
        books = new ArrayList<>();
        scanner = new Scanner(System.in);
        books = BookFileManager.readBooksFromFile(); // Load existing books
    }

    @Override
    public void addNewBook() {
        int id = getValidatedInt("Enter book ID (ID must be at least 1 digits to 9 digits): ", 1, Integer.MAX_VALUE);
        scanner.nextLine(); // Consume any leftover newline

        System.out.print("Book Name: ");
        String bookName = scanner.nextLine();
        if (bookName.matches("[a-zA-Z\\s]+")) {
            int year = getValidatedInt("Enter year of publication (between 1500 and 2024): ", 1500, 2024);
            int price = getValidatedInt("Enter price: ", 1, Integer.MAX_VALUE);
            scanner.nextLine(); // Consume any leftover newline
            String author = getValidatedString("Enter author name: ");

            books.add(new BookStore(id, bookName, year, price, author));
            bookAdded = true; // Set the flag to true since a new book has been added
            System.out.println("Book added successfully!");
        } else {
            System.out.println("Invalid input. Please enter only alphabetic characters for the name.");
        }
    }

    @Override
    public void saveBooksToFile() {
        if (!bookAdded) { // Check if any new book has been added before saving
            System.out.println("No new book added to save. Please add a book first.");
        } else {
            BookFileManager.writeBooksToFile(books); // Save the books to the file
            System.out.println("Books saved successfully!");
        }
    }

    // Remaining methods (getValidatedInt, getValidatedString, deleteBook, displayBookList, etc.) remain unchanged.


    private int getValidatedInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                String valInput = String.valueOf(input);
                
                valInput = valInput.replaceFirst("^0+(?!$)", "");

                int inputfinal = Integer.parseInt(valInput);
                System.out.println("--->" + inputfinal);

                if (inputfinal >= min && inputfinal <= max ) {
                    return inputfinal;
                } else {
                    System.out.println("Input out of bounds. Please try again.");
                }
            } else {
                scanner.next(); // consume the invalid input
                System.out.println("Invalid input. Please Try again!.");
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

    
    public void showMenu() {
        System.out.println("Select an option:");
        System.out.println("1. Add new book");
        System.out.println("2. Save books to file");
        System.out.println("3. Show all books");
        System.out.println("4. Delete a book");
        System.out.println("0. Exit");
        System.out.println();
    }

    public void processSelection(int choice) {
        switch (choice) {
            case 1:
                addNewBook();
                break;
            case 2:
            saveBooksToFile();
                break;
            case 3:
                
                displayBookList();
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
            System.out.println();
            System.out.println();
        }
    }
}