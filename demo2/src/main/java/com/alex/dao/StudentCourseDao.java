package com.alex.dao;

import com.alex.domain.Student;

import java.util.Map;

public interface StudentCourseDao {
    public void saveStudentCourses(Map<String, Student> studentCourses);
}
