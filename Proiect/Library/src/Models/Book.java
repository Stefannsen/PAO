package Models;

import Repositories.AuthorRepository;
import Repositories.PublisherRepository;
import Repositories.SectionRepository;

import java.util.Objects;
import java.util.Optional;

public class Book implements Comparable<Book>{
    private int id;
    private String bookName;
    private int nrOfCopies;
    private int crtNrOfCopies;
    private int sectionId;
    private int authorId;
    private int publisherId;
    private Section section;
    private Author author;
    private Publisher publisher;

    public Book(int id, String bookName, int nrOfCopies, int crtNrOfCopies){
        this.id = id;
        this.bookName = bookName;
        this.nrOfCopies = nrOfCopies;
        this.crtNrOfCopies = crtNrOfCopies;
    }

    public Book(String bookName, int nrOfCopies, int sectionId, int authorId, int publisherId) {
        this.bookName = bookName;
        this.nrOfCopies = nrOfCopies;
        this.crtNrOfCopies = nrOfCopies;
        this.sectionId = sectionId;
        this.authorId = authorId;
        this.publisherId = publisherId;

        SectionRepository sectionRepository = SectionRepository.getInstance();
        Optional<Section> section = sectionRepository.getById(sectionId);
        if(section.isPresent()){
            this.section = section.get();
        }

        PublisherRepository publisherRepository = PublisherRepository.getInstance();
        Optional<Publisher> publisher = publisherRepository.getById(publisherId);
        if(publisher.isPresent()){
            this.publisher = publisher.get();
        }

        Optional<Author> author = AuthorRepository.getInstance().getById(authorId);
        if(author.isPresent())  {
            this.author = author.get();
        }
    }

    public Book(int id, String bookName, int nrOfCopies, int crtNrOfCopies,  int sectionId, int authorId, int publisherId) {
        this.id = id;
        this.bookName = bookName;
        this.nrOfCopies = nrOfCopies;
        this.crtNrOfCopies = crtNrOfCopies;
        this.sectionId = sectionId;
        this.authorId = authorId;
        this.publisherId = publisherId;

        SectionRepository sectionRepository = SectionRepository.getInstance();
        Optional<Section> section = sectionRepository.getById(sectionId);
        if(section.isPresent()){
            this.section = section.get();
        }

        PublisherRepository publisherRepository = PublisherRepository.getInstance();
        Optional<Publisher> publisher = publisherRepository.getById(publisherId);
        if(publisher.isPresent()){
            this.publisher = publisher.get();
        }

        Optional<Author> author = AuthorRepository.getInstance().getById(authorId);
        if(author.isPresent())  {
            this.author = author.get();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    @Override
    public String toString() {
        return "**********************\n" +
                "ID: " + this.id +
                "\nTitle: " + this.bookName +
                "\nAuthor: " + this.author.getFirstName() + " " +
                this.author.getLastName() +
                "\nStatus: " + this.checkAvailability() +
                "\n**********************";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && nrOfCopies == book.nrOfCopies && crtNrOfCopies == book.crtNrOfCopies && sectionId == book.sectionId && authorId == book.authorId && publisherId == book.publisherId && Objects.equals(bookName, book.bookName) && Objects.equals(section, book.section) && Objects.equals(author, book.author) && Objects.equals(publisher, book.publisher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookName, nrOfCopies, crtNrOfCopies, sectionId, authorId, publisherId, section, author, publisher);
    }

    @Override
    public int compareTo(Book o) {
        int c_crtNr = this.crtNrOfCopies - o.getCrtNrOfCopies();
        int c_nr   = this.nrOfCopies - o.getNrOfCopies();
        return (c_crtNr == 0) ? c_nr : c_crtNr;
    }
}
