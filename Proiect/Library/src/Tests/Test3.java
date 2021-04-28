package Tests;

import IO.SingletonAuthor;
import IO.SingletonBook;
import IO.SingletonPublisher;
import IO.SingletonSection;
import Models.*;
import Services.BookService;
import Services.CustomerService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Test3 {
    public static void main(String[] args) throws IOException {
        // Services
        CustomerService cs = new CustomerService();
        BookService bs = new BookService();

        // Publishers + Authors + Books + Sections
        SingletonPublisher.getInstance().ReadPublisher("src/ioFiles/PublishersIn.csv");
        SingletonAuthor.getInstance().ReadAuthor("src/ioFiles/AuthorsIn.csv");
        SingletonSection.getInstance().ReadSection("src/ioFiles/SectionsIn.csv");
        SingletonBook.getInstance().Read("src/ioFiles/BookIn.csv");

        // Customers
        NormalReader nr1 = new NormalReader("Donald", "501212121212121");
        NormalReader nr2 = new NormalReader("John", "1020202393939");
        Student s1 = new Student("Marry", "202020202020", "Princeton", 2);

        bs.setSections(SingletonSection.getInstance().getSections());
        bs.setBooks(SingletonBook.getInstance().getBooks());
        Author a1 = SingletonAuthor.getInstance().getAuthors().get(0);
        Publisher p1 = SingletonPublisher.getInstance().getPublishers().get(0);
        bs.addBook(new Book("AAaaaa", 12, a1, p1), SingletonSection.getInstance().getSections().get(0));
        cs.addCustomer(nr1);
        cs.addCustomer(nr2);
        cs.addCustomer(s1);


        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        while(n != 0){
            switch(n){
                case 1:
                    bs.displayAllBooks();
                    SingletonBook.getInstance().Write("src/ioFiles/BookOut.csv");
                    break;
                case 2 :
                    bs.displayBooksBySection(bs.getSections().get(0));
                    // bs.displayBooksBySection("nume gresit");
                    break;
                case 3:
                    bs.displayBooksByAuthor(SingletonAuthor.getInstance().getAuthors().get(0));
                    break;
                case 4:
                    System.out.println(bs.inLibrary(SingletonBook.getInstance().getBooks().get(0)));
                    break;
                case 5:
                    bs.deleteBook("b1");
                    // bs.deleteBook("b2");
                    bs.displayAllBooks();
                    break;
                case 6:
                    bs.updateNrOfCopies(30, bs.getBooks().get(0));
                    System.out.println(bs.getBooks().get(0).getNrOfCopies());
                    break;
                case 7:
                    cs.displayCheckedOutBooks(s1);
                    break;
                case 8:
                    cs.checkOutBook(s1, bs.getBooks().get(0), bs);
                    cs.checkOutBook(s1, bs.getBooks().get(2), bs);
                    // cs.checkOutBook("nr1", "b1", bs); -> Unavailable
                    // cs.displayCheckedOutBooks("s1");
                    break;
                case 9:
                    cs.checkInBook(s1, bs.getBooks().get(0), bs);
                    cs.displayCheckedOutBooks(s1);
                    break;
                case 10:
                    cs.sortCustomersByName();
                    break;
                case 11:
                    cs.displayHistory();
                default:
                    System.out.println("Select a number between 1-11");
            }
            n = scanner.nextInt();
        }
    }
}
