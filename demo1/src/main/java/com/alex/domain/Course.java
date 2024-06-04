package com.alex.domain;

public class Course {
    private String courseNo;
    private String courseName;
    private double credits;


    public Course(String cNo, String cName, double credits) {
        setCourseNo(cNo);
        setCourseName(cName);
        setCredits(credits);
    }

    public void setCourseNo(String cNo) {
        courseNo = cNo;
    }

    public String getCourseNo() {
        return courseNo;
    }

    public void setCourseName(String cName) {
        courseName = cName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCredits(double c) {
        credits = c;
    }

    public double getCredits() {
        return credits;
    }

    public String toString() {
        String prerequisiteCourses = "";

        return getCourseNo() + ":  " + getCourseName() + "\tCredits:  "
                + getCredits() + prerequisiteCourses;
    }


    public ScheduledCourse  getScheduledCourse(String dayOfWeek
            , String timeOfDay, String room,int capacity, int classNo) {

        ScheduledCourse s = new ScheduledCourse(classNo
                , dayOfWeek, timeOfDay, this, room, capacity);
        return s;
    }

}
