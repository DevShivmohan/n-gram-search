package org.example.connections;


import org.example.constants.DbConstants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlConnection {
    private static SqlConnection sqlConnection;
    private final Connection connection;
    public Connection getConnection(boolean ...transaction) throws SQLException {
        return connection;
    }

    public static SqlConnection getInstance() throws SQLException {
        if(sqlConnection ==null)
            sqlConnection=new SqlConnection();
        return sqlConnection;
    }

     private SqlConnection() throws SQLException {
         connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/it","root","root");
         PreparedStatement preparedStatement= connection.prepareStatement(DbConstants.DEVICE_ORDER_MAP_TABLE_CREATION);
         preparedStatement.executeUpdate();
         preparedStatement= connection.prepareStatement(DbConstants.DEVICE_TABLE_CREATION);
         preparedStatement.executeUpdate();
         preparedStatement= connection.prepareStatement(DbConstants.STUDENT_TABLE_CREATION);
         preparedStatement.executeUpdate();
         preparedStatement= connection.prepareStatement(DbConstants.DEVICE_RETURN_TABLE_CREATION);
         preparedStatement.executeUpdate();
         preparedStatement= connection.prepareStatement(DbConstants.DEVICE_ORDER_TABLE_CREATION);
         preparedStatement.executeUpdate();
         preparedStatement= connection.prepareStatement(DbConstants.DEVICE_ORDER_ITEM_TABLE_CREATION);
         preparedStatement.executeUpdate();
         preparedStatement.close();
     }

}
