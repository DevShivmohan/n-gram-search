package org.example.service;


import org.example.entity.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentService {
    Student addStudent(Student student) throws Throwable;
    Student updateStudent(Student student,int id) throws Throwable;
    Student getStudent(int id) throws Throwable;
    List<Student> getStudents() throws Throwable;
    List<Student> getStudentsByNGramSearch(String keyword) throws Throwable;
    Student getStudentByStudentId(String studentId) throws Throwable;
    Student deleteStudent(int id) throws Throwable;
    boolean isStudentExists(String studentId) throws SQLException;
    boolean isStudentExistsById(int id) throws SQLException;
}
