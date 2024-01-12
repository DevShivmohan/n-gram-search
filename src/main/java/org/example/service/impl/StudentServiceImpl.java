package org.example.service.impl;


import org.example.connections.SqlConnection;
import org.example.entity.Student;
import org.example.service.StudentService;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentServiceImpl implements StudentService {
    @Override
    public Student addStudent(Student student) throws Throwable {
        if(isStudentExists(student.getStudentId()))
            throw new Throwable("Student already exists with id "+student.getStudentId());
        student.setJoinedDate(new Date());
        Connection connection= SqlConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement=connection.prepareStatement("insert into student(stu_id,name,email,grade,joined_date) values(?,?,?,?,?);", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1,student.getStudentId());
        preparedStatement.setString(2,student.getName());
        preparedStatement.setString(3,student.getEmail());
        preparedStatement.setString(4,student.getGrade());
        preparedStatement.setLong(5,student.getJoinedDate().getTime());
        preparedStatement.executeUpdate();
        ResultSet resultSet= preparedStatement.getGeneratedKeys();
        if(resultSet.next())
            student.setId(resultSet.getInt(1));
        connection.commit();
        resultSet.close();
        preparedStatement.close();
        return student;
    }

    @Override
    public Student updateStudent(Student student, int id) throws Throwable {
        if(!isStudentExistsById(id))
            throw new Throwable("Student does not exists with id "+id);
        Connection connection= SqlConnection.getInstance().getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement("update student set stu_id=?,name=?,email=?,grade=? where id=?;");
        preparedStatement.setString(1,student.getStudentId());
        preparedStatement.setString(2,student.getName());
        preparedStatement.setString(3,student.getEmail());
        preparedStatement.setString(4,student.getGrade());
        preparedStatement.setInt(5,id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        return getStudent(id);
    }

    @Override
    public Student getStudent(int id) throws Throwable {
        Connection connection=SqlConnection.getInstance().getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement("select * from student where id=?;");
        preparedStatement.setInt(1,id);
        ResultSet resultSet= preparedStatement.executeQuery();
        if(!resultSet.next())
            throw new Throwable("Student not found with id "+id);
        Student student=new Student();
        student.setId(resultSet.getInt(1));
        student.setStudentId(resultSet.getString(2));
        student.setName(resultSet.getString(3));
        student.setEmail(resultSet.getString(4));
        student.setGrade(resultSet.getString(5));
        student.setJoinedDate(new Date(resultSet.getLong(6)));
        resultSet.close();
        preparedStatement.close();
        return student;
    }

    @Override
    public List<Student> getStudents() throws Throwable {
        Connection connection=SqlConnection.getInstance().getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement("select * from student;");
        ResultSet resultSet= preparedStatement.executeQuery();
        List<Student> students=new ArrayList<>();
        while (resultSet.next()){
            Student student=new Student();
            student.setId(resultSet.getInt(1));
            student.setStudentId(resultSet.getString(2));
            student.setName(resultSet.getString(3));
            student.setEmail(resultSet.getString(4));
            student.setGrade(resultSet.getString(5));
            student.setJoinedDate(new Date(resultSet.getLong(6)));
            students.add(student);
        }
        resultSet.close();
        preparedStatement.close();
        return students;
    }

    @Override
    public Student getStudentByStudentId(String studentId) throws Throwable {
        Connection connection=SqlConnection.getInstance().getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement("select * from student where stu_id=?;");
        preparedStatement.setString(1,studentId);
        ResultSet resultSet= preparedStatement.executeQuery();
        if(!resultSet.next())
            throw new Throwable("Student not found with studentId "+studentId);
        Student student=new Student();
        student.setId(resultSet.getInt(1));
        student.setStudentId(resultSet.getString(2));
        student.setName(resultSet.getString(3));
        student.setEmail(resultSet.getString(4));
        student.setGrade(resultSet.getString(5));
        student.setJoinedDate(new Date(resultSet.getLong(6)));
        resultSet.close();
        preparedStatement.close();
        return student;
    }

    @Override
    public Student deleteStudent(int id) throws Throwable {
        if(!isStudentExistsById(id))
            throw new Throwable("Student does not exists by id "+id);
        Student student=getStudent(id);
        Connection connection=SqlConnection.getInstance().getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement("delete from student where id=?;");
        preparedStatement.setInt(1,id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        return student;
    }

    @Override
    public boolean isStudentExists(String studentId) throws SQLException {
        Connection connection=SqlConnection.getInstance().getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement("select * from student where stu_id=?;");
        preparedStatement.setString(1,studentId);
        ResultSet resultSet=preparedStatement.executeQuery();
        boolean existsStatus=resultSet.next();
        resultSet.close();
        preparedStatement.close();
        return existsStatus;
    }

    @Override
    public boolean isStudentExistsById(int id) throws SQLException {
        Connection connection=SqlConnection.getInstance().getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement("select * from student where id=?;");
        preparedStatement.setInt(1,id);
        ResultSet resultSet=preparedStatement.executeQuery();
        boolean existsStatus=resultSet.next();
        resultSet.close();
        preparedStatement.close();
        return existsStatus;
    }

}
