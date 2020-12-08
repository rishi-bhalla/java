package com.example.demo.student.service;

import com.example.demo.student.model.Course;
import com.example.demo.student.model.Student;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class StudentService {

    private static AtomicInteger courseCounter = new AtomicInteger();
    private static List<Student> students = new ArrayList<>();
    private static Student rishi, varan;
    private static Course course1, course2, course3, course4;

    static {
        //Initialize Data
        course1 = new Course("Course" + courseCounter.incrementAndGet(), "Spring", "10 Steps",
                Arrays.asList("Learn Maven", "Import Project", "First Example", "Second Example"));
        course2 = new Course("Course" + courseCounter.incrementAndGet(), "Spring MVC", "10 Examples",
                Arrays.asList("Learn Maven", "Import Project", "First Example","Second Example"));
        course3 = new Course("Course" + courseCounter.incrementAndGet(), "Spring Boot", "6K Students",
                Arrays.asList("Learn Maven", "Learn Spring", "Learn Spring MVC", "First Example", "Second Example"));
        course4 = new Course("Course" + courseCounter.incrementAndGet(), "Maven", "Most popular maven course on internet!",
                Arrays.asList("Pom.xml", "Build Life Cycle", "Parent POM", "Importing into Eclipse"));

        rishi = new Student("Student1", "Rishi Bhalla", "Hiker, Programmer and Architect",
                new ArrayList<>(Arrays.asList(course1, course2, course3, course4)));

        varan = new Student("Student2", "Varan Bhalla", "Hiker, Programmer and Architect",
                new ArrayList<>(Arrays.asList(course1, course2, course3, course4)));

        students.add(rishi);
        students.add(varan);
    }

    public List<Student> retrieveAllStudents(){
        return students;
    }

    public Student retrieveStudent(String studentId) {
        if(studentId == null)
            return null;
        return students.stream()
                .filter(student -> student.getId().equals(studentId))
                .findFirst()
                .orElse(null);
    }

    public List<Course> retrieveCourses(String studentId) {
        if(studentId == null)
            return null;

        Student student = retrieveStudent(studentId);
        if(student == null)
            return  null;

        return student.getCourses();
    }

    public Course retrieveCourse(String studentId, String courseId) {
        if(studentId == null || courseId == null)
            return null;

        Student student = retrieveStudent(studentId);
        if(student == null)
            return  null;

        return student.getCourses().stream()
                .filter(course -> course.getId().equals(courseId))
                .findFirst()
                .orElse(null);
    }

    public Course addCourse(String studentId, Course course) {
        if(studentId == null || course == null)
            return null;

        Student student = retrieveStudent(studentId);
        if(student == null)
            return  null;

        course.setId("Course" + courseCounter.incrementAndGet());
        student.getCourses().add(course);
        return course;
    }

    public static void main(String[] args) {
        StudentService s =  new StudentService();
        Student student = s.retrieveStudent("1");
        System.out.println(student);
    }
}
