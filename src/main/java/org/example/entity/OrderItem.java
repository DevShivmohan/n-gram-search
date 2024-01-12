package org.example.entity;

/**
 * create table order_item(id int not null auto_increment,qty int not null,device_id int not null,primary key(id));
 */
public class OrderItem {
    private int id;
    private int qty;
    private Device device;

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", qty=" + qty +
                ", device=" + device +
                '}';
    }

    public OrderItem() {
    }

    public OrderItem(int id, int qty, Device device) {
        this.id = id;
        this.qty = qty;
        this.device = device;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }
}
