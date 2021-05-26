package mainDB;

import ConfigDB.DatabaseConfiguration;
import ConfigDB.SetUpDataUsingStatemnt;
import Models.Author;
import Models.Publisher;
import Repositories.AuthorRepository;
import Repositories.PublisherRepository;
import java.util.Optional;

public class Test_publisher {
    public static void main(String[] args) {
        SetUpDataUsingStatemnt setUpData = new SetUpDataUsingStatemnt();
        setUpData.createTablePublisher();
        // setUpData.getAllAuthors();

        PublisherRepository publisherRepository = PublisherRepository.getInstance();
        publisherRepository.insert("Nemirasaasasa", "Romania");
        publisherRepository.insert("Humanitas", "Romania");
        publisherRepository.insert("Dacia", "Romania");
        publisherRepository.insert("Curtea Veche", "Romania");
        publisherRepository.insert("Polirom", "Romania");
        publisherRepository.insert("Editura de sters", "Romania");

        // Get by id
        Optional<Publisher> publisher = publisherRepository.getById(1);
        if(publisher.isPresent()){
            System.out.println(publisher.get());
        }

        // Update lastName
        publisherRepository.updateName("Nemira", 1);

        // Get by id + see the changes
        publisher = publisherRepository.getById(1);
        if(publisher.isPresent())  {
            System.out.println(publisher.get());
        }

        // Delete by id
        publisherRepository.delete(6);

        setUpData.getAllPublishers();

        // Close connection
        DatabaseConfiguration.closeDataBaseConnection();

    }
}
