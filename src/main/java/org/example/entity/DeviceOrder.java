package org.example.entity;

import java.util.Date;
import java.util.List;

/**
 * - id, Student, List<Device>, orderDate, expectedReturnDate,is_order_return;
 */
public class DeviceOrder {
    private int id;
    private Student student;
    private List<OrderItem> orderItems;
    private Date orderDate;
    /**
     * sort overdue
     */
    private Date expectedReturnDate;
    private BaseState isOrderReturn;

    @Override
    public String toString() {
        return "DeviceOrder{" +
                "id=" + id +
                ", student=" + student +
                ", orderItems=" + orderItems +
                ", orderDate=" + orderDate +
                ", expectedReturnDate=" + expectedReturnDate +
                ", isOrderReturn=" + isOrderReturn +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public void setExpectedReturnDate(Date expectedReturnDate) {
        this.expectedReturnDate = expectedReturnDate;
    }

    public BaseState getIsOrderReturn() {
        return isOrderReturn;
    }

    public void setIsOrderReturn(BaseState isOrderReturn) {
        this.isOrderReturn = isOrderReturn;
    }

    public DeviceOrder() {
    }

    public DeviceOrder(int id, Student student, List<OrderItem> orderItems, Date orderDate, Date expectedReturnDate, BaseState isOrderReturn) {
        this.id = id;
        this.student = student;
        this.orderItems = orderItems;
        this.orderDate = orderDate;
        this.expectedReturnDate = expectedReturnDate;
        this.isOrderReturn = isOrderReturn;
    }
}
