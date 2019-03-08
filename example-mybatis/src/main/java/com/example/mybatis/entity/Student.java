package com.example.mybatis.entity;

public class Student {
    private Integer id;
    private String name;
    private String age;

    public Student(){}

    public Integer getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getAge(){
        return age;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public void  setName(String name){
        this.name = name;
    }

    public void setAge(String age){
        this.age = age;
    }
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }

}
