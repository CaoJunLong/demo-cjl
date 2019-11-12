package com.jk.model;

import java.io.Serializable;

public class Student implements Serializable {

    private static final long serialVersionUID = 8896071322549116551L;
    private Integer sid;
    private String studentName;
    private Integer sex;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sid=" + sid +
                ", studentName='" + studentName + '\'' +
                ", sex=" + sex +
                '}';
    }
}
