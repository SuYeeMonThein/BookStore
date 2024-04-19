public class ShowBookList {

    BookStore[] books = new BookStore[20];

    public ShowBookList() {
        fillBookStoreArray();
    }

    private void fillBookStoreArray() {
        books[0] = new BookStore("The Kite Runner", 2003, 16, "Khaled Hosseini");
        books[1] = new BookStore("The Pillars of the Earth", 1989, 15, "Ken Follett");
        books[2] = new BookStore("Cutting for stone", 2009, 20, "Abrahan Verghese");
        books[3] = new BookStore("The Shadow of the Wind", 2001, 20, "Carlos Ruiz Zafon");
        books[4] = new BookStore("Memoirs of a geisha", 1977, 12, "Arthur Golden");
        books[5] = new BookStore("The Thron Birds", 1977, 22, "Gre David Roberts");
        books[6] = new BookStore("The Poisonwood Bible", 1997, 17, "Barbara Kingsolver");
        books[7] = new BookStore("Snow Flower and the Secret Fan", 2005, 16, "Lisa See");
        books[8] = new BookStore("The Good of Small things", 1997, 15, "Arudhati Roy");
        books[9] = new BookStore("The Shipping News", 1993, 18, "E.Annie Proulx");
        books[10] = new BookStore("The Time Traveler's Wife", 2003, 18, "Audrey Niffenegger");
        books[11] = new BookStore("Atonement ", 2001, 19, "Ian McEwan");
        books[12] = new BookStore("The Help", 2009, 19, "Kathryn Stockett");
        books[13] = new BookStore("THe Goldfinch", 2002, 21, "Donna Tartt");
        books[14] = new BookStore("The Lovely Bones", 2002, 15, "Alice Sebold");
        books[15] = new BookStore("The Nightingale", 2015, 20, "Kristin Hannah");
        books[16] = new BookStore("Life of Pi", 2001, 16, "Yann Martel");
        books[17] = new BookStore("The Book Thief", 2005, 17, "Sue Monk Kidd");
        books[18] = new BookStore("The Art of RAcing in the Rain", 2001, 15, "Garth Stein");
        books[19] = new BookStore("Water for Elephants", 2006, 16, "Sara Gruen");
    }



public void displayBookList() {
    System.out.println("Book List  : ");
    for (BookStore book : books) {
        System.out.println(
                book.getBookname() + " (" + book.getYear() + ") - $" + book.getPrice() + " by " + book.getAuthor());
    }
}

public static void main(String[] args) {
    ShowBookList bookList = new ShowBookList();
    bookList.displayBookList();
}
}