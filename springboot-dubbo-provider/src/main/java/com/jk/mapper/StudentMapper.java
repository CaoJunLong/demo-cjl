package com.jk.mapper;

import com.jk.model.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StudentMapper {
    int querycoynt();

    List<Student> queryStudentList(@Param("start") int start, @Param("end") int end);

    void del(Integer id);

    void addstudent(Student student);

    Student updatebyid(Integer id);

    void updatestudent(Student student);

    List<Student> queryStudentsList();

    void addStudent(List<Student> list);

    List<Student> queryStudentsLists(@Param("id") String[] id);

    List<Map<String, Object>> queryStudent();

    List<Map<String, Object>> queryCar();

    List<Map<String, Object>> queryCarAll();

    List<Map<String, Object>> querymianji();

    List<Map<String, Object>> queryzhuzhuang();
}
