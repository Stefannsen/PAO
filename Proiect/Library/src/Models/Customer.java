package Models;
import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;

public abstract class Customer {
    protected String id;
    protected String name;
    protected String cnp;
    HashSet<Book> borrowedBooks;

    public Customer(String name, String cnp) {
        this.name = name;
        this.cnp = cnp;
       // borrowedBooks = new Book[5];
    }

    public Customer() {
        Scanner scanner = new Scanner(System.in);
        this.name = scanner.nextLine();
        this.cnp = scanner.nextLine();
        borrowedBooks = new HashSet<Book>();
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


    public HashSet<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(HashSet<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public int getNrOfBooks() {
        return borrowedBooks.size();
    }

    public abstract int getBooksLimit();

    public abstract int calculatePassPrice();
    public abstract String getCls();

    public void showCustomer(){
        System.out.println("************" + "\nName: " + name + "\nCNP: " + cnp);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id.equals(customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
