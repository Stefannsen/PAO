package Services;

import Models.Availability;
import Models.Book;
import Models.Section;


public class BookService {
    public Book[] books;
    public int crtIndex;
    public int maxIndex;
    public Section[] sections;
    public int sectionIndex;

    public BookService(int maxIndex) {
        this.crtIndex = -1;
        this.maxIndex = maxIndex;
        this.books = new Book[maxIndex];
        this.sectionIndex = -1;
        this.sections = new Section[20];
    }

    public void displayAllBooks() {
        if (crtIndex == -1)
            System.out.println("The library is empty!");
        else {
            System.out.println("***** Books *****");
            for (int i = 0; i <= crtIndex; i++)
                System.out.println(books[i].toString());
            System.out.println('\n');
        }
    }

    public int inLibrary(Book book) {
        if (crtIndex == -1)
            return -1;
        for (int i = 0; i <= crtIndex; i++)
            if (books[i].getId().equals(book.getId()))
                return i;
        return -1;
    }

    public int inLibrary(String id) {
        if (crtIndex == -1)
            return -1;
        for (int i = 0; i <= crtIndex; i++)
            if (books[i].getId().equals(id))
                return i;
        return -1;
    }

    // Add an object
    public void addBook(Book book, String sectionName) {
        if (this.inLibrary(book) > -1) {
            System.out.println("Book already in library!");
            return;
        }
        if (this.crtIndex + 1 < this.maxIndex) {
            boolean ok = false;
            for(int i = 0; i <= sectionIndex; i++)
                if(sections[i].getName().equals(sectionName)) {
                    ok = true;
                    this.books[++crtIndex] = book;
                    this.books[crtIndex].setSection(sections[i]);
                    break;
                }
            if(!ok)
                System.out.println("The category doesn't exist!");
        }
        else
            System.out.println("The library is full :(");
    }

    // Read and add an object
/*    public void addBook(String sectionName){
        Book book = new Book();
        addBook(book, sectionName);
    }*/

    // Parameter id
    public void deleteBook(String id){
        for(int i = 0; i <= crtIndex; i++)
            if(books[i].getId().equals(id)) {
                Book book = books[i];
                if(book.getNrOfCopies() == book.getCrtNrOfCopies()){
                    System.arraycopy(books, i + 1, books, i, crtIndex - i);
                    books[crtIndex] = null;
                    crtIndex--;
                    break;
                }
                else
                    System.out.println("Copies of this book are not returned!");
            }
    }

    private Book[] searchByAuthor(String author) {
        Book[] authorsBooks = new Book[crtIndex + 1];
        int count = 0;
        for (int i = 0; i <= crtIndex; i++)
            if (books[i].getAuthor().getName().equals(author))
                authorsBooks[count++] = books[i];
        return authorsBooks;
    }

    public void displayBooksByAuthor(String author) {
        Book[] books1;
        books1 = searchByAuthor(author);
        if (books1.length == 0)
            System.out.println("No result!");
        else {
            for (Book book : books1) {
                if (book == null)
                    break;
                else
                    System.out.println(book.toString());
            }
        }
    }

    public void addSection(String sectionName){
        if(!isSection(sectionName))
            sections[++sectionIndex] = new Section(sectionName);
        else
            System.out.println("The section already exists!");
    }

    public void displayBooksBySection(String sectionName){
        if(!isSection(sectionName))
            System.out.println("The section doesn't exist!");
        else {
            boolean atLeastOne = false;
            for (int i = 0; i <= crtIndex; i++)
                if (books[i].getSection().getName().equals(sectionName)) {
                    atLeastOne = true;
                    System.out.println(books[i].toString());
                }
            if(!atLeastOne)
                System.out.println("No books in this category!");
        }
    }

    public boolean isSection(String sectionName){
        for(int i = 0; i <= sectionIndex; i++)
            if(sections[i].getName().equals(sectionName))
                return true;
        return false;
    }

    public void updateNrOfCopies(int newNr, String id){
        if(inLibrary(id) > 0) {
            for (int i = 0; i < crtIndex; i++)
                if (books[i].getId().equals(id)) {
                    books[i].setNrOfCopies(newNr);
                    books[i].setCrtNrOfCopies(newNr);
                }
        }
        else{
            System.out.println("Not found1");
        }
    }

    public Book checkOutBook(String id){
        int bookIndex = inLibrary(id);
        if(bookIndex == -1) {
            System.out.println("Not found!");
            return null;
        }
        else{
            Book book = books[bookIndex];
            if(book.checkAvailability() == Availability.Available){
               book.setCrtNrOfCopies(book.getCrtNrOfCopies() - 1);
               return book;
            }
            else
                System.out.println("The book is unavailable!");
        }

        return null;
    }

    public Book checkInBook(String id){
        int bookIndex = inLibrary(id);
        if(bookIndex == -1) {
            System.out.println("Not found!");
            return null;
        }
        else{
            Book book = books[bookIndex];
            book.setCrtNrOfCopies(book.getCrtNrOfCopies() + 1);
            return book;
        }
    }

}
