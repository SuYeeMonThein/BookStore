import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookFileManager {

    private static final String FILENAME = "books.txt";

    public static void writeBooksToFile(List<BookStore> books) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME))) {
            for (BookStore book : books) {
                writer.write(
                        book.getId() + "," + book.getBookName() + "," + book.getYear() + "," + book.getPrice() + "," + book.getAuthor());
                writer.newLine();
            }
            System.out.println("Books have been written to file.");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public static List<BookStore> readBooksFromFile() {
        List<BookStore> books = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                String bookname = data[1];
                int year = Integer.parseInt(data[2]);
                int price = Integer.parseInt(data[3]);
                String author = data[4];
                books.add(new BookStore(id, bookname, year, price, author));
            }
            System.out.println("Books have been read from file.");
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return books;
    }
}
