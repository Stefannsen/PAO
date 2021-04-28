package Models;
import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;

public class Student extends Customer{
    private String universityName;
    private int yearOfStudy;
    private static int count = 0;
    private final static int booksLimit = 5;

    public Student(String name, String cnp, String universityName, int yearOfStudy) {
        super(name, cnp);
        this.universityName = universityName;
        this.yearOfStudy = yearOfStudy;
        this.borrowedBooks = new HashSet<Book>();
    }

    public Student() {
        super();
        Scanner scanner = new Scanner(System.in);
        this.universityName = scanner.nextLine();
        this.yearOfStudy = scanner.nextInt();
    }

    {
        count++;
        this.id = "s" + count;
    }

    public  int getBooksLimit() {
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
