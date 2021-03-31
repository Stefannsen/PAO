package Services;

import Models.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

public class CustomerService {
    private int count = 0;
    private int countH = 0;
    public Customer[] customers;
    public BorrowInfo[] history;

    public CustomerService(){
        customers = new Customer[100];
        history = new BorrowInfo[100];
    }

    public int isCustomer(String id){
        for(int i = 0; i < count; i++)
            if(customers[i].getId().equals(id))
                return i;
        return -1;
    }

    public void addCustomer(Customer customer){
        if(isCustomer(customer.getId()) > -1)
            System.out.println("This is already a customer!");
        else{
            if(count + 1 < 100)
                customers[count++] = customer;
            else
                System.out.println("Come back later!");
        }
    }

    public void displayAllCustomers(){
        if(count == 0)
            System.out.println("There are not any customers yet :(");
        else{
            for(int i = 0; i < count; i++)
                customers[i].showCustomer();
        }
    }

    public void displayCheckedOutBooks(String id){
        int index = isCustomer(id);
        if(index != -1){
            System.out.println(Arrays.toString(customers[index].getBorrowedBooks()));
        }
        else
            System.out.println("Not found!");
    }


    public void displayHistory(){
        for(int i = 0; i < countH; i++){
            System.out.println(history[i].toString());
        }
    }

    public void sortCustomersByName(){
       Customer[] arr = new Customer[count];
       System.arraycopy(customers, 0, arr, 0, 3);
       Arrays.sort(arr, new CustomersSortedByName());
       for(int i = 0; i < count; i++)
           arr[i].showCustomer();
    }

    public void checkOutBook(String id, String bookId, BookService books){
        int cIndex = isCustomer(id);
        if(cIndex == -1)
            System.out.println("Not found!");
        else{
            Book book = books.checkOutBook(bookId);
            if(book != null){
                if(customers[cIndex].getNrOfBooks() < customers[cIndex].getBooksLimit()) {
                    customers[cIndex].getBorrowedBooks()[customers[cIndex].getNrOfBooks()] = book;
                    customers[cIndex].setNrOfBooks(customers[cIndex].getNrOfBooks() + 1);
                    history[countH++] = new BorrowInfo(customers[cIndex], book, new Date());
                }
                else{
                    System.out.println("The customer can't get more than 5 books at the same time!");
                }
            }
        }
    }

    public void checkInBook(String id, String bookId, BookService books){
        int cIndex = isCustomer(id);
        if(cIndex == -1)
            System.out.println("Not found!");
        else{
            Book[] borrowedBooks = customers[cIndex].getBorrowedBooks();
            int nrb = customers[cIndex].getNrOfBooks();
            Book book = books.checkInBook(bookId);
            if(book != null) {
                for(int i = 0; i < nrb; i++)
                    if(borrowedBooks[i].getId().equals(bookId)) {
                        System.arraycopy(borrowedBooks, i +1, borrowedBooks, i, nrb - 1 - i);
                        borrowedBooks[nrb-1] = null;
                        customers[cIndex].setNrOfBooks(nrb - 1);
                        for(int j = 0; j < countH; j++)
                            if(history[j].getCustomer().getId().equals(id) && history[j].getBook().getId().equals(bookId)){
                                history[j].setReturnDate(new Date());
                            }
                        break;
                    }
            }
        }
    }
}
