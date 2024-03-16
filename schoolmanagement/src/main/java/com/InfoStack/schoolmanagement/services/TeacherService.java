package com.InfoStack.schoolmanagement.services;


import com.InfoStack.schoolmanagement.models.Teacher;
import com.InfoStack.schoolmanagement.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
    @Autowired
    TeacherRepository tr;
    public Teacher saveData(Teacher t) {
        return tr.save(t);

    }
}
