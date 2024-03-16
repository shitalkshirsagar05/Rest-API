//
//package com.InfoStack.schoolmanagement.controller;
//import com.InfoStack.schoolmanagement.models.Teacher;
//import com.InfoStack.schoolmanagement.services.TeacherService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//
//
//@Controller
//@RequestMapping("/teacher")
//public class TeacherController {
//    @Autowired
//    TeacherService tservice;
//    @RequestMapping("/registration")
//    public String teacherResistration(){
//        return "teacher-registration";
//    }
//    @RequestMapping("/save")
//    public String saveTeacher(@RequestParam("fullname")String fullname,
//                              @RequestParam("salary") String salary,
//                              @RequestParam("subject") String subject) {
//        Teacher t = new Teacher();
//        t.setTeacherName(fullname);
//        t.setTeacherSalary(salary);
//        t.setTeacherSubject(subject);
//
//        tservice.saveData(t);
//        return "teacher-registration";
//    }
//}
package com.InfoStack.schoolmanagement.controller;

import com.InfoStack.schoolmanagement.models.Teacher;
import com.InfoStack.schoolmanagement.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherRepository teacherRepository;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("teacher", new Teacher());
        return "teacher-registration";
    }

    @PostMapping("/save")
    public String saveTeacher(@ModelAttribute("teacher") Teacher teacher) {
        teacherRepository.save(teacher);
        return "redirect:/teacher/list";
    }

    @GetMapping("/list")
    public String showTeacherList(Model model) {
        model.addAttribute("teachers", teacherRepository.findAll());
        return "teacher-list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid teacher Id:" + id));
        model.addAttribute("teacher", teacher);
        return "edit-teacher";
    }

    @PostMapping("/update/{id}")
    public String updateTeacher(@PathVariable("id") int id, @ModelAttribute("teacher") Teacher teacher, Model model) {
        teacher.setTeacherId(id);
        teacherRepository.save(teacher);
        model.addAttribute("teachers", teacherRepository.findAll());
        return "redirect:/teacher/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteTeacher(@PathVariable("id") int id, Model model) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid teacher Id:" + id));
        teacherRepository.delete(teacher);
        model.addAttribute("teachers", teacherRepository.findAll());
        return "redirect:/teacher/list";
    }
}