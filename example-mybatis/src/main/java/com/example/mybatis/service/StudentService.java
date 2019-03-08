package com.example.mybatis.service;

import com.example.mybatis.entity.Student;
import com.example.mybatis.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "studentService")
public class StudentService {

    @Autowired
    StudentMapper studentMapper;

    public Student findById(Integer id){
        return studentMapper.findById(id);
    }


}

