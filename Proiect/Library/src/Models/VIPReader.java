package Models;

public class VIPReader extends Customer{

    private final static int booksLimit = 100;

    public VIPReader(String name, String cnp) {
        super(name, cnp);
        this.borrowedBooks = new Book[booksLimit];
    }

    public  int getBooksLimit() {
        return booksLimit;
    }

    @Override
    public int calculatePassPrice() {
        return 50;
    }

    @Override
    public String getCls() {
        return "VIP Reader";
    }
}
