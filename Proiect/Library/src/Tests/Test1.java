package Tests;

import Models.*;
import Services.BookService;
import Services.CustomerService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Test1 {
    public static void menu(){
        System.out.println("******* MENU *******");
        System.out.println("\n1. Display all the books");
        System.out.println("\n2. Display all the books from a section");
        System.out.println("\n3. Display all the books written by a specific author");
        System.out.println("\n");
        // ........................
    }



    public static void main(String[] args) throws IOException {
        // Services
        CustomerService cs = new CustomerService();
        BookService bs = new BookService();

        // Publishers + Authors + Books + Sections
        Publisher p1 = new Publisher("Publisher1", "USA");
        Publisher p2 = new Publisher("Publisher2","Romania");

        Author a1 = new Author("Ernest Hemingway", "USA");
        Author a2 = new Author("Ion Creanga", "Romania");
        Author a3 = new Author("Ioan Slavici", "Romania");

        Book b1 = new Book("The Sun Also Rises", 1, a1, p1);
        Book b2 = new Book("A Moveable feast", 10, a1, p1);
        Book b3 = new Book("Amintiri din Copilarie", 20, a2, p2);
        Book b4 = new Book("Moara cu Noroc", 13, a3, p2);
        Book b5 = new Book("Mara", 5, a3, p2);

        Section section1 = new Section("Adventure");
        Section section2 = new Section("Drama");
        // Customers
        NormalReader nr1 = new NormalReader("Donald", "501212121212121");
        NormalReader nr2 = new NormalReader("John", "1020202393939");
        Student s1 = new Student("Marry", "202020202020", "Princeton", 2);


        bs.addSection(section1);
        bs.addSection(section2);

        bs.addBook(b1, section2);
        bs.addBook(b2, section2);
        bs.addBook(b3, section1);
        bs.addBook(b4, section2);
        bs.addBook(b5, section2);

        cs.addCustomer(nr1);
        cs.addCustomer(nr2);
        cs.addCustomer(s1);

        ArrayList<Customer> arr = cs.customers;
        for(Customer b : arr)
            b.showCustomer();
        System.out.println(cs.customers.contains(s1));
        System.out.println(cs.customers.contains(nr1));
        System.out.println(cs.customers.contains(nr2));

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        while(n != 0){
            switch(n){
                case 1:
                    bs.displayAllBooks();
                    break;
                case 2 :
                    bs.displayBooksBySection(section1);
                    // bs.displayBooksBySection("nume gresit");
                    break;
                case 3:
                    bs.displayBooksByAuthor(a1);
                    break;
                case 4:
                    System.out.println(bs.inLibrary(b1));
                    break;
                case 5:
                    bs.deleteBook("b1");
                    // bs.deleteBook("b2");
                    bs.displayAllBooks();
                    break;
                case 6:
                    bs.updateNrOfCopies(30, b1);
                    System.out.println(b1.getNrOfCopies());
                    break;
                case 7:
                    cs.displayCheckedOutBooks(s1);
                    break;
                case 8:
                    cs.checkOutBook(s1, b1, bs);
                    cs.checkOutBook(s1, b3, bs);
                    // cs.checkOutBook("nr1", "b1", bs); -> Unavailable
                    // cs.displayCheckedOutBooks("s1");
                    break;
                case 9:
                    cs.checkInBook(s1, b1, bs);
                    cs.displayCheckedOutBooks(s1);
                    break;
                case 10:
                    cs.sortCustomersByName();
                    break;
                case 11:
                    cs.displayHistory();
                    break;
                case 12:
                    return;
                default:
                    System.out.println("Select a number between 1-11");
            }
            n = scanner.nextInt();
        }



/*      Calendar cal = new GregorianCalendar(2021, Calendar.FEBRUARY, 15);
        Date d = new Date();
        Date d1 = cal.getTime();
        System.out.println((d.getTime() - d1.getTime()) / (1000*60*60*24));*/
    }
}
