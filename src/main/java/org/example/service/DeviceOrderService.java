package org.example.service;


import org.example.entity.DeviceOrder;

import java.util.List;

public interface DeviceOrderService {
    DeviceOrder addDeviceOrder(DeviceOrder deviceOrder) throws Throwable;
    DeviceOrder getDeviceOrder(int id) throws Throwable;
    List<DeviceOrder> getOverDueOrders() throws Throwable;
}
