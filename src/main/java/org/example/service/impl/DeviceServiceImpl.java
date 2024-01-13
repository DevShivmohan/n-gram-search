package org.example.service.impl;


import org.example.connections.SqlConnection;
import org.example.constants.DbConstants;
import org.example.entity.Device;
import org.example.service.DeviceService;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DeviceServiceImpl implements DeviceService {
    @Override
    public Device addDevice(Device device) throws SQLException {
        Connection connection= SqlConnection.getInstance().getConnection();
        device.setCreatedDate(new Date());
        PreparedStatement preparedStatement=connection.prepareStatement("insert into device(name,model_number,qty,created_date) values(?,?,?,?);", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1,device.getName());
        preparedStatement.setString(2,device.getModelNumber());
        preparedStatement.setInt(3,device.getQty());
        preparedStatement.setLong(4,device.getCreatedDate().getTime());
        preparedStatement.executeUpdate();
        ResultSet resultSet= preparedStatement.getGeneratedKeys();
        if(resultSet.next())
            device.setId(resultSet.getInt(1));
        resultSet.close();
        preparedStatement.close();
        return device;
    }

    @Override
    public Device updateDevice(Device device, int deviceId) throws Throwable {
        Connection connection=SqlConnection.getInstance().getConnection();
        if(getDevice(deviceId)==null)
            throw new Throwable("Device not found");
        PreparedStatement preparedStatement=connection.prepareStatement("update device set name=?,model_number=?,qty=? where id=?;");
        preparedStatement.setString(1,device.getName());
        preparedStatement.setString(2,device.getModelNumber());
        preparedStatement.setInt(3,device.getQty());
        preparedStatement.setInt(4,deviceId);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        return getDevice(deviceId);
    }

    @Override
    public Device getDevice(int deviceId) throws SQLException {
        Connection connection=SqlConnection.getInstance().getConnection();
        PreparedStatement preparedStatement= connection.prepareStatement("select * from device where id=?;");
        preparedStatement.setInt(1,deviceId);
        ResultSet resultSet=preparedStatement.executeQuery();
        if(!resultSet.next())
            return null;
        Device device=new Device(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3)
        ,resultSet.getInt(4),new Date(resultSet.getLong(5)));
        resultSet.close();
        preparedStatement.close();
        return device;
    }

    @Override
    public Device deleteDevice(int deviceId) throws Throwable {
        Device device=getDevice(deviceId);
        if(device==null)
            throw new Throwable("Device not found");
        PreparedStatement preparedStatement= SqlConnection.getInstance().getConnection()
                .prepareStatement("delete from device where id=?;");
        preparedStatement.setInt(1,deviceId);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        return device;
    }

    @Override
    public List<Device> getDevices() throws SQLException {
        Connection connection=SqlConnection.getInstance().getConnection();
        PreparedStatement preparedStatement= connection.prepareStatement("select * from device;");
        ResultSet resultSet=preparedStatement.executeQuery();
        final List<Device> devices=new ArrayList<>();
        while (resultSet.next()){
            devices.add(new Device(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3)
                    ,resultSet.getInt(4),new Date(resultSet.getLong(5))));
        }
        resultSet.close();
        preparedStatement.close();
        return devices;
    }

    @Override
    public List<Device> getDevicesByNGramSearch(String keyword) throws SQLException {
        List<Device> devices=new ArrayList<>();
        Connection connection=SqlConnection.getInstance().getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement(DbConstants.QUERY_FOR_N_GRAM_SEARCH_DEVICE);
        preparedStatement.setString(1,keyword);
        preparedStatement.setString(2,keyword);
        ResultSet resultSet=preparedStatement.executeQuery();
        while (resultSet.next()){
            devices.add(getDevice(resultSet.getInt(1)));
        }
        resultSet.close();
        preparedStatement.close();
        return devices;
    }

}
