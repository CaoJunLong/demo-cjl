package com.jk.mapper;

import com.jk.model.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {
    int querycoynt();

    List<Student> queryStudentList(@Param("start")int start, @Param("end")int end);

    void del(Integer id);

    void addstudent(Student student);

    Student updatebyid(Integer id);

    void updatestudent(Student student);
}
