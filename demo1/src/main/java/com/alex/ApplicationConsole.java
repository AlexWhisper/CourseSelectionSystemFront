package com.alex;

import com.alex.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ApplicationConsole {
    public static void main(String[] args) {
//        应用程序首先初始化对象，然后输出对象，最后模拟一个学生选课成功，另一个学生选课因满员而失败两个场景。控制台应用的输出如下：
//=========教师=========
//        董永 (副教授, 计算机科学与技术)
//        赵云 (教授, 计算机科学与技术)
//        郭天 (教授, 数学)
//                =========学生=========
//        张三 (201901001) [学士 - 数学]
//        李四 (201901002) [学士 - 计算机科学与技术]
//        王五 (201901003) [学士 - 计算机科学与技术]
//                =========课程Course=========
//        CS103：数据结构与算法，4.0学分
//        MAT101：概率与统计，3.0学分
//        CS101：C 程序设计，3.0学分
//        CS102：面向对象程序设计，3.0学分
//        CS201：离散数学，3.0学分
//                ==========开出的课==========
//        CS102-2, 周四, 下午4:00-6:00, 郭天 (教授, 数学), 25
//        CS103-1, 周一, 下午6:00-8:00, 董永 (副教授, 计算机科学与技术), 20
//        MAT101-1, 周五, 下午4:00-6:00, 赵云 (教授, 计算机科学与技术), 15
//        CS101-1, 周一, 上午8:00-10:00, 郭天 (教授, 数学), 30
//        CS101-2, 周二, 上午8:00-10:00, 赵云 (教授, 计算机科学与技术), 30
//        CS102-1, 周三, 下午2:00-4:00, 董永 (副教授, 计算机科学与技术), 25
//        CS201-1, 周一, 下午4:00-6:00, 郭天 (教授, 数学), 1
//
//        学生 张三 试图注册 CS201-1
//        注册成功
//        学生 王五 试图注册 CS201-1
//        注册失败：已满员


        List<Teacher> teachers = new ArrayList<Teacher>();
        teachers.add(new Teacher("郭天", "00003", "教授", "数学"));
        teachers.add(new Teacher("赵云", "00002", "教授", "计算机科学与技术"));
        teachers.add(new Teacher("董永", "00001", "副教授", "计算机科学与技术"));

        List<Student> students = new ArrayList<Student>();
        students.add(new Student("张三", "201901001", "数学", "学士"));
        students.add(new Student("李四", "201901002", "计算机科学与技术", "学士"));
        students.add(new Student("王五", "201901003", "计算机科学与技术", "学士"));

        List<Course> courseList = new ArrayList<>();
        courseList.add(new Course("CS103", "数据结构与算法", 4.0));
        courseList.add(new Course("MAT101", "概率与统计", 3.0));
        courseList.add(new Course("CS101", "C程序设计", 3.0));
        courseList.add(new Course("CS102", "面向对象程序设计", 3.0));
        courseList.add(new Course("CS201", "离散数学", 3.0));

        List<ScheduledCourse> scheduledCourses = new ArrayList<>();
        scheduledCourses.add(new ScheduledCourse(2, "周四", "下午4:00-6:00", new Course("CS102", "面向对象程序设计", 3.0), "教室", 25));
        scheduledCourses.add(new ScheduledCourse(1, "周一", "下午6:00-8:00", new Course("CS103", "数据结构与算法", 4.0), "教室", 20));
        scheduledCourses.add(new ScheduledCourse(1, "周五", "下午4:00-6:00", new Course("MAT101", "概率与统计", 3.0), "教室", 15));
        scheduledCourses.add(new ScheduledCourse(1, "周一", "上午8:00-10:00", new Course("CS101", "C程序设计", 3.0), "教室", 30));
        scheduledCourses.add(new ScheduledCourse(2, "周二", "上午8:00-10:00", new Course("CS101", "C程序设计", 3.0), "教室", 30));
        scheduledCourses.add(new ScheduledCourse(1, "周三", "下午2:00-4:00", new Course("CS102", "面向对象程序设计", 3.0), "教室", 25));
        scheduledCourses.add(new ScheduledCourse(1, "周一", "下午4:00-6:00", new Course("CS201", "离散数学", 3.0), "教室", 1));




        System.out.println("=============教师===============");
        for (Teacher teacher : teachers) {
            System.out.println(teacher);
        }
        System.out.println("=============学生===============");
        for (Student student : students) {
            System.out.println(student );
        }
        System.out.println("=============课程Course===============");
        for (Course course : courseList) {
            System.out.println(course);
        }
        System.out.println("=============开出的课===============");
        for (ScheduledCourse scheduledCourse : scheduledCourses) {
            System.out.println(scheduledCourse);
        }

        ScheduledCourse CS201_1 = scheduledCourses.get(6);
        EnrollmentStatus status = CS201_1.enroll(students.get(0));
        System.out.println(students.get(0).getName()+"试图注册"+ CS201_1.getRepresentedCourse().getCourseNo());
        System.out.println(status.value());

        System.out.println(students.get(2).getName()+"试图注册"+ CS201_1.getRepresentedCourse().getCourseNo());
        status = CS201_1.enroll(students.get(2));
        System.out.println(status.value());

        CS201_1.enroll(students.get(1));

        CS201_1.drop(students.get(0));

        //重复选课
        ScheduledCourse CS103 = scheduledCourses.get(0);
        status=CS103.enroll(students.get((0)));
        System.out.println(status.value());
        status=CS103.enroll(students.get((0)));
        System.out.println(status.value());


        //重复选课
        ScheduledCourse CS102_1 = scheduledCourses.get(5);
        ScheduledCourse CS102_2 = scheduledCourses.get(1);
        status=CS102_1.enroll(students.get((0)));
        System.out.println(status.value());
        status=CS102_2.enroll(students.get((0)));
        System.out.println(status.value());



    }
}
