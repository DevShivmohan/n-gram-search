package org.example.constants;

public class DbConstants {
    public static final String STUDENT_TABLE_CREATION="create table if not exists student(id int not null auto_increment,stu_id varchar(20) not null unique,\n" +
            "name varchar(30) not null,email varchar(50),grade varchar(15), joined_date bigint not null,primary key (id));";
    public static final String DEVICE_TABLE_CREATION="create table if not exists device(id int not null AUTO_INCREMENT,name varchar(20) not null,model_number varchar(14) not null,qty int not null,created_date bigint not null,PRIMARY KEY (id));";
    public static final String DEVICE_ORDER_TABLE_CREATION="create table if not exists device_order (id int not null auto_increment, stu_id int not null, order_date bigint not null,\n" +
            " expected_return_date bigint not null, is_order_return enum ('Y','N'),primary key(id) );";
    public static final String DEVICE_ORDER_MAP_TABLE_CREATION="drop table if exists device_order_map;";
    public static final String DEVICE_RETURN_TABLE_CREATION="create table if not exists device_return (id int not null auto_increment, device_order_id int not null, return_date bigint not null, primary key(id));";
    public static final String DEVICE_ORDER_ITEM_TABLE_CREATION="create table if not exists order_item(device_order_id int not null,qty int not null,device_id int not null);";
}
