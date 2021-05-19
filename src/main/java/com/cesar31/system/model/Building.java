package com.cesar31.system.model;

import com.cesar31.system.structures.LinkdList;

/**
 *
 * @author cesar31
 */
public class Building {

    private String name;
    private LinkdList<Classroom> classrooms;

    public Building() {
        this.classrooms = new LinkdList<>();
    }

    public Building(String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkdList<Classroom> getClassrooms() {
        return classrooms;
    }

    @Override
    public String toString() {
        return "Building{" + "name=" + name + ", classrooms=" + classrooms + '}';
    }
}
