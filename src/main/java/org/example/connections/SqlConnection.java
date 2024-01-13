package org.example.connections;


import org.example.constants.DbConstants;

import java.sql.*;

public class SqlConnection {
    private static SqlConnection sqlConnection;
    private final Connection connection;

    public Connection getConnection(boolean... transaction) throws SQLException {
        return connection;
    }

    public static SqlConnection getInstance() throws SQLException {
        if (sqlConnection == null)
            sqlConnection = new SqlConnection();
        return sqlConnection;
    }

    private SqlConnection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/it", "root", "root");
        PreparedStatement preparedStatement = connection.prepareStatement(DbConstants.DEVICE_ORDER_MAP_TABLE_CREATION);
        preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement(DbConstants.DEVICE_TABLE_CREATION);
        preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement(DbConstants.STUDENT_TABLE_CREATION);
        preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement(DbConstants.DEVICE_RETURN_TABLE_CREATION);
        preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement(DbConstants.DEVICE_ORDER_TABLE_CREATION);
        preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement(DbConstants.DEVICE_ORDER_ITEM_TABLE_CREATION);
        preparedStatement.executeUpdate();
        if(!checkIfStudentIndexExists("ft_student_index")){
            preparedStatement=connection.prepareStatement(DbConstants.QUERY_FOR_FULLTEXT_INDEX_STUDENT);
            preparedStatement.executeUpdate();
        }
        if(!checkIfDeviceIndexExists("ft_device_index")){
            preparedStatement=connection.prepareStatement(DbConstants.QUERY_FOR_FULLTEXT_INDEX_DEVICE);
            preparedStatement.executeUpdate();
        }
        preparedStatement.close();
    }

    private boolean checkIfStudentIndexExists(String indexName) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DbConstants.QUERY_FOR_CHECK_STUDENT_INDEX);
        preparedStatement.setString(1, indexName);
        ResultSet resultSet = preparedStatement.executeQuery();
        boolean status=resultSet.next();
        resultSet.close();
        preparedStatement.close();
        return status;
    }

    private boolean checkIfDeviceIndexExists(String indexName) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DbConstants.QUERY_FOR_CHECK_DEVICE_INDEX);
        preparedStatement.setString(1, indexName);
        ResultSet resultSet = preparedStatement.executeQuery();
        boolean status=resultSet.next();
        resultSet.close();
        preparedStatement.close();
        return status;
    }

}
