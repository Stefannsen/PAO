package Models;
import java.util.Scanner;

public abstract class Customer {
    protected String id;
    protected String name;
    protected String cnp;
    Book[] borrowedBooks;
    public int nrOfBooks = 0;

    public Customer(String name, String cnp) {
        this.name = name;
        this.cnp = cnp;
       // borrowedBooks = new Book[5];
    }

    public Customer() {
        Scanner scanner = new Scanner(System.in);
        this.name = scanner.nextLine();
        this.cnp = scanner.nextLine();
        borrowedBooks = new Book[5];
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnp() {
        return cnp;
    }


    public Book[] getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(Book[] borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public int getNrOfBooks() {
        return nrOfBooks;
    }

    public void setNrOfBooks(int nrOfBooks) {
        this.nrOfBooks = nrOfBooks;
    }

    public abstract int getBooksLimit();

    public abstract int calculatePassPrice();
    public abstract String getCls();

    public  void showCustomer(){
        System.out.println("************" + "\nName: " + name + "\nCNP: " + cnp);
    }
}
