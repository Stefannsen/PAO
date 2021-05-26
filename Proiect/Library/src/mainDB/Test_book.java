package mainDB;

import ConfigDB.DatabaseConfiguration;
import ConfigDB.SetUpDataUsingStatemnt;
import Models.Book;
import Repositories.BookRepository;
import Repositories.PublisherRepository;

import java.util.Optional;

public class Test_book {
    public static void main(String[] args) {
        SetUpDataUsingStatemnt setUpData = new SetUpDataUsingStatemnt();
        setUpData.createTableBook();
        // setUpData.getAllAuthors();

        BookRepository bookRepository = BookRepository.getInstance();
//        bookRepository.insert("Moara cu noroc", 12, 12, 6, 2, 1);
//        bookRepository.insert("Amintiri din copilarie", 24, 24, 6, 3, 1);
//        bookRepository.insert("Robinson Crusoe",34, 34, 1, 8,3);
//        // Get by id
//        Optional<Book> publisher = bookRepository.getById(1);
//        if(publisher.isPresent()){
//            System.out.println(publisher.get());
//        }
//
//        // Update lastName
//        bookRepository.updateCrtNrOfCopies(1000, 1);
//
//        // Get by id + see the changes
        Optional<Book> book = bookRepository.getById(1);
        if(book.isPresent())  {
            System.out.println(book.get().getCrtNrOfCopies());
        }
//
//        // Delete by id
//        bookRepository.delete(6);

//        setUpData.getAllBooks();

        // Close connection
        DatabaseConfiguration.closeDataBaseConnection();
    }
}
