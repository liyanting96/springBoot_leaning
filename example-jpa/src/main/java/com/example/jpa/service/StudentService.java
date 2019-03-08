package com.example.jpa.service;

import com.example.jpa.entity.Student;
import com.example.jpa.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "studentService")
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public Student findById(Integer id){
        return studentRepository.getById(id);
    }

    public Student getStudentByName(String name){ return studentRepository.getStudentByName(name);}


}

