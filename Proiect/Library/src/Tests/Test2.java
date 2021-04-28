package Tests;

import Models.*;
import Services.BookService;
import Services.BooksSorted;
import Services.CustomerService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Test2 {

    public static void main(String[] args) throws IOException {

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
        Book b3 = new Book("Mara", 20, a2, p2);
        Book b4 = new Book("Moara cu Noroc", 13, a3, p2);
        Book b5 = new Book("Mara", 20, a3, p2);

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


        cs.checkOutBook(s1, b3, bs);
        cs.checkOutBook(nr1, b3, bs);
        List<Book> bookList = new LinkedList<>();
        bookList.add(b1);
        bookList.add(b2);
        bookList.add(b5);
        bookList.add(b4);
        bookList.add(b3);
//        System.out.println(bookList.toString());
//        bookList.remove(b1);
//        System.out.println(bookList.toString());
//        System.out.println(bookList.contains(b1));
        // bookList.stream().filter(b -> b.getCrtNrOfCopies() >= 13).forEach(System.out::println);
//        bookList.stream().filter(book -> book.getCrtNrOfCopies() > 10).
//                sorted((book1, book2) -> book1.getBookName().compareTo(book2.getBookName())).
//                forEach(System.out::println);
//        bookList.stream().filter(book -> book.getCrtNrOfCopies() > 0).
//                sorted().
//                forEach(System.out::println);
        System.out.println(bookList.toString());
        bookList.stream().sorted(new BooksSorted()).forEach(System.out::println);

    }
}

