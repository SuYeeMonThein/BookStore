import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShowBookList {

    private List<BookStore> books = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public ShowBookList() {
        books = BookFileManager.readBooksFromFile();
    }

    public void addBook(String bookname, int year, int price, String author) {
        books.add(new BookStore(bookname, year, price, author));
    }

    public void displayBookList() {
        System.out.println("Book List  : ");
        for (BookStore book : books) {
            System.out.println(
                    book.getBookname() + " (" + book.getYear() + ") - $" + book.getPrice() + " by " + book.getAuthor());
        }
    }

    public void showMenu() {
        System.out.println("Select an option:");
        System.out.println("1. Add new book");
        System.out.println("2. Show all books");
        System.out.println("3. Save books to file");
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
            case 0:
                System.out.println("Exiting...");
                System.exit(0);
            default:
                System.out.println("Invalid option!");
        }
    }

    public void addNewBook() {
        System.out.println("Enter book details:");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Year: ");
        int year = scanner.nextInt();
        System.out.print("Price: ");
        int price = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        System.out.print("Author: ");
        String author = scanner.nextLine();

        addBook(name, year, price, author);
        System.out.println("Book added successfully!");
    }

    public void saveBooksToFile() {
        BookFileManager.writeBooksToFile(books);
    }

    public static void main(String[] args) {
        ShowBookList bookList = new ShowBookList();
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            bookList.showMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            bookList.processSelection(choice);
        }
    }
}
