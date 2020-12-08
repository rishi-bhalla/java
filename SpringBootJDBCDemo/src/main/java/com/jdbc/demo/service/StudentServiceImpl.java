package com.jdbc.demo.service;

import com.jdbc.demo.model.Student;
import com.jdbc.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public int save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public int update(Student student) {
        return studentRepository.update(student);
    }

    @Override
    public int deleteById(long id) {
        return studentRepository.deleteById(id);
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> findById(long id) {
        return studentRepository.findById(id);
    }
}
