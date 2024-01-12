package org.example;

import org.example.entity.*;
import org.example.service.DeviceOrderService;
import org.example.service.DeviceReturnService;
import org.example.service.impl.DeviceOrderServiceImpl;
import org.example.service.impl.DeviceReturnServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class ITManagementApp {
    public static void main(String[] args) throws Throwable {
//                Student student=new Student();
//        student.setName("Ayush");
//        student.setStudentId("AYUS3H122135");
//        student.setEmail("ayush@dfjh");
//        student.setGrade("123");
//        StudentService studentService=new StudentServiceImpl();
//        Student student1= studentService.addStudent(student);
//        System.out.println(student1);
//        studentService.getStudents().forEach(System.out::println);
//        studentService.getStudents().forEach(System.out::println);
//        Device device=new Device(0,"Mobile","FTRE24536",4,null);
//        DeviceService deviceService=new DeviceServiceImpl();
//        Device device1= deviceService.addDevice(device);
//        System.out.println(device1);
//        Scanner scanner=new Scanner(System.in);
//        System.out.println("Enter any String: ");
//        String enteredString=scanner.nextLine();
//        System.out.println("Entered text is : "+enteredString);
//        DeviceService deviceService=new DeviceServiceImpl();
//        Device device=new Device(0,"Mobile","GTX343",2,new Date());
//        deviceService.deleteDevice(1);
//        List<Device> result= deviceService.getDevices();
//        System.out.println(result);
//        DeviceOrderService deviceOrderService=new DeviceOrderServiceImpl();
//        DeviceOrder deviceOrder=new DeviceOrder();
//        Student student=new Student();
//        List<OrderItem> orderItems=new ArrayList<OrderItem>();
////        orderItems.add(new OrderItem(0,1,new Device(2,null,null,0,null)));
//        orderItems.add(new OrderItem(0,2,new Device(3,null,null,0,null)));
//        student.setId(4);
//        deviceOrder.setStudent(student);
//        deviceOrder.setOrderItems(orderItems);
//        DeviceOrder result= deviceOrderService.addDeviceOrder(deviceOrder);
//        System.out.println(result);
        DeviceReturnService deviceReturnService=new DeviceReturnServiceImpl();
        DeviceReturn deviceReturn=new DeviceReturn();
        DeviceOrder deviceOrder=new DeviceOrder();
        deviceOrder.setId(1);
        deviceReturn.setDeviceOrder(deviceOrder);
        DeviceReturn result= deviceReturnService.addDeviceReturn(deviceReturn);
        System.out.println(result);
    }
}
