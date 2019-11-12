package com.jk.service;

import com.jk.model.Student;

import java.util.HashMap;

public interface StudentService {
    HashMap<String, Object> queryStudentList(Integer page, Integer rows);

    void del(Integer id);

    void addstudent(Student student);
    Student updatebyid(Integer id);
}
