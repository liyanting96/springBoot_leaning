package com.example.jpa.Controller;

import com.example.jpa.Entity.Student;
import com.example.jpa.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "/jpa")
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
    @RequestMapping(value = "/queryStudentById", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> queryStudent(Integer id){
       return studentService.queryStudent();

    }

    @RequestMapping(value = "/getStudentByNameWithJson", method = RequestMethod.POST)
    @ResponseBody
    public Student getStudentByNameWithJson(HttpServletRequest httpServletRequest, @RequestBody Map<String, String> param){
        System.out.println(httpServletRequest.getRequestURI() + "param: " + httpServletRequest.getQueryString());
        String name = param.get("name");
        Student student = studentService.getStudentByName(name);
        System.out.println("secces");
        if(student == null) {
            throw new RuntimeException("查询错误");
        }
        return student;

    }
    @RequestMapping(value = "/getStudentByName", method = RequestMethod.POST)
    @ResponseBody
    public Student getStudentByName(String name){
        Student student = studentService.getStudentByName(name);
        if(student == null) {
            throw new RuntimeException("查询错误");
        }
        return student;

    }

}
