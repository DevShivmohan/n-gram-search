package org.example.service;


import org.example.entity.Device;

import java.sql.SQLException;
import java.util.List;

public interface DeviceService {
    Device addDevice(Device device) throws SQLException;
    Device updateDevice(Device device,int deviceId) throws Throwable;
    Device getDevice(int deviceId) throws SQLException;
    Device deleteDevice(int deviceId) throws Throwable;
    List<Device> getDevices() throws SQLException;
    List<Device> getDevicesByNGramSearch(String keyword) throws SQLException;
}
