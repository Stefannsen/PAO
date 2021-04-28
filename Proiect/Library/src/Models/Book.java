package Models;


import java.util.Objects;

public class Book implements Comparable<Book>{
    private static int count = 0;
    private final String id;
    private String bookName;
    private int nrOfCopies;
    private int crtNrOfCopies;
    private Section section;
    private Author author;
    private Publisher publisher;

    public Book(String bookName, int nrOfCopies) {
        this.bookName = bookName;
        this.nrOfCopies = nrOfCopies;
        this.crtNrOfCopies = nrOfCopies;
    }

    public Book(String bookName, int nrOfCopies, Author author, Publisher publisher) {
        this.bookName = bookName;
        this.nrOfCopies = nrOfCopies;
        this.crtNrOfCopies = nrOfCopies;
        this.author = author;
        this.publisher = publisher;
    }

    public Book(){}

    {
        count++;
        this.id = "b" + count;
    }

    public String getId() {
        return id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getNrOfCopies() {
        return nrOfCopies;
    }

    public void setNrOfCopies(int nrOfCopies) {
        this.nrOfCopies = nrOfCopies;
    }

    public int getCrtNrOfCopies() {
        return crtNrOfCopies;
    }

    public void setCrtNrOfCopies(int crtNrOfCopies) {
        this.crtNrOfCopies = crtNrOfCopies;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Availability checkAvailability(){
        if(this.crtNrOfCopies < 1)
            return Availability.Unavailable;
        else
            return Availability.Available;
    }

    @Override
    public String toString() {
        return "**********************\n" +
                "ID: " + this.id +
                "\nTitle: " + this.bookName +
                "\nAuthor: " + this.author.getName() +
                "\nStatus: " + this.checkAvailability() +
                "\n**********************";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(bookName, book.bookName) && Objects.equals(author.getName(),
                book.author.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookName, author.getName());
    }


    @Override
    public int compareTo(Book o) {
        int c_name = this.bookName.compareTo(o.getBookName());
        int c_nr   = this.nrOfCopies - o.getNrOfCopies();
        return (c_name == 0) ? c_nr : c_name;
    }
}
