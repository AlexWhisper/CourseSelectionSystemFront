package com.alex.domain;

public class Student extends Person {

    private String major;
    private String degree;

    public Student(String name, String sid, String major, String degree) {
        super(name, sid);

        this.setMajor(major);
        this.setDegree(degree);
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getMajor() {
        return major;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getDegree() {
        return degree;
    }

    @Override
    public String toString() {
        return getName()+"（"+getId()+"）"+"["+getDegree()+"-"+getMajor()+"]";
    }
}