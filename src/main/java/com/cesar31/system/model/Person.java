package com.cesar31.system.model;

/**
 *
 * @author cesar31
 */
public class Person {
    
    private String id;
    private String name;
    private String address;

    public Person() {
    }

    public Person(String id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return id + "\\n" + name + "\\n" + address;
    }
}
