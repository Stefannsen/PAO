package Repositories;

import ConfigDB.DatabaseConfiguration;

import java.sql.*;

public class AuditRepository {
    private  static AuditRepository instance;

    private AuditRepository(){}

    public static AuditRepository getInstance(){
        if(instance == null){
            instance = new AuditRepository();
        }
        return instance;
    }

    public void insert()   {
        String methodName = new Throwable().getStackTrace()[1].getMethodName();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String insertPersonSql = "INSERT INTO audit(functionName, timeStamp) VALUES(?, ?)";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertPersonSql);
            preparedStatement.setString(1, methodName);
//            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            String currentTime = sdf.format(timestamp);
            preparedStatement.setTimestamp(2, timestamp);
            preparedStatement.executeUpdate();
        } catch (SQLException e)    {
            e.printStackTrace();
        }
    }
}
