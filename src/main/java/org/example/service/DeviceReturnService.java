package org.example.service;


import org.example.entity.DeviceReturn;

public interface DeviceReturnService {
    DeviceReturn addDeviceReturn(DeviceReturn deviceReturn) throws Throwable;
    DeviceReturn getDeviceReturn(int id) throws Throwable;
}
