package Models;

import Repositories.BookRepository;
import Repositories.CustomerRepository;
import Repositories.PublisherRepository;
import Repositories.SectionRepository;

import java.util.Date;
import java.util.Optional;

public class BorrowInfo {
    private Customer customer;
    private int customerId;
    private Book book;
    private int bookId;
    private Date borrowDate;
    private Date returnDate;

    public BorrowInfo(int customerId, int bookId, Date borrowDate, Date returnDate) {
        this.customerId = customerId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;

        CustomerRepository customerRepository = CustomerRepository.getInstance();
        Optional<Customer> customer = customerRepository.getById(customerId);
        if(customer.isPresent()){
            this.customer = customer.get();
        }

        BookRepository bookRepository = BookRepository.getInstance();
        Optional<Book> book = bookRepository.getById(bookId);
        if(book.isPresent()){
            this.book = book.get();
        }
    }

    public BorrowInfo(int customerId, int bookId) {
        this.customerId = customerId;
        this.bookId = bookId;
        this.borrowDate = new Date();

        CustomerRepository customerRepository = CustomerRepository.getInstance();
        Optional<Customer> customer = customerRepository.getById(customerId);
        if(customer.isPresent()){
            this.customer = customer.get();
        }

        BookRepository bookRepository = BookRepository.getInstance();
        Optional<Book> book = bookRepository.getById(bookId);
        if(book.isPresent()){
            this.book = book.get();
        }
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String status(){
        if(returnDate == null)
            return "Not returned yet";
        else
            return returnDate.toString();
    }

    @Override
    public String toString() {
        return "********** INFO **********" +
                "\nCustomer: " + customer.getLastName() + " " + customer.getFirstName() +
                "\nBook: " + book.getBookName() +
                "\nBorrowDate: " + borrowDate.toString() +
                "\nReturnDate: " + status();
    }


}
