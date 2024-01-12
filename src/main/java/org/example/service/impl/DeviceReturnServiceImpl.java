package org.example.service.impl;

import org.example.connections.SqlConnection;
import org.example.entity.*;
import org.example.service.DeviceReturnService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

/**
 * create table if not exists device_return (id int not null auto_increment,
 * device_order_id int not null, return_date bigint not null, primary key(id));
 */
public class DeviceReturnServiceImpl implements DeviceReturnService {
    private final DeviceOrderServiceImpl deviceOrderService;
    public DeviceReturnServiceImpl(){
        this.deviceOrderService=new DeviceOrderServiceImpl();
    }

    @Override
    public DeviceReturn addDeviceReturn(DeviceReturn deviceReturn) throws Throwable {
        DeviceOrder deviceOrder=deviceOrderService.getDeviceOrder(deviceReturn.getDeviceOrder().getId());
        if(deviceOrder.getIsOrderReturn()== BaseState.Y)
            throw new Throwable("Order device already returned");
        Connection connection= SqlConnection.getInstance().getConnection(true);
        PreparedStatement preparedStatement=connection.prepareStatement("insert into device_return(device_order_id,return_date) values(?,?);", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1,deviceOrder.getId());
        preparedStatement.setLong(2,System.currentTimeMillis());
        preparedStatement.executeUpdate();
        ResultSet resultSet=preparedStatement.getGeneratedKeys();
        resultSet.next();
        DeviceReturn resultDeviceReturn=new DeviceReturn();
        resultDeviceReturn.setId(resultSet.getInt(1));
        resultSet.close();
        preparedStatement.close();

        // inventory plus
        for (OrderItem orderItem:deviceOrder.getOrderItems()){
            Device device=orderItem.getDevice();
            preparedStatement=connection.prepareStatement("update device set qty=? where id=?;");
            preparedStatement.setInt(1,device.getQty()+orderItem.getQty());
            preparedStatement.setInt(2,device.getId());
            preparedStatement.executeUpdate();
        }

        // order set flag Y
        preparedStatement=connection.prepareStatement("update device_order set is_order_return=? where id=?;");
        preparedStatement.setString(1,BaseState.Y.name());
        preparedStatement.setInt(2,deviceOrder.getId());
        preparedStatement.executeUpdate();
        return getDeviceReturn(resultDeviceReturn.getId());
    }

    @Override
    public DeviceReturn getDeviceReturn(int id) throws Throwable {
        Connection connection= SqlConnection.getInstance().getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement("select * from device_return where id=?;");
        preparedStatement.setInt(1,id);
        ResultSet resultSet=preparedStatement.executeQuery();
        if(!resultSet.next())
            throw new Throwable("Device return not found with id "+id);
        DeviceReturn deviceReturn=new DeviceReturn();
        deviceReturn.setId(id);
        deviceReturn.setReturnDate(new Date(resultSet.getLong(3)));
        deviceReturn.setDeviceOrder(deviceOrderService.getDeviceOrder(resultSet.getInt(2)));
        resultSet.close();
        preparedStatement.close();
        return deviceReturn;
    }
}
