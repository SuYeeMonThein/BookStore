public class BookStore {

    private String bookname;
    private int year;
    private int price;
    private String author;

    public BookStore(String bookname, int year, int price, String author) {
        this.bookname = bookname;
        this.year = year;
        this.price = price;
        this.author = author;
    }

    public String getBookname() {
        return bookname;
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

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

}