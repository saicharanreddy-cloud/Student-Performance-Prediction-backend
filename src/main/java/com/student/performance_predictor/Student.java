
package com.student.performance_predictor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double attendance;
    private double studyHours;
    private double assignmentMarks;
    private double previousMarks;
    private double predictedMarks;

    public Student() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAttendance() {
        return attendance;
    }

    public void setAttendance(double attendance) {
        this.attendance = attendance;
    }

    public double getStudyHours() {
        return studyHours;
    }

    public void setStudyHours(double studyHours) {
        this.studyHours = studyHours;
    }

    public double getAssignmentMarks() {
        return assignmentMarks;
    }

    public void setAssignmentMarks(double assignmentMarks) {
        this.assignmentMarks = assignmentMarks;
    }

    public double getPreviousMarks() {
        return previousMarks;
    }

    public void setPreviousMarks(double previousMarks) {
        this.previousMarks = previousMarks;
    }

    public double getPredictedMarks() {
        return predictedMarks;
    }

    public void setPredictedMarks(double predictedMarks) {
        this.predictedMarks = predictedMarks;
    }
}
