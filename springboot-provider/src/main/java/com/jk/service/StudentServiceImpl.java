package com.jk.service;

import com.jk.mapper.StudentMapper;
import com.jk.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service("studentService")
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
}
