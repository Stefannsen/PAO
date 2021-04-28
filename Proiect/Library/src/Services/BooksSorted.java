package Services;

import Models.Book;

import java.util.Comparator;

public class BooksSorted implements Comparator<Book> {

    @Override
    public int compare(Book o1, Book o2) {
        int book_name = o1.getBookName().compareTo(o2.getBookName());
        int nr1 = o1.getCrtNrOfCopies() - o2.getCrtNrOfCopies();
        int nr2 = o1.getNrOfCopies() - o2.getNrOfCopies();
        return (book_name == 0) ? (nr1 == 0) ? nr2 : nr1 : book_name;
    }
}
