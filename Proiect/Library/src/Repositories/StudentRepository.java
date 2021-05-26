package Repositories;

import ConfigDB.DatabaseConfiguration;
import Models.Customer;
import Models.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class StudentRepository {
    private static StudentRepository instance;
    private StudentRepository(){}

    public static StudentRepository getInstance(){
        if(instance == null)
            instance = new StudentRepository();
        return instance;
    }
    public void insert(String lastName, String firstName, String cnp, String u_name, int year)   {
        AuditRepository.getInstance().insert();
        CustomerRepository customerRepository = CustomerRepository.getInstance();
        customerRepository.insert(lastName, firstName, cnp);
        Optional<Customer> customer = customerRepository.getByCNP(cnp);
        if(customer.isPresent()){
            int id = customer.get().getId();
            String insertPersonSql = "INSERT INTO student_reader(id, universityName, yearOfStudy) VALUES(?,?,?)";
            Connection connection = DatabaseConfiguration.getDatabaseConnection();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(insertPersonSql);
                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, u_name);
                preparedStatement.setInt(3, year);
                preparedStatement.executeUpdate();
            } catch (SQLException e)    {
                e.printStackTrace();
            }
        }
    }

    Optional<Student> getById(int id)   {
        AuditRepository.getInstance().insert();
        String selectSql = "SELECT * FROM customer c, student_reader nr WHERE c.id = nr.id AND c.id = ?";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToStudent(resultSet);
        } catch (SQLException e)    {
            e.printStackTrace();
        }
        return Optional.empty();
    }


    public void delete(int id){
        AuditRepository.getInstance().insert();
        String insertPersonSql = "DELETE FROM student_reader WHERE id = ?";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertPersonSql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e)    {
            e.printStackTrace();
        }
    }

    public Optional<Student> mapToStudent(ResultSet resultSet) throws SQLException  {

        if(resultSet.next()){
            int id = resultSet.getInt(1);
            String lastNAme = resultSet.getString(2);
            String firstNAme = resultSet.getString(3);
            String cnp = resultSet.getString(4);
            String u_name = resultSet.getString(5);
            int year = resultSet.getInt(6);
            return Optional.of(new Student(id, lastNAme, firstNAme, cnp, u_name, year));
        }
        return Optional.empty();
    }
}
