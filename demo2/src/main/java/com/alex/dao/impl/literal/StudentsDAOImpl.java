package com.alex.dao.impl.literal;

import com.alex.dao.StudentsDao;
import com.alex.domain.Student;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentsDAOImpl implements StudentsDao {
    private String filename;
    public StudentsDAOImpl(String filename) {
        this.filename = filename;
    }
    @Override
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split("\t");
                String name = fields[0];
                String id = fields[1];
                String major = fields[2];
                String degree = fields[3];
                students.add(new Student(name, id, major, degree));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }
}
