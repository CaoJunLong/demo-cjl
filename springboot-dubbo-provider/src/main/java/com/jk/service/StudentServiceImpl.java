package com.jk.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jk.mapper.StudentMapper;
import com.jk.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(version = "1.0")
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public HashMap<String, Object> queryStudentList(Integer page, Integer rows) {
        int total=studentMapper.querycoynt();
        int start=(page-1)*rows;
        int end=page+rows;
        List<Student> list=studentMapper.queryStudentList(start,end);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("rows", list);
        return map;
    }

    @Override
    public void del(Integer id) {
        studentMapper.del(id);
    }

    @Override
    public void addstudent(Student student) {
        if(student.getSid()==null){
            studentMapper.addstudent(student);
        }else {
            studentMapper.updatestudent(student);
        }


    }

    @Override
    public Student updatebyid(Integer id) {
        return studentMapper.updatebyid(id);
    }

    @Override
    public List<Student> queryStudentsList() {
        return studentMapper.queryStudentsList();
    }

    @Override
    public void addStudent(List<Student> list) {
        studentMapper.addStudent(list);
    }

    @Override
    public List<Student> queryStudentsLists(String[] id) {
        return studentMapper.queryStudentsLists(id);
    }

    @Override
    public List<Map<String, Object>> queryStudent() {
        return studentMapper.queryStudent();
    }

    @Override
    public List<Map<String, Object>> queryCar() {
        return studentMapper.queryCar();
    }

    @Override
    public List<Map<String, Object>> queryCarAll() {
        return studentMapper.queryCarAll();
    }

    @Override
    public List<Map<String, Object>> querymianji() {
        return studentMapper.querymianji();
    }

    @Override
    public List<Map<String, Object>> queryzhuzhuang() {
        return studentMapper.queryzhuzhuang();
    }

}
