package com.jdbc.demo.repository;

import com.jdbc.demo.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StudentRepositoryImpl implements StudentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Student student) {
        return jdbcTemplate.update("insert into Student (name, age) values (?, ?)", student.getName(), student.getAge());
    }

    @Override
    public int update(Student student) {
        return jdbcTemplate.update("update Student set age = ?, name = ? where id = ?", student.getAge(), student.getName(), student.getId());
    }

    @Override
    public int deleteById(long id) {
        return jdbcTemplate.update("delete from Student where id = ?", id);
    }

    @Override
    public List<Student> findAll() {
        return jdbcTemplate.query(
                "select * from Student",
                    (rs, rowNum) ->
                        new Student(
                                rs.getLong("id"),
                                rs.getString("name"),
                                rs.getInt("age")
                        ));
    }

    @Override
    public Optional<Student> findById(long id) {
        return jdbcTemplate.queryForObject(
                "select * from Student where id = ?",
                    new Object[]{id},
                        (rs, rowNum) ->
                            Optional.of(new Student(
                                    rs.getLong("id"),
                                    rs.getString("name"),
                                    rs.getInt("age")
                            ))
        );
    }
}
