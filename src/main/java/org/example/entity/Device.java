package org.example.entity;

import java.util.Date;

public class Device {
    private int id;
    private String name;
    private String modelNumber;
    private int qty;
    private Date createdDate;

    public Device() {
    }

    public Device(int id, String name, String modelNumber, int qty, Date createdDate) {
        this.id = id;
        this.name = name;
        this.modelNumber = modelNumber;
        this.qty = qty;
        this.createdDate = createdDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", modelNumber='" + modelNumber + '\'' +
                ", qty=" + qty +
                ", createdDate=" + createdDate +
                '}';
    }
}
