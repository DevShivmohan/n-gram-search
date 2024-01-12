package org.example.service.impl;

import org.example.connections.SqlConnection;
import org.example.entity.*;
import org.example.service.DeviceOrderService;
import org.example.service.DeviceService;
import org.example.service.StudentService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DeviceOrderServiceImpl implements DeviceOrderService {
    private final StudentService studentService;
    private final DeviceService deviceService;

    public DeviceOrderServiceImpl() {
        studentService = new StudentServiceImpl();
        deviceService = new DeviceServiceImpl();
    }

    @Override
    public DeviceOrder addDeviceOrder(DeviceOrder deviceOrder) throws Throwable {
        Connection connection = SqlConnection.getInstance().getConnection(true);
        Student student = studentService.getStudent(deviceOrder.getStudent().getId());
        for (OrderItem orderItem : deviceOrder.getOrderItems()) {
            Device dbDevice= deviceService.getDevice(orderItem.getDevice().getId());
            if(dbDevice.getQty()!=0 && orderItem.getQty()>dbDevice.getQty() && (dbDevice.getQty()-orderItem.getQty())<0)
                throw new Throwable("Device not available with quantity "+orderItem.getQty());
            dbDevice.setQty(dbDevice.getQty() - orderItem.getQty());
            deviceService.updateDevice(dbDevice, dbDevice.getId());
        }

        PreparedStatement preparedStatement = connection.prepareStatement("insert into device_order(stu_id,order_date,expected_return_date,is_order_return) values(?,?,?,?);", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, student.getId());
        preparedStatement.setLong(2, System.currentTimeMillis());
        preparedStatement.setLong(3, System.currentTimeMillis() + TimeUnit.DAYS.toMillis(7));
        preparedStatement.setString(4, BaseState.N.name());
        preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        if (resultSet.next())
            deviceOrder.setId(resultSet.getInt(1));
        resultSet.close();
        preparedStatement.close();
        for (OrderItem orderItem:deviceOrder.getOrderItems()) {
            PreparedStatement preparedStatement1 = connection.prepareStatement("insert into order_item(device_order_id,qty,device_id) values(?,?,?);");
            preparedStatement1.setInt(1, deviceOrder.getId());
            preparedStatement1.setInt(2, orderItem.getQty());
            preparedStatement1.setInt(3, orderItem.getDevice().getId());
            preparedStatement1.executeUpdate();
            preparedStatement1.close();
        }
        return getDeviceOrder(deviceOrder.getId());
    }

    @Override
    public DeviceOrder getDeviceOrder(int id) throws Throwable {
        Connection connection = SqlConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from device_order where id=?;");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (!resultSet.next())
            throw new Throwable("Device order not found");
        DeviceOrder deviceOrder = new DeviceOrder();
        Student student = new Student();
        deviceOrder.setStudent(student);
        deviceOrder.setId(id);
        deviceOrder.setOrderDate(new Date(resultSet.getLong(3)));
        student.setId(resultSet.getInt(5));
        deviceOrder.setExpectedReturnDate(new Date(resultSet.getLong(3)));
        deviceOrder.setIsOrderReturn(BaseState.valueOf(resultSet.getString(4)));
        List<OrderItem> orderItems=new ArrayList<>();
        deviceOrder.setOrderItems(orderItems);
        PreparedStatement preparedStatement2=connection.prepareStatement("select device_order_id,qty,device_id from order_item where device_order_id=?;");
        preparedStatement2.setInt(1,deviceOrder.getId());
        ResultSet resultSet1=preparedStatement2.executeQuery();
        while (resultSet1.next()){
            orderItems.add(new OrderItem(resultSet1.getInt(1),resultSet1.getInt(2),
                    new Device(resultSet1.getInt(3),null,null,0,null)));
        }
        for (OrderItem orderItem:orderItems){
            Device device=orderItem.getDevice();
            PreparedStatement preparedStatement1=connection.prepareStatement("select id,name,model_number,qty,created_date from device where id=?;");
            preparedStatement1.setInt(1,device.getId());
            resultSet1=preparedStatement1.executeQuery();
            if(resultSet1.next()){
                device.setName(resultSet1.getString(2));
                device.setModelNumber(resultSet1.getString(3));
                device.setQty(resultSet1.getInt(4));
                device.setCreatedDate(new Date(resultSet1.getLong(5)));
            }
        }


        resultSet1.close();
        preparedStatement2=connection.prepareStatement("select * from student where id=?;");
        preparedStatement2.setInt(1,student.getId());
        resultSet1=preparedStatement2.executeQuery();
        resultSet1.next();
        student.setStudentId(resultSet1.getString(2));
        student.setName(resultSet1.getString(3));
        student.setEmail(resultSet1.getString(4));
        student.setGrade(resultSet1.getString(5));
        student.setJoinedDate(new Date(resultSet1.getLong(6)));
        resultSet1.close();
        preparedStatement2.close();
        return deviceOrder;
    }
}
