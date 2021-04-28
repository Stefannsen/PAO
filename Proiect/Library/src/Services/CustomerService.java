package Services;
import Models.*;

import java.io.IOException;
import java.util.*;

public class CustomerService {

    public ArrayList<Customer> customers;
    public ArrayList<BorrowInfo> history;

    public CustomerService(){
        customers = new ArrayList<Customer>();
        history = new ArrayList<BorrowInfo>();
    }

    public int isCustomer(Customer customer){
        return customers.indexOf(customer);
    }

    public void addCustomer(Customer customer) throws IOException {
        Audit.getInstance().writeInfo();
        if(isCustomer(customer) != -1)
            System.out.println("This is already a customer!");
        else
            customers.add(customer);
    }

    public void displayAllCustomers() throws IOException {
        Audit.getInstance().writeInfo();
        if(customers.isEmpty())
            System.out.println("There are not any customers yet :(");
        else{
            for(Customer customer : customers)
                System.out.println(customer.toString());
        }
    }

    public void displayCheckedOutBooks(Customer customer) throws IOException {
        Audit.getInstance().writeInfo();
        int index = isCustomer(customer);
        if(index != -1){
            customer.getBorrowedBooks().forEach(System.out::println);
        }
        else
            System.out.println("Not found!");
    }


    public void displayHistory() throws IOException {
        Audit.getInstance().writeInfo();
        for(BorrowInfo b : history)
            System.out.println(b.toString());
    }

    public void sortCustomersByName() throws IOException {
        Audit.getInstance().writeInfo();
       customers.stream()
               .sorted((c1, c2) -> c1.getName().compareTo(c2.getName()))
               .forEach(Customer::showCustomer);
    }

    public void checkOutBook(Customer customer, Book book, BookService bs) throws IOException {
        Audit.getInstance().writeInfo();
        if(!customers.contains(customer))
            System.out.println("Not found!=========");
        else{
            Book checkOutBook = bs.checkOutBook(book);
            if(checkOutBook != null){
                if(customer.getNrOfBooks() < customer.getBooksLimit()) {
                    customer.getBorrowedBooks().add(checkOutBook);
                    history.add(new BorrowInfo(customer, checkOutBook));
                }
                else{
                    System.out.println("The customer can't get more than 5 books at the same time!");
                }
            }
        }
    }

    public void checkInBook(Customer customer, Book book, BookService bs) throws IOException {
        Audit.getInstance().writeInfo();
        if(!customers.contains(customer))
            System.out.println("Not found!");
        else{
            HashSet<Book> borrowedBooks = customer.getBorrowedBooks();
            int nrb = customer.getNrOfBooks();
            Book checkInBook = bs.checkInBook(book);
            if(checkInBook != null) {
               borrowedBooks.remove(book);
               for(BorrowInfo b : history)
                   if(b.getCustomer().equals(customer) && b.getBook().equals(book)){
                       b.setReturnDate(new Date());
                       break;
                    }
            }
        }
    }
}
