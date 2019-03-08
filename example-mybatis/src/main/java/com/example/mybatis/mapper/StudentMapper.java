package com.example.mybatis.mapper;

import com.example.mybatis.entity.Student;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentMapper {
    Student findById(Integer id);
    //int insert(Student record);
}
