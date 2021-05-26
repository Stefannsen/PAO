package Repositories;

import ConfigDB.DatabaseConfiguration;
import Models.Author;
import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class AuthorRepository {

    private static AuthorRepository instance;
    private AuthorRepository(){}
    public static AuthorRepository getInstance(){
        if(instance == null)
            instance = new AuthorRepository();
        return instance;
    }

    public void insert(String lastName, String firstName, String nationality)   {
        AuditRepository.getInstance().insert();
        String insertPersonSql = "INSERT INTO author(lastName, firstName, nationality) VALUES(?, ?, ?)";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertPersonSql);
            preparedStatement.setString(1, lastName);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, nationality);
            preparedStatement.executeUpdate();
        } catch (SQLException e)    {
            e.printStackTrace();
        }
    }

    public void insert(Author author)   {
        AuditRepository.getInstance().insert();
        String insertPersonSql = "INSERT INTO author VALUES(null, ?, ?, ?)";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertPersonSql);
            preparedStatement.setString(1, author.getLastName());
            preparedStatement.setString(2, author.getFirstName());
            preparedStatement.setString(3, author.getNationality());
            preparedStatement.execute();
        } catch (SQLException e)    {
            e.printStackTrace();
        }
    }

    public Optional<Author> getById(int id)   {
        AuditRepository.getInstance().insert();
        String selectSql = "SELECT * FROM author a WHERE a.id = ?";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToAuthor(resultSet);
        } catch (SQLException e)    {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public ArrayList<Author> getByAll()   {
        AuditRepository.getInstance().insert();
        String selectSql = "SELECT * FROM author";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Author> arr = new ArrayList<>();
            while(resultSet.next()){
                var x = mapToA(resultSet);
                if(x.isPresent())
                    arr.add(x.get());
            }
            return arr;
        } catch (SQLException e)    {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private Optional<Author> mapToA(ResultSet resultSet) throws SQLException  {
        int id = resultSet.getInt(1);
        String lastName = resultSet.getString(2);
        String firstName = resultSet.getString(3);
        String nationality = resultSet.getString(4);
        return Optional.of(new Author(id, lastName, firstName, nationality));
    }


    public void updateLastName(String lastName, int id) {
        AuditRepository.getInstance().insert();
        String updateNameSql = "UPDATE author SET lastName=? WHERE id=?";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateNameSql);
            preparedStatement.setString(1, lastName);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e)    {
            e.printStackTrace();
        }
    }

    public void delete(int id){
        AuditRepository.getInstance().insert();
        String insertPersonSql = "DELETE FROM author WHERE id = ?";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertPersonSql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e)    {
            e.printStackTrace();
        }
    }

    private Optional<Author> mapToAuthor(ResultSet resultSet) throws SQLException  {
        if(resultSet.next())    {
            int id = resultSet.getInt(1);
            String lastName = resultSet.getString(2);
            String firstName = resultSet.getString(3);
            String nationality = resultSet.getString(4);
            return Optional.of(new Author(id, lastName, firstName, nationality));
        }
        return Optional.empty();
    }
}
