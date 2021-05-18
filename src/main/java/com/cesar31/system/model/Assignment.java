package com.cesar31.system.model;

/**
 *
 * @author cesar31
 */
public class Assignment {

    private Student student;
    private Schedule schedule;
    private int homeworkeScore;
    private int testScore;

    public Assignment() {
    }

    public Assignment(Student student, Schedule schedule, int homeworkeScore, int testScore) {
        this.student = student;
        this.schedule = schedule;
        this.homeworkeScore = homeworkeScore;
        this.testScore = testScore;
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

    public int getHomeworkeScore() {
        return homeworkeScore;
    }

    public void setHomeworkeScore(int homeworkeScore) {
        this.homeworkeScore = homeworkeScore;
    }

    public int getTestScore() {
        return testScore;
    }

    public void setTestScore(int testScore) {
        this.testScore = testScore;
    }

    @Override
    public String toString() {
        return "Assignment{" + "student=" + student + ", schedule=" + schedule + ", homeworkeScore=" + homeworkeScore + ", testScore=" + testScore + '}';
    }
}
