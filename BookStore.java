public class BookStore {

    private String bookname;
    private int year;
    private int price;
    private String author;
    private int id;

    public BookStore(int id,String bookname, int year, int price, String author) {
        this.id=id;
        this.bookname = bookname;
        this.year = year;
        this.price = price;
        this.author = author;
    }
    public int getId() {
        return id;
    }

    public String getBookName() {
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
    public void setId(int id) {
        this.id = id;
    }
    public void setBookName(String bookname) {
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