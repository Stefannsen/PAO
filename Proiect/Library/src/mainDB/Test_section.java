package mainDB;

import ConfigDB.DatabaseConfiguration;
import ConfigDB.SetUpDataUsingStatemnt;
import Models.Publisher;
import Models.Section;
import Repositories.PublisherRepository;
import Repositories.SectionRepository;

import java.util.Optional;

public class Test_section {
    public static void main(String[] args) {
        SetUpDataUsingStatemnt setUpData = new SetUpDataUsingStatemnt();
        setUpData.createTableSection();
        // setUpData.getAllAuthors();

        SectionRepository sectionRepository = SectionRepository.getInstance();
        sectionRepository.insert("Aventuraaaaaaaaaaaa");
        sectionRepository.insert("Drama");
        sectionRepository.insert("Istorie");
        sectionRepository.insert("Psihologie");
        sectionRepository.insert("Economie");
        sectionRepository.insert("Beletristica");
        sectionRepository.insert("Sectiune gresita !!!!!");

        // Get by id
        Optional<Section> section = sectionRepository.getById(1);
        if(section.isPresent()){
            System.out.println(section.get());
        }

        // Update lastName
        sectionRepository.updateName("Aventura", 1);

        // Get by id + see the changes
        section = sectionRepository.getById(1);
        if(section.isPresent())  {
            System.out.println(section.get());
        }

        // Delete by id
        sectionRepository.delete(7);

        setUpData.getAllSections();

        // Close connection
        DatabaseConfiguration.closeDataBaseConnection();
    }
}
