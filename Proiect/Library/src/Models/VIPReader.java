package Models;

import java.util.HashSet;

public class VIPReader extends Customer{

    private final static int booksLimit = 100;

    public VIPReader(String name, String cnp) {
        super(name, cnp);
        this.borrowedBooks = new HashSet<Book>();
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

    public VIPReader() {
        super();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
