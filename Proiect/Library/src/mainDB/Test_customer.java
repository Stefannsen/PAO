package mainDB;

import ConfigDB.DatabaseConfiguration;
import ConfigDB.SetUpDataUsingStatemnt;
import Models.Student;
import Repositories.BorrowInfoRepository;
import Repositories.NormalReaderRepository;
import Repositories.PublisherRepository;
import Repositories.StudentRepository;

public class Test_customer {
    public static void main(String[] args) {
        SetUpDataUsingStatemnt setUpData = new SetUpDataUsingStatemnt();
        setUpData.createTableAudit();
//        setUpData.createTableCustomer();
//        setUpData.createTableNormalReader();
//        setUpData.createTableVIPReader();
//        setUpData.createTableStudent();
//        setUpData.createTableBorrowInfo();
//        NormalReaderRepository normalReaderRepository = new NormalReaderRepository();
//        normalReaderRepository.insert("Popescu", "Ioana", "6000203192837");
//        normalReaderRepository.insert("Ionescu", "Andrei", "6000200092837");
//        normalReaderRepository.insert("Petru", "Rares", "6000923192837");
//        BorrowInfoRepository borrowInfoRepository = BorrowInfoRepository.getInstance();
//        borrowInfoRepository.insert(2,1);
//        borrowInfoRepository.insert(1,1);
//        borrowInfoRepository.insert(1,2);
//        borrowInfoRepository.insert(1,3);

        StudentRepository sr = StudentRepository.getInstance();
        sr.insert("Negulescu","Stefan","5000203128436", "UniBuc", 2);
        setUpData.getAllStudents();
//        borrowInfoRepository.updateReturnDate(1,1);
//        setUpData.getAllBorrowInfos();
//
//        setUpData.getAllNormalReaders();

        DatabaseConfiguration.closeDataBaseConnection();
    }
}
