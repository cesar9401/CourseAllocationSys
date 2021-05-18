package com.cesar31.system.model;

/**
 *
 * @author cesar31
 */
public class Course {

    private String id;
    private String name;
    private int semester;
    private int credits;

    public Course() {
    }

    public Course(String id, String name, int semester, int credits) {
        this.id = id;
        this.name = name;
        this.semester = semester;
        this.credits = credits;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    @Override
    public String toString() {
        return "Course{" + "id=" + id + ", name=" + name + ", semester=" + semester + ", credits=" + credits + '}';
    }
}
