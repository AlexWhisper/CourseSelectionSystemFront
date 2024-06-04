package com.alex.domain;

public class Teacher extends Person {
    private String title;
    private String department;

    public Teacher(String name, String pid, String title, String dept) {
        super(name, pid);
        setTitle(title);
        setDepartment(dept);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDepartment(String dept) {
        department = dept;
    }

    public String getDepartment() {
        return department;
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public String toString() {
        return getName()+"（"+getTitle()+", "+getDepartment()+"）";
    }
}
