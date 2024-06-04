package com.alex.dao.impl.literal;

import com.alex.dao.TeachersDAO;
import com.alex.domain.Teacher;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TeachersDAOImpl implements TeachersDAO {
    private String filename;

    public TeachersDAOImpl(String filename) {
        this.filename = filename;
    }

    @Override
    public List<Teacher> getAllTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split("\t");
                String name = fields[0];
                String id = fields[1];
                String title = fields[2];
                String department = fields[3];
                teachers.add(new Teacher(name, id, title, department));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return teachers;
    }
}
