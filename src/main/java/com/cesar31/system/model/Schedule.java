package com.cesar31.system.model;

import com.cesar31.system.structures.DoublyLinkdList;

/**
 *
 * @author cesar31
 */
public class Schedule {

    private String id;
    private String interval;
    private String day;
    private Course course;
    private Classroom classroom;
    private Building building;
    private Professor professor;
    
    // Asignaciones
    private DoublyLinkdList<Assignment> assignments;

    public Schedule() {
        this.assignments = new DoublyLinkdList<>();
    }

    public Schedule(String id, String interval, String day) {
        this();
        this.id = id;
        this.interval = interval;
        this.day = day;
    }
    
    public Schedule(String id, String interval, String day, Course course, Classroom classroom, Building building, Professor professor) {
        this();
        this.id = id;
        this.interval = interval;
        this.day = day;
        this.course = course;
        this.classroom = classroom;
        this.building = building;
        this.professor = professor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public DoublyLinkdList<Assignment> getAssignments() {
        return assignments;
    }

    @Override
    public String toString() {
        return "Schedule{" + "id=" + id + ", interval=" + interval + ", day=" + day + ", course=" + course + ", classroom=" + classroom + ", building=" + building + ", professor=" + professor + '}';
    }
}
