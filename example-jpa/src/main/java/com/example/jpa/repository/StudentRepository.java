package com.example.jpa.repository;

import com.example.jpa.Entity.Student;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface StudentRepository extends JpaRepository<Student, Integer> {
     List<Student> findAll();

    List<Student> findAll(Sort sort);


//    @Query("from Student where id = :id")
//    Student getById(@Param("id") Integer id);

    //Student findStudentById(int id);
    Student getById(Integer id);

    @Query("SELECT s FROM Student s WHERE s.name = ?1")
    Student getStudentByName(String name);
    @Query(value = "SELECT `name`, age, DATE_FORMAT(`time`, '%Y-%m-%d %H:%i:%s') as createtime FROM t_student", nativeQuery = true)
    List<Map<String, Object>> queryStudentByName();

    @Modifying
    @Query("UPDATE Student s set s.age = :age where s.id = :id")
    void updateStudent(@Param("age") String age, @Param("id") Integer id);

    @Modifying
    @Query(value = "INSERT t_student s(s.name, s.age) VALUES(?1, ?2)", nativeQuery = true)
    int insertStudent(String name, String age);

    //void save(Student student);
}


