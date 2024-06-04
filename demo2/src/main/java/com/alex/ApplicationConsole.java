package com.alex;

import com.alex.dao.*;
import com.alex.dao.impl.literal.*;
import com.alex.domain.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApplicationConsole {
    public static void main(String[] args) {
        String path="E:\\Project\\JAVA\\CRS\\demo2\\src\\main\\java\\com\\alex\\plainFiles\\";
        // 创建文件版本的txt实现类
        TeachersDAO teacherDao = new TeachersDAOImpl(path+"Teachers.txt");
        StudentsDao studentDao = new StudentsDAOImpl(path+"Students.txt");
        CoursesDAO courseDao = new CoursesDAOImpl(path+"Courses.txt");
        ScheduledCoursesDAOImpl scheduledCourseDao = new ScheduledCoursesDAOImpl(path+"ScheduledCourses.txt");

        // 从文件中读取数据
        List<Teacher> teachers = teacherDao.getAllTeachers();
        List<Student> students = studentDao.getAllStudents();
        List<Course> courses = courseDao.getAllCourses();
        List<ScheduledCourse> scheduledCourses = scheduledCourseDao.getAllScheduledCourses();

        // 打印教师信息
        System.out.println("=========教师=========");
        for (Teacher teacher : teachers) {
            System.out.println(teacher.getName() + " (" + teacher.getTitle() + ", " + teacher.getDepartment() + ")");
        }

        // 打印学生信息
        System.out.println("=========学生=========");
        for (Student student : students) {
            System.out.println(student.getName() + " (" + student.getId() + ") [" + student.getDegree() + " - " + student.getMajor() + "]");
        }

        // 打印课程信息
        System.out.println("=========课程Course=========");
        for (Course course : courses) {
            System.out.println(course.getCourseNo() + "：" + course.getCourseName() + "，" + course.getCredits() + "学分");
        }

        // 打印开出的课程信息
        System.out.println("==========开出的课==========");
        for (ScheduledCourse scheduledCourse : scheduledCourses) {
            System.out.println(scheduledCourse.getFullScheduleCourseNo() + ", " + scheduledCourse.getDayOfWeek()
                    + ", " + scheduledCourse.getTimeOfDay() + ", " + scheduledCourse.getRoom() + ", "
                    + scheduledCourse.getCapacity());
        }


        scheduledCourses.get(1).enroll(students.get(0));
        scheduledCourses.get(2).enroll(students.get(0));
        scheduledCourses.get(0).enroll(students.get(1));
        scheduledCourses.get(3).enroll(students.get(2));




        StudentCourseDao studentCourseDao = new StudentCourseDaoImpl(path+"StudentCourses.txt");

        for (ScheduledCourse scheduledCours : scheduledCourses) {
            studentCourseDao.saveStudentCourses(scheduledCours.getEnrolledStudent());
        }

        // 保存学生选课结果到文件中



    }
}
