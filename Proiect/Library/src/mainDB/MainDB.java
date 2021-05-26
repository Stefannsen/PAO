package mainDB;

import ConfigDB.DatabaseConfiguration;
import ConfigDB.SetUpDataUsingStatemnt;
import Models.Author;
import Repositories.AuthorRepository;

import java.net.SocketTimeoutException;
import java.util.Optional;

public class MainDB {
    public static void main(String[] args) {
        SetUpDataUsingStatemnt setUpData = new SetUpDataUsingStatemnt();
//      setUpData.createTableAuthor();
//      setUpData.addAuthor();
//      setUpData.getAllAuthors();

        // Add all the authors
        AuthorRepository personRepository = AuthorRepository.getInstance();
        personRepository.insert("Hemingway", "Ernest", "SUA");
        personRepository.insert("Slaviciisasa", "Ioan", "Romania");
        personRepository.insert("Creanga", "Ion", "Romania");
        personRepository.insert("Martin", "George R.R.", "SUA");
        personRepository.insert("Twain", "MArk", "SUA");
        personRepository.insert("Rowling", "J.K.", "UK");
        personRepository.insert("Caragiale", "Ion Luca", "Romania");
        personRepository.insert("Defoe", "Daniel", "UK");
        personRepository.insert("Badwriter", "Unknown-Uninspired", "TARA DE NICAIERI");
        // Get ALL
        setUpData.getAllAuthors();
        personRepository.insert(new Author("Petersen", "Jordan", "Canada"));

        // Get by id
        Optional<Author> author = personRepository.getById(2);
        if(author.isPresent())  {
            System.out.println(author.get());
        }

        // Update lastName
        personRepository.updateLastName("Slavici", 2);

        // Get by id + see the changes
        author = personRepository.getById(2);
        if(author.isPresent())  {
            System.out.println(author.get());
        }

        // Delete by id
        personRepository.delete(9);

        setUpData.getAllAuthors();

        // Close connection
        DatabaseConfiguration.closeDataBaseConnection();
    }
}
