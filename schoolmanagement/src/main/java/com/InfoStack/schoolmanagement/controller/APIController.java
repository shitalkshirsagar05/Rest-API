package com.InfoStack.schoolmanagement.controller;

import com.InfoStack.schoolmanagement.models.Student;
import com.InfoStack.schoolmanagement.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class APIController {
    @Autowired
    StudentService service;
    @RequestMapping("/student/api/save")
    public Student saveStudent(@RequestParam("name") String name, @RequestParam("mobileno") String mobileno, @RequestParam("city") String city) {
        Student s = new Student(name, city,mobileno);
        return service.saveData(s);
    }

    @RequestMapping("student/api/get/{rollno}")
    public Student getStudent(@PathVariable("rollno") int rollno) {
        Student s = service.getStudentDetails(rollno);
        return s;
    }

    @RequestMapping("student/api/getall")
    public List<Student> getStudent() {
        return service.getAllStudents();
    }

    @RequestMapping("student/api/delete/{rollno}")
    public void deleteStudent(@PathVariable("rollno") int rollno) {
        service.deleteStudent(rollno);
    }
}
