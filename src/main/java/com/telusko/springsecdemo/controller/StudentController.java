package com.telusko.springsecdemo.controller;

import com.telusko.springsecdemo.model.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    List<Student> students = new ArrayList<>(
            List.of(
                    new Student(1, "Alice", "Java"),
                    new Student(2, "Bob", "Python"),
                    new Student(3, "Charlie", "JavaScript")
            )
    );

    @GetMapping("/students")
    public List<Student> getStudents() {
        return students;
    }
//Sending CSRF Token
    @GetMapping("csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request) {
       return (CsrfToken) request.getAttribute("_csrf");
   }

    @PostMapping("/student")
    public void add(Student student){
        students.add(student);
    }

}
