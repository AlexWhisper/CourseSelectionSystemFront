package com.alex.dao.impl.literal;

import com.alex.dao.StudentCourseDao;
import com.alex.domain.Student;

import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Map;

public class StudentCourseDaoImpl implements StudentCourseDao {
    private String filename;

    public StudentCourseDaoImpl(String filename) {
        this.filename = filename;
    }
    @Override
    public void saveStudentCourses(Map<String, Student> studentCourses) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename,true))) {
            for (Map.Entry<String, Student> entry : studentCourses.entrySet()) {
                writer.write(entry.getKey() + "\t" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
