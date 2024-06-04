package com.alex.domain;


public enum EnrollmentStatus {
    // 枚举量
    SUCCESS("注册成功"), SECTION_FULL("注册失败：已满员"), PREREQ("注册失败：前驱课未修"), PRE_ENROLL("注册失败：已经注册"), REP_COURSE("注册失败：重课");

    // 实例变量
    private final String value;

    EnrollmentStatus(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}