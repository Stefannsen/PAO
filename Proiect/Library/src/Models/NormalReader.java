package Models;

import java.util.HashSet;

public class NormalReader extends Customer{
    private static int count = 0;
    private final static int booksLimit = 5;

    public NormalReader(String name, String cnp){
        super(name, cnp);
        this.borrowedBooks = new HashSet<Book>();

    }

    public NormalReader(){
        super();
    }

    {
        count++;
        this.id = "nr" + count;
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
    public String getId() {
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
