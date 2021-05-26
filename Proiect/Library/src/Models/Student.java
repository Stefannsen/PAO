package Models;
import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;

public class Student extends Customer{
    private String universityName;
    private int yearOfStudy;
    private final static int booksLimit = 5;

    public Student(int id, String lastName, String firstName, String cnp, String universityName, int yearOfStudy) {
        super(id, lastName, firstName, cnp);
        this.universityName = universityName;
        this.yearOfStudy = yearOfStudy;
    }

    public int getBooksLimit() {
        return booksLimit;
    }

    @Override
    public int calculatePassPrice() {
        return 0;
    }

    @Override
    public String getCls() {
        return "Student";
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public void showCustomer() {
        super.showCustomer();
        System.out.println("Type: " + getCls() + "\nUniversity: " + universityName + "\nYear: " + yearOfStudy +
                "\nPass price" +  calculatePassPrice() + "\n************");

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
