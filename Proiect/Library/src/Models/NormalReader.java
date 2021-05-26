package Models;

import java.util.HashSet;

public class NormalReader extends Customer{
    private final static int booksLimit = 5;

    public NormalReader(int id, String lastName, String firstName, String cnp) {
        super(id, lastName, firstName, cnp);
    }

    public  int getBooksLimit() {
        return booksLimit;
    }

    @Override
    public int calculatePassPrice() {
        return 25;
    }

    @Override
    public String getCls() {
        return "NormalReader";
    }

    @Override
    public void showCustomer() {
        super.showCustomer();
        System.out.println("Type: " + getCls() +  "\nPass price: " + calculatePassPrice() + "\n************");

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
