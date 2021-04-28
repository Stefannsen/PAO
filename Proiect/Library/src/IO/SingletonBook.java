package IO;

import Models.Author;
import Models.Book;
import Models.Publisher;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SingletonBook {
    private static SingletonBook instance;
    private ArrayList<Book> books;


    private SingletonBook() {
        System.out.println("First time the constructor is being called.");
    }

    public static SingletonBook getInstance() {
        if (instance == null) {
            instance = new SingletonBook();
        }
        return instance;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    private Author getAuthorById(String Id){
        for(Author a : SingletonAuthor.getInstance().getAuthors())
            if(a.getId().equals(Id))
                return a;
        return null;
    }

    private Publisher getPublisherById(String Id){
        for(Publisher a : SingletonPublisher.getInstance().getPublishers())
            if(a.getId().equals(Id))
                return a;
        return null;
    }


    public void Read(String filePath) {
        String line = "";
        String splitBy = ",";
        ArrayList<Book> personList = new ArrayList<Book>();
        try {
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

                while ((line = br.readLine()) != null)   //returns a Boolean value
                {
                    String[] parameters = line.split(splitBy);    // use comma as separator
                    List<String> aux = Arrays.stream(parameters).map(String::trim).collect(Collectors.toList());
                    Author a = this.getAuthorById(aux.get(2));
                    Publisher p = this.getPublisherById(aux.get(3));
                    if(a != null && p != null)
                        personList.add(new Book(aux.get(0), Integer.parseInt(aux.get(1)), a, p));
                    //System.out.println("Employee [First Name=" + employee[0] + ", Last Name=" + employee[1] + ", Designation=" + employee[2]);
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        this.books =  personList;
    }

    public void Write(String filePath) throws IOException {
        FileWriter csvWriter = new FileWriter(filePath);
        for(Book p : books){
            csvWriter.append(p.getId()).append(",").append(p.getBookName()).append(",")
                    .append(p.getAuthor().getName()).append(",").append(p.getPublisher().getName());
            csvWriter.append("\n");
        }
        csvWriter.flush();
        csvWriter.close();
    }

}
