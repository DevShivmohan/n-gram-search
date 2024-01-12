package org.example.entity;

import java.util.Date;

/**
 * create table if not exists device_return (id int not null auto_increment,
 * device_order_id int not null, return_date bigint not null, primary key(id));
 */
public class DeviceReturn {
    private int id;
    private DeviceOrder deviceOrder;
    private Date returnDate;

    public DeviceReturn() {
    }

    public DeviceReturn(int id, DeviceOrder deviceOrder, Date returnDate) {
        this.id = id;
        this.deviceOrder = deviceOrder;
        this.returnDate = returnDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DeviceOrder getDeviceOrder() {
        return deviceOrder;
    }

    public void setDeviceOrder(DeviceOrder deviceOrder) {
        this.deviceOrder = deviceOrder;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "DeviceReturn{" +
                "id=" + id +
                ", deviceOrder=" + deviceOrder +
                ", returnDate=" + returnDate +
                '}';
    }
}
