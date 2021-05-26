package Repositories;

import ConfigDB.DatabaseConfiguration;
import Models.Publisher;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class PublisherRepository {
    private static PublisherRepository instance;
    private PublisherRepository(){}

    public static PublisherRepository getInstance(){
        if(instance == null)
            instance = new PublisherRepository();
        return instance;
    }
    public void insert(String name, String country)   {
        AuditRepository.getInstance().insert();
        String insertPersonSql = "INSERT INTO publisher(name, country) VALUES(?, ?)";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertPersonSql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, country);
            preparedStatement.executeUpdate();
        } catch (SQLException e)    {
            e.printStackTrace();
        }
    }

    public void insert(Publisher publisher)   {
        AuditRepository.getInstance().insert();
        String insertPersonSql = "INSERT INTO publisher VALUES(null, ?, ?)";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertPersonSql);
            preparedStatement.setString(1, publisher.getName());
            preparedStatement.setString(2, publisher.getCountry());
            preparedStatement.execute();
        } catch (SQLException e)    {
            e.printStackTrace();
        }
    }

    public Optional<Publisher> getById(int id)   {
        AuditRepository.getInstance().insert();
        String selectSql = "SELECT * FROM publisher p WHERE p.id = ?";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToPublisher(resultSet);
        } catch (SQLException e)    {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void updateName(String name, int id) {
        AuditRepository.getInstance().insert();
        String updateNameSql = "UPDATE publisher SET name=? WHERE id=?";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateNameSql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e)    {
            e.printStackTrace();
        }
    }

    public void delete(int id){
        AuditRepository.getInstance().insert();
        String insertPersonSql = "DELETE FROM publisher WHERE id = ?";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertPersonSql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e)    {
            e.printStackTrace();
        }
    }

    private Optional<Publisher> mapToPublisher(ResultSet resultSet) throws SQLException  {
        if(resultSet.next()){
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String country = resultSet.getString(3);
            return Optional.of(new Publisher(id, name, country));
        }
        return Optional.empty();
    }
}
