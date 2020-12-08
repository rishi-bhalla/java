package com.jdbc.demo;

import com.jdbc.demo.model.Student;
import com.jdbc.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SpringBootJDBCDemoApplication implements CommandLineRunner {

    @Autowired
    private StudentService studentService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Start Application...");
        testStudentData();
    }

    private void testStudentData() {
        List<Student> students = Arrays.asList(
                new Student(1, "S1", 11),
                new Student(2, "S2", 12),
                new Student(3, "S3", 13),
                new Student(4, "S4", 14)
        );

        //save
        System.out.println("...Save...\n");
        students.forEach(student -> {
            System.out.println("Saving student with name: " + student.getName() + "\n");
            studentService.save(student);
        });

        //find all
        System.out.println("...Get all students...\n");
        System.out.println(studentService.findAll() + "\n");

        //find by id
        System.out.println("...Find student with id 2...\n");
        Student student = studentService.findById(2).orElseThrow(IllegalArgumentException::new);
        System.out.println("Student with id 2 is: " + student + "\n");

        //update
        System.out.println("...Update name and age of student with id 2...\n");
        student.setName("Updated Student\n");
        student.setAge(21);
        System.out.println("Rows affected: " + studentService.update(student) + "\n");

        //delete
        System.out.println("...Delete student with id 4...\n");
        System.out.println("Rows affected: " + studentService.deleteById(4) + "\n");

        //find all
        System.out.println("...Get updated list of all students...\n");
        System.out.println(studentService.findAll() + "\n");
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootJDBCDemoApplication.class, args);
    }
}
