package Repositories;

import ConfigDB.DatabaseConfiguration;
import Models.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class BookRepository {
    private static BookRepository instance;
    private BookRepository(){}
    public static BookRepository getInstance(){
        if(instance == null)
            instance = new BookRepository();
        return instance;
    }

    public void insert(String bookName, int nrOfCopies, int crtNrOfCopies, int sectionId, int authorId, int publisherId)   {
        AuditRepository.getInstance().insert();
        String insertPersonSql = "INSERT INTO book(bookName, nrOfCopies, crtNrOfCopies, sectionId, authorId, publisherId)" +
                " VALUES(?, ?, ?, ?, ?, ?)";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertPersonSql);
            preparedStatement.setString(1, bookName);
            preparedStatement.setInt(2, nrOfCopies);
            preparedStatement.setInt(3, crtNrOfCopies);
            preparedStatement.setInt(4, sectionId);
            preparedStatement.setInt(5, authorId);
            preparedStatement.setInt(6, publisherId);
            preparedStatement.executeUpdate();
        } catch (SQLException e)    {
            e.printStackTrace();
        }
    }

    public void insert(Book book)   {
        AuditRepository.getInstance().insert();
        String insertPersonSql = "INSERT INTO book VALUES(null, ?, ?, ?, ?, ?, ?)";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertPersonSql);
            preparedStatement.setString(1, book.getBookName());
            preparedStatement.setInt(2, book.getNrOfCopies());
            preparedStatement.setInt(3, book.getCrtNrOfCopies());
            preparedStatement.setInt(4, book.getSectionId());
            preparedStatement.setInt(5, book.getAuthorId());
            preparedStatement.setInt(6, book.getPublisherId());
            preparedStatement.execute();
        } catch (SQLException e)    {
            e.printStackTrace();
        }
    }

    public Optional<Book> getById(int id)   {
        AuditRepository.getInstance().insert();
        String selectSql = "SELECT * FROM book b WHERE b.id = ?";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToBook(resultSet);
        } catch (SQLException e)    {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public ArrayList<Book> getAllBooks()   {
        AuditRepository.getInstance().insert();
        String selectSql = "SELECT * FROM book";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Book> arr = new ArrayList<>();
            while(resultSet.next()){
                var x = mapToB(resultSet);
                if(x.isPresent())
                    arr.add(x.get());
            }
            return arr;
        } catch (SQLException e)    {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private Optional<Book> mapToB(ResultSet resultSet) throws SQLException  {
        AuditRepository.getInstance().insert();
        int id = resultSet.getInt(1);
        String bookName = resultSet.getString(2);
        int nrOfCopies = resultSet.getInt(3);
        int crtNrOfCopies = resultSet.getInt(4);
        int sectionId = resultSet.getInt(5);
        int authorId = resultSet.getInt(6);
        int publisherId = resultSet.getInt(7);
        return Optional.of(new Book(id, bookName, nrOfCopies, crtNrOfCopies,sectionId, authorId, publisherId));
    }

    public void updateCrtNrOfCopies(int crtNrOfCopies, int id) {
        AuditRepository.getInstance().insert();
        String updateNameSql = "UPDATE book SET crtNrOfCopies=? WHERE id=?";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateNameSql);
            preparedStatement.setInt(1, crtNrOfCopies);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e)    {
            e.printStackTrace();
        }
    }

    public void delete(int id){
        AuditRepository.getInstance().insert();
        String insertPersonSql = "DELETE FROM book WHERE id = ?";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertPersonSql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e)    {
            e.printStackTrace();
        }
    }

    private Optional<Book> mapToBook(ResultSet resultSet) throws SQLException  {
        AuditRepository.getInstance().insert();
        if(resultSet.next()){
            int id = resultSet.getInt(1);
            String bookName = resultSet.getString(2);
            int nrOfCopies = resultSet.getInt(3);
            int crtNrOfCopies = resultSet.getInt(4);
            int sectionId = resultSet.getInt(5);
            int authorId = resultSet.getInt(6);
            int publisherId = resultSet.getInt(7);

            return Optional.of(new Book(id, bookName, nrOfCopies, crtNrOfCopies,sectionId, authorId, publisherId));
        }
        return Optional.empty();
    }
}
