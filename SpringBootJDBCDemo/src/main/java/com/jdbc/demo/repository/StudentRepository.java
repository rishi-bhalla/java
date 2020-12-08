package com.jdbc.demo.repository;

import com.jdbc.demo.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {

    int save (Student student);
    int update(Student student);
    int deleteById(long id);
    List<Student> findAll();
    Optional<Student> findById(long id);

}
