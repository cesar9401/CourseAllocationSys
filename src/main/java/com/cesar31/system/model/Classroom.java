package com.cesar31.system.model;

/**
 *
 * @author cesar31
 */
public class Classroom {

    private Building building;
    private String id;
    private int numberOfStudents;

    public Classroom() {
    }

    public Classroom(String id, int numberOfStudents) {
        this.id = id;
        this.numberOfStudents = numberOfStudents;
    }

    public Classroom(Building building, String id, int numberOfStudents) {
        this.building = building;
        this.id = id;
        this.numberOfStudents = numberOfStudents;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public void setNumberOfStudents(int numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }

    @Override
    public String toString() {
        return "Classroom{" + "building=" + building + ", id=" + id + ", numberOfStudents=" + numberOfStudents + '}';
    }
}
