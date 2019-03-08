package com.example.aop.controller;

import com.example.aop.annotations.ParamCheck;
import com.example.aop.entity.Student;
import com.example.aop.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = "/aop")
public class StudentController {
    @Autowired
    private StudentService studentService;


    @RequestMapping(value = "/getStudentById", method = RequestMethod.GET)
    @ResponseBody
    public Student getStudent(Integer id){
        Student student = studentService.findById(id);
        if(student == null) {
            throw new RuntimeException("查询错误");
        }
        return student;

    }
    @RequestMapping(value = "/getStudentByIdWithAop", method = RequestMethod.GET)
    @ResponseBody
    public Student getStudentByIdWithAop(@ParamCheck Integer id){
        Student student = studentService.findById(id);
        if(student == null) {
            throw new RuntimeException("查询错误");
        }
        return student;

    }

    @RequestMapping(value = "/getStudentByIdWithParam", method = RequestMethod.GET)
    @ResponseBody
    public Student getStudentByIdWithParam(@RequestParam Integer id){
        Student student = studentService.findById(id);
        if(student == null) {
            throw new RuntimeException("查询错误");
        }
        return student;

    }

    @RequestMapping(value = "/getStudentByName", method = RequestMethod.GET)
    @ResponseBody
    public Student getStudentByName(String name){
        Student student = studentService.getStudentByName(name);
        if(student == null) {
            throw new RuntimeException("查询错误");
        }
        return student;

    }
    @RequestMapping(value = "/getStudentByNameWithAop", method = RequestMethod.GET)
    @ResponseBody
    public Student getStudentByNameWithAop(@ParamCheck String name){
        Student student = studentService.getStudentByName(name);
        if(student == null) {
            throw new RuntimeException("查询错误");
        }
        return student;

    }

    @RequestMapping(value = "/getStudentByNameWithParam", method = RequestMethod.GET)
    @ResponseBody
    public Student getStudentByNameWithParam(@RequestParam String name){
        Student student = studentService.getStudentByName(name);
        if(student == null) {
            throw new RuntimeException("查询错误");
        }
        return student;

    }



}
