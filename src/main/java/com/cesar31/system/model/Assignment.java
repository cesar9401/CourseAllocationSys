package com.cesar31.system.model;

/**
 *
 * @author cesar31
 */
public class Assignment {

    // student.getId().concat(schedule.getId());
    private String id;

    private Student student;
    private Schedule schedule;
    private int homeworkScore;
    private int testScore;

    public Assignment() {
    }

    public Assignment(Student student, Schedule schedule, int homeworkeScore, int testScore) {
        this.student = student;
        this.schedule = schedule;
        this.homeworkScore = homeworkeScore;
        this.testScore = testScore;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public int getHomeworkScore() {
        return homeworkScore;
    }

    public void setHomeworkScore(int homeworkScore) {
        this.homeworkScore = homeworkScore;
    }

    public int getTestScore() {
        return testScore;
    }

    public void setTestScore(int testScore) {
        this.testScore = testScore;
    }

    @Override
    public String toString() {
        return "Assignment{" + "id=" + id + ", student=" + student + ", schedule=" + schedule + ", homeworkScore=" + homeworkScore + ", testScore=" + testScore + '}';
    }
}
