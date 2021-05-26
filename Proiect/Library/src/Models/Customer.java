package Models;
import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;

public class Customer {
    protected int id;
    protected String lastName;
    protected String firstName;
    protected String cnp;

    public Customer(int id, String lastName, String firstName, String cnp) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.cnp = cnp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public int calculatePassPrice() {return 0;};
    public String getCls() {return "";};

    public void showCustomer(){
        System.out.println("************" + "\nName: " + lastName + " " +
                firstName + " " + "\nCNP: " + cnp);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
