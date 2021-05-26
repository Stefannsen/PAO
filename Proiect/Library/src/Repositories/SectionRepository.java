package Repositories;

import ConfigDB.DatabaseConfiguration;
import Models.Section;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class SectionRepository {
    private static SectionRepository instance;
    private SectionRepository(){}

    public static SectionRepository getInstance(){
        if(instance == null)
            instance = new SectionRepository();
        return instance;
    }
    public void insert(String name)   {
        AuditRepository.getInstance().insert();
        String insertPersonSql = "INSERT INTO section(name) VALUES(?)";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertPersonSql);
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        } catch (SQLException e)    {
            e.printStackTrace();
        }
    }

    public void insert(Section section)   {
        AuditRepository.getInstance().insert();
        String insertPersonSql = "INSERT INTO section VALUES(null, ?)";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertPersonSql);
            preparedStatement.setString(1, section.getName());
            preparedStatement.execute();
        } catch (SQLException e)    {
            e.printStackTrace();
        }
    }

    public Optional<Section> getById(int id)   {
        AuditRepository.getInstance().insert();
        String selectSql = "SELECT * FROM section s WHERE s.id = ?";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToSection(resultSet);
        } catch (SQLException e)    {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public ArrayList<Section> getAll()   {
        AuditRepository.getInstance().insert();
        String selectSql = "SELECT * FROM section";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Section> arr = new ArrayList<>();
            while(resultSet.next()){
                var x = mapToS(resultSet);
                if(x.isPresent())
                    arr.add(x.get());
            }
            return arr;
        } catch (SQLException e)    {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private Optional<Section> mapToS(ResultSet resultSet) throws SQLException  {
        int id = resultSet.getInt(1);
        String name = resultSet.getString(2);
        return Optional.of(new Section(id, name));
    }

    public void updateName(String name, int id) {
        AuditRepository.getInstance().insert();
        String updateNameSql = "UPDATE section SET name=? WHERE id=?";
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
        String insertPersonSql = "DELETE FROM section WHERE id = ?";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertPersonSql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e)    {
            e.printStackTrace();
        }
    }

    private Optional<Section> mapToSection(ResultSet resultSet) throws SQLException  {
        if(resultSet.next()){
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            return Optional.of(new Section(id, name));
        }
        return Optional.empty();
    }
}
