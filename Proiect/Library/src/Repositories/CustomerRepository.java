package Repositories;

import ConfigDB.DatabaseConfiguration;
import Models.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class CustomerRepository {
    private static CustomerRepository instance;
    private CustomerRepository(){}

    public static CustomerRepository getInstance(){
        if(instance == null)
            instance = new CustomerRepository();
        return instance;
    }

     void insert(String lastName, String firstName, String cnp)   {
         AuditRepository.getInstance().insert();
        String insertPersonSql = "INSERT INTO customer(lastName, firstName, cnp) VALUES(?, ?, ?)";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertPersonSql);
            preparedStatement.setString(1, lastName);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, cnp);
            preparedStatement.executeUpdate();
        } catch (SQLException e)    {
            e.printStackTrace();
        }
    }

     public void insert(Customer customer)   {
         AuditRepository.getInstance().insert();
        String insertPersonSql = "INSERT INTO customer VALUES(null, ?, ?, ?)";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertPersonSql);
            preparedStatement.setString(1, customer.getLastName());
            preparedStatement.setString(2, customer.getFirstName());
            preparedStatement.setString(3, customer.getCnp());
            preparedStatement.execute();
        } catch (SQLException e)    {
            e.printStackTrace();
        }
    }

     public Optional<Customer> getById(int id)   {
         AuditRepository.getInstance().insert();
        String selectSql = "SELECT * FROM customer c WHERE c.id = ?";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToCustomer(resultSet);
        } catch (SQLException e)    {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    Optional<Customer> getByCNP(String cnp)   {
        AuditRepository.getInstance().insert();
        String selectSql = "SELECT * FROM customer c WHERE c.cnp = ?";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setString(1, cnp);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToCustomer(resultSet);
        } catch (SQLException e)    {
            e.printStackTrace();
        }
        return Optional.empty();
    }

     void delete(int id){
         AuditRepository.getInstance().insert();
        String insertPersonSql = "DELETE FROM customer WHERE id = ?";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertPersonSql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e)    {
            e.printStackTrace();
        }
    }

     Optional<Customer> mapToCustomer(ResultSet resultSet) throws SQLException  {
        if(resultSet.next()){
            int id = resultSet.getInt(1);
            String lastNAme = resultSet.getString(2);
            String firstNAme = resultSet.getString(3);
            String cnp = resultSet.getString(4);
            return Optional.of(new Customer(id, lastNAme, firstNAme, cnp));
        }
        return Optional.empty();
    }
}
