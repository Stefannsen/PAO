package Models;

import java.util.Date;

public class BorrowInfo {
    private Customer customer;
    private Book book;
    private Date borrowDate;
    private Date returnDate;

    public BorrowInfo(Customer customer, Book book, Date borrowDate) {
        this.customer = customer;
        this.book = book;
        this.borrowDate = borrowDate;
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

    public String status(){
        if(returnDate == null)
            return "Not returned yet";
        else
            return returnDate.toString();
    }

    @Override
    public String toString() {
        return "********** INFO **********" +
                "\nCustomer: " + customer.getName() +
                "\nBook: " + book.getBookName() +
                "\nBorrowDate: " + borrowDate.toString() +
                "\nReturnDate: " + status();
    }

/*    public int additionalCost(){
        Date now = new Date();
        int days = now.getTime() - borrowDate.getTime()
        if(days > 60)
            return (days - 60);
        else
            return 0;
    }*/
}
