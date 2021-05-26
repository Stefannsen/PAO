package Repositories;

import ConfigDB.DatabaseConfiguration;
import Models.Book;
import Models.BorrowInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

public class BorrowInfoRepository {
    private static BorrowInfoRepository instance;
    private BorrowInfoRepository(){}

    public static BorrowInfoRepository getInstance(){
        if(instance == null){
            instance = new BorrowInfoRepository();
        }
        return instance;
    }

    public void insert(int customerId, int bookId)   {
        AuditRepository.getInstance().insert();
        Date borrowDate = new Date();
        BookRepository bookRepository = BookRepository.getInstance();
        Optional<Book> book = bookRepository.getById(bookId);
        if(book.isPresent()) {
            Book thisBook = book.get();
            if(thisBook.getCrtNrOfCopies() > 0) {
                bookRepository.updateCrtNrOfCopies(thisBook.getCrtNrOfCopies() - 1, thisBook.getId());
                String insertPersonSql = "INSERT INTO borrow_info(customerId, bookId, borrowDate) VALUES(?, ?, ?)";
                Connection connection = DatabaseConfiguration.getDatabaseConnection();
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(insertPersonSql);
                    preparedStatement.setInt(1, customerId);
                    preparedStatement.setInt(2, bookId);
                    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
                    String currentTime = sdf.format(borrowDate);
                    preparedStatement.setDate(3, java.sql.Date.valueOf(currentTime));
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            else{
                System.out.println("Momentan stocul este epuizat!");
            }
        }
    }

//    public void insert(BorrowInfo borrowInfo)   {
//        String insertPersonSql = "INSERT INTO borrow_info VALUES(?, ?, ?, ?)";
//        Connection connection = DatabaseConfiguration.getDatabaseConnection();
//        BookRepository bookRepository = new BookRepository();
//        Optional<Book> book = bookRepository.getById();
//        if(book.isPresent()) {
//            Book thisBook = book.get();
//            try {
//                PreparedStatement preparedStatement = connection.prepareStatement(insertPersonSql);
//                preparedStatement.setInt(1, borrowInfo.getCustomerId());
//                preparedStatement.setInt(2, borrowInfo.getBookId());
//                preparedStatement.setDate(3, (java.sql.Date) borrowInfo.getBorrowDate());
//                preparedStatement.setDate(4, (java.sql.Date) borrowInfo.getReturnDate());
//                preparedStatement.execute();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    public ArrayList<BorrowInfo> getByCustomerId(int id)   {
        AuditRepository.getInstance().insert();
        String selectSql = "SELECT p.customerId, p.bookId, p.borrowDate, p.returnDate FROM borrow_info p, customer c WHERE p.customerId = ? AND p.customerId = c.id";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<BorrowInfo> resulted = new ArrayList<>();
            while(resultSet.next()){
                var x = mapBI(resultSet);
                if(x.isPresent())
                    resulted.add(x.get());
            }
            return resulted;
        } catch (SQLException e)    {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public ArrayList<BorrowInfo> getAll()   {
        AuditRepository.getInstance().insert();
        String selectSql = "SELECT * FROM borrow_info";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<BorrowInfo> resulted = new ArrayList<>();
            while(resultSet.next()){
                var x = mapBI(resultSet);
                if(x.isPresent())
                    resulted.add(x.get());
            }
            return resulted;
        } catch (SQLException e)    {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private Optional<BorrowInfo> mapBI(ResultSet resultSet) throws SQLException  {
        AuditRepository.getInstance().insert();
        int customerId = resultSet.getInt(1);
        int bookId = resultSet.getInt(2);
        Date borrowDate = resultSet.getDate(3);
        Date returnDate = resultSet.getDate(4);
        return Optional.of(new BorrowInfo(customerId, bookId, borrowDate, returnDate));

    }



    public void updateReturnDate(int customerId, int bookId) {
        AuditRepository.getInstance().insert();
        AuditRepository.getInstance().insert();
        Date returnDate = new Date();
        BookRepository bookRepository = BookRepository.getInstance();
        Optional<Book> book = bookRepository.getById(bookId);
        if(book.isPresent()) {
            Book thisBook = book.get();
            System.out.println(thisBook.getCrtNrOfCopies());
            System.out.println(thisBook.getNrOfCopies());
            if (thisBook.getCrtNrOfCopies() < thisBook.getNrOfCopies()) {
                bookRepository.updateCrtNrOfCopies(thisBook.getCrtNrOfCopies() + 1, thisBook.getId());
                String updateNameSql = "UPDATE borrow_info SET returnDate=? WHERE customerId=? AND bookId=?";
                Connection connection = DatabaseConfiguration.getDatabaseConnection();

                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(updateNameSql);
                    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
                    String currentTime = sdf.format(returnDate);
                    preparedStatement.setDate(1, java.sql.Date.valueOf(currentTime));
                    preparedStatement.setInt(2, customerId);
                    preparedStatement.setInt(3, bookId);
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            System.out.println("Cannot check in this book!");
        }
    }

    public void delete(int customerId, int bookId){
        AuditRepository.getInstance().insert();
        AuditRepository.getInstance().insert();
        String insertPersonSql = "DELETE FROM borrow_info WHERE customerId = ? AND bookId = ?";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertPersonSql);
            preparedStatement.setInt(1, customerId);
            preparedStatement.setInt(2, bookId);
            preparedStatement.execute();
        } catch (SQLException e)    {
            e.printStackTrace();
        }
    }

    private Optional<BorrowInfo> mapToBorrowInfo(ResultSet resultSet) throws SQLException  {
        AuditRepository.getInstance().insert();
        if(resultSet.next()){
            int customerId = resultSet.getInt(1);
            int bookId = resultSet.getInt(2);
            Date borrowDate = resultSet.getDate(3);
            Date returnDate = resultSet.getDate(4);
            return Optional.of(new BorrowInfo(customerId, bookId, borrowDate, returnDate));
        }
        return Optional.empty();
    }
}
