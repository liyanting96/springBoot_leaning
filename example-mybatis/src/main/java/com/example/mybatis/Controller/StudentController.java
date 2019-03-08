package com.example.mybatis.Controller;

import com.example.mybatis.entity.Student;
import com.example.mybatis.service.StudentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = "/mabatis")
public class StudentController {
    @Autowired
    private StudentService studentService;


    @RequestMapping(value = "/getStudentById", method = RequestMethod.GET)
    @ResponseBody
    public Student getStudent(@Param("id") Integer id){
        Student user = studentService.findById(id);
        if(user == null) {
            throw new RuntimeException("查询错误");
        }
        return user;

    }

}
