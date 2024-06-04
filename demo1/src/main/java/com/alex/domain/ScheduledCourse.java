package com.alex.domain;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class ScheduledCourse {

    private int classNo; //课程号
    private String dayOfWeek; //周几
    private String timeOfDay;//时间
    private String room; //教室
    private int capacity;//课容量
    private Course representedCourse;//课程
    private Teacher instructor;//任课教师
    private Map<String, Student> enrolledStudent;//选课学生名单
    private Queue<Student> queue;

    public ScheduledCourse(int classNo, String dayOfWeek, String timeOfDay, Course representedCourse, String room, int capacity) {
        super();
        this.classNo = classNo;
        this.dayOfWeek = dayOfWeek;
        this.timeOfDay = timeOfDay;
        this.room = room;
        this.capacity = capacity;
        this.representedCourse = representedCourse;
        this.instructor = null;
        enrolledStudent = new HashMap<String, Student>();
        queue = new LinkedList<Student>();
    }

    public int getClassNo() {
        return classNo;
    }

    public void setClassNo(int classNo) {
        this.classNo = classNo;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getTimeOfDay() {
        return timeOfDay;
    }

    public void setTimeOfDay(String timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Course getRepresentedCourse() {
        return representedCourse;
    }

    public void setRepresentedCourse(Course representedCourse) {
        this.representedCourse = representedCourse;
    }

    public Teacher getInstructor() {
        return instructor;
    }

    public void setInstructor(Teacher instructor) {
        this.instructor = instructor;
    }

    public Map<String, Student> getEnrolledStudent() {
        return enrolledStudent;
    }

    public void setEnrolledStudent(Map<String, Student> enrolledStudent) {
        this.enrolledStudent = enrolledStudent;
    }

    /**
     * 返回如下格式： CS101-1，周一，上午 8：00-10：00，赵教授，2
     */
    public String toString() {
        return getRepresentedCourse().getCourseNo() + "-"
                + getClassNo()
                + "," + getDayOfWeek() + "," + getTimeOfDay() + ","
                + getInstructor() + ","
                + getCapacity();
    }

    /***
     * 课程号(course no.)和班号(course no.) 使用联合称为"完全号"。
     * 例如"CS101-1"
     */
    public String getFullScheduleCourseNo() {
        return getRepresentedCourse().getCourseNo() + "-" + getClassNo();
    }

    /**
     * 如果容量满了，则返回枚举对象"SECTION_FULL"
     * 如果当前课里没有这个学生，则把该学生加入到选课名单中
     */
//    public EnrollmentStatus enroll(Student student) {
//        EnrollmentStatus status = EnrollmentStatus.SUCCESS;
//        if (enrolledStudent.size() >= getCapacity()) {
//            status = EnrollmentStatus.SECTION_FULL;
//        } else {
//            //学生注册成功，放入选课名单
//            if (!enrolledStudent.containsValue(student)) {
//                enrolledStudent.put(student.getId(), student);
//            }
//        }
//        return status;
//    }


    /**
     * 1.	补充程序，使之实现“等待队列”功能。即当因某门课因座位满而无法选上时将该学生放入等待队列，一旦有空位，则从队列中移出一位学生进行注册。程序应输出：
     * =========退课等待队列自动注册成功=======
     * 学生 张三 试图退课 CS201-1
     * 张三退课成功！
     * 李四注册成功！
     * 当前等待队列的学生为：[王五 (202001003) [学士 - 计算机科学与技术]]
     * 当前选课名单为：{202001002=李四 (202001002) [学士 - 计算机科学与技术]}
     *
     *
    */
    public EnrollmentStatus enroll(Student student) {
        if (enrolledStudent.size() >= capacity) {
            queue.offer(student); // 将学生加入等待队列
            return EnrollmentStatus.SECTION_FULL;
        } else {
            if (!enrolledStudent.containsKey(student.getId())) {
                enrolledStudent.put(student.getId(), student);
                return EnrollmentStatus.SUCCESS;
            } else {
                return EnrollmentStatus.PRE_ENROLL;
            }
        }
    }


    /**
     * 退课
     */
//    public boolean drop(Student student) {
//        if (!isEnrolledIn(student))
//            return false;
//        else {
//            enrolledStudent.remove(student.getId());
//            return true;
//        }
//    }

    public boolean drop(Student student) {
        if (enrolledStudent.containsKey(student.getId())) {
            enrolledStudent.remove(student.getId());
            // 如果等待队列中有学生，则自动注册该学生
            if (!queue.isEmpty()) {
                Student waitingStudent = queue.poll();
                System.out.println("=========退课等待队列自动注册成功=======");
                System.out.println("学生 " + student.getName() + " 试图退课 " + getFullScheduleCourseNo());
                System.out.println(student.getName() + "退课成功！");
                EnrollmentStatus status = this.enroll(student);
                System.out.println(waitingStudent.getName() +status);
                System.out.println("当前等待队列的学生为：" + queue);
                System.out.println("当前选课名单为：" + enrolledStudent);
                return true;
            }
            return true;
        }
        return false;
    }

    /**
     * 判断是否已经选过课程
     */
    public boolean isEnrolledIn(Student student) {
        return enrolledStudent.values().contains(student);
    }

    public int getTotalEnrollment() {
        return enrolledStudent.size();
    }

    public Map<String, Student> getEnrolledStudents() {
        return enrolledStudent;
    }

    public boolean isScheduleCourseOf(Course course) {
        return course == representedCourse;
    }
}