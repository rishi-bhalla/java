package com.jdbc.demo.service;

import com.jdbc.demo.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    int save (Student student);
    int update(Student student);
    int deleteById(long id);
    List<Student> findAll();
    Optional<Student> findById(long id);

}
