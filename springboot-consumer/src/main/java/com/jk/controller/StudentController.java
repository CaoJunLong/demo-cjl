package com.jk.controller;

import com.jk.model.Student;
import com.jk.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping("toshow")
    public String toshow(){
        return "show";
    }


    @RequestMapping("queryStudentList")
    @ResponseBody
    public HashMap<String,Object> queryStudentList(Integer page,Integer rows){
      return  studentService.queryStudentList(page,rows);
    }

    @RequestMapping("del")
    @ResponseBody
    public void del (Integer id){
        studentService.del(id);
    }

    @RequestMapping("addstudent")
    @ResponseBody
    public void addstudent(Student student){
        studentService.addstudent(student);
    }

    @RequestMapping("updatebyid")
    @ResponseBody
    public Student updatebyid(Integer id){
        return studentService.updatebyid(id);
    }


}
