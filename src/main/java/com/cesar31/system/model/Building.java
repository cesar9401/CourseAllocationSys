package com.cesar31.system.model;

/**
 *
 * @author cesar31
 */
public class Building {

    private String name;

    public Building() {
    }

    public Building(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Building{" + "name=" + name + '}';
    }
}
