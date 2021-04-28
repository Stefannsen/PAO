package Services;

import Models.Author;
import Models.Availability;
import Models.Book;
import Models.Section;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


public class BookService {
    public ArrayList<Book> books;
    public ArrayList<Section> sections;


    public BookService() {
        this.books = new ArrayList<Book>();
        this.sections = new ArrayList<Section>();
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public ArrayList<Section> getSections() {
        return sections;
    }

    public void setSections(ArrayList<Section> sections) {
        this.sections = sections;
    }

    public void displayAllBooks() throws IOException {
        Audit.getInstance().writeInfo();
        if (books.size() == 0)
            System.out.println("The library is empty!");
        else {
            System.out.println("***** Books *****");
            for (Book book : books)
                System.out.println(book.toString());
            System.out.println('\n');
        }
    }

    public boolean inLibrary(Book book) {
        return books.contains(book);
    }

    public boolean inLibrary(String id) {
        if (books.isEmpty())
            return false;
        for (Book book : books)
            if (book.getId().equals(id))
                return true;
        return false;
    }

    // Add an object
    public void addBook(Book book, Section section) throws IOException {
        Audit.getInstance().writeInfo();
        if (books.contains(book)) {
            System.out.println("Book already in library!");
            return;
        }

        if (sections.contains(section)) {
                book.setSection(section);
                books.add(book);
            }
        else
            System.out.println("The category doesn't exist!");

    }

    // Parameter id
    public void deleteBook(String id) throws IOException {
        Audit.getInstance().writeInfo();
        for(Book book : books)
            if(book.getId().equals(id)) {
                if(book.getNrOfCopies() == book.getCrtNrOfCopies()){
                    books.remove(book);
                    break;
                }
                else
                    System.out.println("Copies of this book are not returned!");
            }
    }

    // Parameter book
    public void deleteBook(Book book) throws IOException {
        Audit.getInstance().writeInfo();
        if(books.contains(book)) {
            if(book.getNrOfCopies() == book.getCrtNrOfCopies())
                books.remove(book);
            else
                System.out.println("Copies of this book are not returned!");
        }
    }

    private List<Book> searchByAuthor(Author author) throws IOException {
        Audit.getInstance().writeInfo();
        return books.stream().filter(b -> b.getAuthor().equals(author)).collect(Collectors.toList());
    }

    public void displayBooksByAuthor(Author author) throws IOException {
        Audit.getInstance().writeInfo();
        List<Book> books1 = searchByAuthor(author);
        if (books1.isEmpty())
            System.out.println("No result!");
        else {
            for (Book book : books1) {
                System.out.println(book.toString());
            }
        }
    }

    public void addSection(Section section) throws IOException {
        Audit.getInstance().writeInfo();
        if(!sections.contains(section))
            sections.add(section);
        else
            System.out.println("The section already exists!");
    }

    private boolean isNotSection(Section section){
        return !sections.contains(section);
    }

    public void displayBooksBySection(Section section) throws IOException {
        Audit.getInstance().writeInfo();
        if(isNotSection(section))
            System.out.println("The section doesn't exist!");
        else {
            List<Book> bookList = books.stream()
                    .filter(b -> b.getSection()
                            .equals(section))
                    .collect(Collectors.toList());
            if(bookList.isEmpty())
                System.out.println("No books in this section1");
            else
                for(Book book : bookList)
                    System.out.println(book.toString());

        }
    }

    public void updateNrOfCopies(int newNr, Book book) throws IOException {
        Audit.getInstance().writeInfo();
        if(inLibrary(book)) {
            book.setNrOfCopies(newNr);
        }
        else{
            System.out.println("Not found1");
        }
    }

    public Book checkOutBook(Book book) throws IOException {
        Audit.getInstance().writeInfo();
        int bookIndex = books.indexOf(book);
        if(bookIndex == -1) {
            System.out.println("Not found!");
            return null;
        }
        else{
            Book checkedBook = books.get(bookIndex);
            if(checkedBook.checkAvailability() == Availability.Available){
                checkedBook.setCrtNrOfCopies(book.getCrtNrOfCopies() - 1);
                return checkedBook;
            }
            else
                System.out.println("The book is unavailable!");
        }

        return null;
    }

    public Book checkInBook(Book book) throws IOException {
        Audit.getInstance().writeInfo();
        int bookIndex = books.indexOf(book);
        if(bookIndex == -1) {
            System.out.println("Not found!");
            return null;
        }
        else{
            Book checkedBook = books.get(bookIndex);
            checkedBook.setCrtNrOfCopies(book.getCrtNrOfCopies() + 1);
            return checkedBook;
        }
    }

}
