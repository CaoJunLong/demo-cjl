package com.jk.service;

import com.jk.model.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface StudentService {
    HashMap<String, Object> queryStudentList(Integer page, Integer rows);

    void del(Integer id);

    void addstudent(Student student);
    Student updatebyid(Integer id);

    List<Student> queryStudentsList();


    void addStudent(List<Student> list);

    List<Student> queryStudentsLists(String[] id);

    List<Map<String, Object>> queryStudent();

    List<Map<String, Object>> queryCar();

    List<Map<String, Object>> queryCarAll();

    List<Map<String, Object>> querymianji();

    List<Map<String, Object>> queryzhuzhuang();
}
