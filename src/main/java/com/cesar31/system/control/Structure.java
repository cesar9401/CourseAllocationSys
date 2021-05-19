package com.cesar31.system.control;

import com.cesar31.system.model.*;
import com.cesar31.system.structures.*;

/**
 *
 * @author cesar31
 */
public class Structure {

    private HashTable<Student> students;
    private DoublyLinkdList<User> users;
    private DoublyLinkdList<Building> buildings;
    private AVLTree<Professor> professors;
    private DoublyLinkdList<Course> courses;
    private BTree<Schedule> schedules;
    private DoublyLinkdList<Assignment> assignments;

    public Structure() {
        this.students = new HashTable<>();
        this.users = new DoublyLinkdList<>();
        this.buildings = new DoublyLinkdList<>();
        this.professors = new AVLTree();
        this.courses = new DoublyLinkdList<>();
        this.schedules = new BTree<>(Schedule.class);
        this.assignments = new DoublyLinkdList<>();
    }

    public HashTable<Student> getStudents() {
        return students;
    }

    public void setStudents(HashTable<Student> students) {
        this.students = students;
    }

    public DoublyLinkdList<User> getUsers() {
        return users;
    }

    public void setUsers(DoublyLinkdList<User> users) {
        this.users = users;
    }

    public DoublyLinkdList<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(DoublyLinkdList<Building> buildings) {
        this.buildings = buildings;
    }

    public AVLTree<Professor> getProfessors() {
        return professors;
    }

    public void setProfessors(AVLTree<Professor> professors) {
        this.professors = professors;
    }

    public DoublyLinkdList<Course> getCourses() {
        return courses;
    }

    public void setCourses(DoublyLinkdList<Course> courses) {
        this.courses = courses;
    }

    public BTree<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(BTree<Schedule> schedules) {
        this.schedules = schedules;
    }

    public DoublyLinkdList<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(DoublyLinkdList<Assignment> assignments) {
        this.assignments = assignments;
    }
}
