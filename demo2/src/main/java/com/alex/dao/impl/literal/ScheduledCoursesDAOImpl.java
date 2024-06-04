package com.alex.dao.impl.literal;

import com.alex.dao.ScheduledCoursesDAO;
import com.alex.domain.Course;
import com.alex.domain.ScheduledCourse;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ScheduledCoursesDAOImpl implements ScheduledCoursesDAO {

    private String filename;

    public ScheduledCoursesDAOImpl(String filename) {
        this.filename = filename;
    }

    @Override
    public List<ScheduledCourse> getAllScheduledCourses() {
        List<ScheduledCourse> scheduledCourses = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split("\t");
                String courseNo = fields[0];
                int classNo = Integer.parseInt(fields[1]);
                String dayOfWeek = fields[2];
                String timeOfDay = fields[3];
                String room = fields[4];
                int capacity = Integer.parseInt(fields[5]);
                scheduledCourses.add(new ScheduledCourse(courseNo, classNo, dayOfWeek, timeOfDay, room, capacity));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scheduledCourses;
    }



}
