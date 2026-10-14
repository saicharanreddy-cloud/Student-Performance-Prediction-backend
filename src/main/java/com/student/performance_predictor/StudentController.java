
package com.student.performance_predictor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "https://student-performance-prediction-frontend-u3dm.onrender.com")
public class StudentController {

    private final StudentRepository studentRepository;
    private final PredictionService predictionService;

    public StudentController(
            StudentRepository studentRepository,
            PredictionService predictionService) {
        this.studentRepository = studentRepository;
        this.predictionService = predictionService;
    }

    @PostMapping("/predict")
    public Student predictPerformance(@RequestBody Student student) {
        double predictedMarks = predictionService.predictMarks(student);

        student.setPredictedMarks(predictedMarks);

        return studentRepository.save(student);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return studentRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable Long id,
            @RequestBody Student updatedStudent) {

        return studentRepository.findById(id)
                .map(student -> {
                    student.setName(updatedStudent.getName());
                    student.setAttendance(updatedStudent.getAttendance());
                    student.setStudyHours(updatedStudent.getStudyHours());
                    student.setAssignmentMarks(updatedStudent.getAssignmentMarks());
                    student.setPreviousMarks(updatedStudent.getPreviousMarks());

                    double predictedMarks =
                            predictionService.predictMarks(student);

                    student.setPredictedMarks(predictedMarks);

                    Student savedStudent = studentRepository.save(student);

                    return ResponseEntity.ok(savedStudent);
                })
                .orElseGet(() -> ResponseEntity.<Student>notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        if (!studentRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        studentRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
