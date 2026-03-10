package com.uob.sis.service;

import com.uob.sis.dto.GradeSubmissionRequestDTO;
import com.uob.sis.dto.GradeSubmissionResponseDTO;
import com.uob.sis.entity.*;
import com.uob.sis.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GradeService {

    @Autowired
    private CourseSectionRepository courseSectionRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private AssessmentRepository assessmentRepository;

    @Autowired
    private AssessmentScoreRepository assessmentScoreRepository;

    @Transactional
    @PreAuthorize("hasRole('FACULTY')")
    public GradeSubmissionResponseDTO submitFinalGrades(GradeSubmissionRequestDTO request, Long facultyId) {
        GradeSubmissionResponseDTO response = new GradeSubmissionResponseDTO();
        List<String> errors = new ArrayList<>();

        // 1. Verify faculty is assigned to this course section
        CourseSection section = courseSectionRepository
                .findByIdAndFacultyId(request.getCourseSectionId(), facultyId)
                .orElse(null);
        if (section == null) {
            response.setSuccess(false);
            response.setMessage("Faculty not assigned to this course section.");
            return response;
        }

        // 2. Get all enrolled students (active)
        List<Enrollment> enrollments = enrollmentRepository
                .findByCourseSectionIdAndStatus(request.getCourseSectionId(), "ENROLLED");
        if (enrollments.isEmpty()) {
            response.setSuccess(false);
            response.setMessage("No enrolled students found.");
            return response;
        }

        // 3. Get all required assessments for this course section
        List<Assessment> requiredAssessments = assessmentRepository
                .findByCourseSectionIdAndRequiredTrue(request.getCourseSectionId());

        // 4. Validate each student grade submission
        for (GradeSubmissionRequestDTO.StudentGradeDTO sg : request.getStudentGrades()) {
            // Find enrollment for this student
            Enrollment enrollment = enrollments.stream()
                    .filter(e -> e.getStudent().getId().equals(sg.getStudentId()))
                    .findFirst()
                    .orElse(null);
            if (enrollment == null) {
                errors.add("Student ID " + sg.getStudentId() + " is not enrolled in this course.");
                continue;
            }

            // Check if final grade already exists
            if (gradeRepository.findByEnrollmentIdAndIsFinalTrue(enrollment.getId()).isPresent()) {
                errors.add("Final grade already exists for student ID " + sg.getStudentId());
                continue;
            }

            // Check all required assessments have scores
            if (!requiredAssessments.isEmpty()) {
                List<AssessmentScore> scores = assessmentScoreRepository
                        .findByAssessmentInAndStudentId(requiredAssessments, sg.getStudentId());
                if (scores.size() != requiredAssessments.size()) {
                    errors.add("Missing assessment scores for student ID " + sg.getStudentId());
                }
            }
        }

        if (!errors.isEmpty()) {
            response.setSuccess(false);
            response.setMessage("Validation failed.");
            response.setErrors(errors);
            return response;
        }

        // 5. Save final grades
        for (GradeSubmissionRequestDTO.StudentGradeDTO sg : request.getStudentGrades()) {
            Enrollment enrollment = enrollments.stream()
                    .filter(e -> e.getStudent().getId().equals(sg.getStudentId()))
                    .findFirst()
                    .get();

            Grade grade = new Grade();
            grade.setEnrollment(enrollment);
            grade.setGradeValue(sg.getGradeValue());
            grade.setSubmittedBy(section.getFaculty()); // faculty from section
            grade.setSubmissionDate(LocalDateTime.now());
            grade.setFinal(true);

            gradeRepository.save(grade);
        }

        response.setSuccess(true);
        response.setMessage("Grades submitted successfully.");
        return response;
    }
}