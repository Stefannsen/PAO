package Repositories;

import ConfigDB.DatabaseConfiguration;
import Models.Book;
import Models.Customer;
import Models.NormalReader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class NormalReaderRepository {
    private static NormalReaderRepository instance;
    private NormalReaderRepository(){}

    public static NormalReaderRepository getInstance(){
        if(instance == null)
            instance = new NormalReaderRepository();
        return instance;
    }
    public void insert(String lastName, String firstName, String cnp)   {
        AuditRepository.getInstance().insert();
        CustomerRepository customerRepository = CustomerRepository.getInstance();;
        customerRepository.insert(lastName, firstName, cnp);
        Optional<Customer> customer = customerRepository.getByCNP(cnp);
        if(customer.isPresent()){
            int id = customer.get().getId();
            String insertPersonSql = "INSERT INTO normal_reader(id) VALUES(?)";
            Connection connection = DatabaseConfiguration.getDatabaseConnection();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(insertPersonSql);
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            } catch (SQLException e)    {
                e.printStackTrace();
            }
        }
    }

    Optional<NormalReader> getById(int id)   {
        AuditRepository.getInstance().insert();
        String selectSql = "SELECT * FROM customer c, normal_reader nr WHERE c.id = nr.id AND c.id = ?";
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


    public void delete(int id){
        AuditRepository.getInstance().insert();
        String insertPersonSql = "DELETE FROM normal_reader WHERE id = ?";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertPersonSql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e)    {
            e.printStackTrace();
        }
    }

    public Optional<NormalReader> mapToCustomer(ResultSet resultSet) throws SQLException  {
        if(resultSet.next()){
            int id = resultSet.getInt(1);
            String lastNAme = resultSet.getString(2);
            String firstNAme = resultSet.getString(3);
            String cnp = resultSet.getString(4);
            return Optional.of(new NormalReader(id, lastNAme, firstNAme, cnp));
        }
        return Optional.empty();
    }
}
