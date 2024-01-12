package org.example.service;


import org.example.entity.DeviceOrder;

public interface DeviceOrderService {
    DeviceOrder addDeviceOrder(DeviceOrder deviceOrder) throws Throwable;
    DeviceOrder getDeviceOrder(int id) throws Throwable;
}
