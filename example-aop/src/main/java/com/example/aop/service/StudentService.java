package com.example.aop.service;

import com.example.aop.entity.Student;
import com.example.aop.exception.GlobalExceptionHandler;
import com.example.aop.exception.SqlException;
import com.example.aop.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service(value = "studentService")
public class StudentService {

    private static Logger LOGGER = LoggerFactory.getLogger("studentServiceLog");
    @Autowired
    StudentRepository studentRepository;

    public Student findById(Integer id){
        return studentRepository.getById(id);
    }

    public Student getStudentByName(String name){
        return studentRepository.getStudentByName(name);
    }

    public void insertStudent(String name, String age){
        //studentRepository.insertStudent(name, age);
        try {
             studentRepository.insertStudent(name, age);
        }catch (DataAccessException e){
            LOGGER.error("insertStudent catch DataAccessException: " + e);
            throw new SqlException();
        }
    }


}

