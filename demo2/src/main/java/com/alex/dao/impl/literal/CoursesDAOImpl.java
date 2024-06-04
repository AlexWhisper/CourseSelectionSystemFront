package com.alex.dao.impl.literal;

import com.alex.dao.CoursesDAO;
import com.alex.domain.Course;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CoursesDAOImpl implements CoursesDAO {
    private String filename;

    public CoursesDAOImpl(String filename) {
        this.filename = filename;
    }

    @Override
    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split("\t");
                String courseNo = fields[0];
                String courseName = fields[1];
                double credits = Double.parseDouble(fields[2]);
                courses.add(new Course(courseNo, courseName, credits));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return courses;
    }
}
