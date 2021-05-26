package mainDB;

import ConfigDB.DatabaseConfiguration;
import ConfigDB.SetUpDataUsingStatemnt;
import Models.NormalReader;
import Models.Publisher;
import Models.VIPReader;
import Repositories.*;
import Servicies.LibraryStats;

import java.util.Scanner;

public class MainTest {
    public static void main(String[] args) {
        SetUpDataUsingStatemnt setUpData = new SetUpDataUsingStatemnt();
        AuthorRepository authorRepository = AuthorRepository.getInstance();
        BookRepository bookRepository = BookRepository.getInstance();
        BorrowInfoRepository borrowInfoRepository = BorrowInfoRepository.getInstance();
        NormalReaderRepository normalReaderRepository = NormalReaderRepository.getInstance();
        PublisherRepository publisherRepository = PublisherRepository.getInstance();
        SectionRepository sectionRepository = SectionRepository.getInstance();
        StudentRepository studentRepository = StudentRepository.getInstance();
        VIPReaderRepository vipReaderRepository = VIPReaderRepository.getInstance();
        LibraryStats librarystats = new LibraryStats();


        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        while(n != 0){
            switch(n){
                case 1:
                    setUpData.getAllBooks();
                    break;
                case 2 :
                    bookRepository.insert("Mara", 20, 20, 6, 2, 1);
                    break;
                case 3:
                    borrowInfoRepository.insert(2, 3);
                    break;
                case 4:
                    borrowInfoRepository.updateReturnDate(2, 3);
                    break;
                case 5:
                    librarystats.getBorrowInfoByCustomerId(1);
                    break;
                case 6:
                    bookRepository.delete(3);
                    break;
                case 7:
                   librarystats.sortBooksByCurrentNr();
                case 8:
                    librarystats.numberOfBorrowsPerBook();
                    break;
                case 9:
                    librarystats.mostRedAuthor();
                    break;
                case 10:
                    librarystats.mostPopularBookLastMonth();
                    break;
                case 11:
                    librarystats.top3Sections();
                    break;
                case 12:
                    librarystats.unavailableBooks();
                    break;
                default:
                    System.out.println("Select a number between 1-11");
            }
            n = scanner.nextInt();
        }


        DatabaseConfiguration.closeDataBaseConnection();
    }

}
