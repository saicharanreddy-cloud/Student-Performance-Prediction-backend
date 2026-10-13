
package com.student.performance_predictor;

import org.springframework.stereotype.Service;

@Service
public class PredictionService {

    public double predictMarks(Student student) {
        double attendanceScore = student.getAttendance() * 0.10;

        double studyScore =
                Math.min(student.getStudyHours(), 10) * 2.0;

        double assignmentScore =
                student.getAssignmentMarks() * 0.25;

        double previousScore =
                student.getPreviousMarks() * 0.45;

        double predictedMarks =
                attendanceScore
                + studyScore
                + assignmentScore
                + previousScore;

        predictedMarks = Math.max(0, Math.min(100, predictedMarks));

        return Math.round(predictedMarks * 100.0) / 100.0;
    }
}
