package Models;

import java.util.HashSet;

public class VIPReader extends Customer{

    private final static int booksLimit = 100;

    public VIPReader(int id, String lastName, String firstName, String cnp) {
        super(id, lastName, firstName, cnp);
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

    @Override
    public int getId() {
        return super.getId();
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
