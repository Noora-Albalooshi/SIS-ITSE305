package com.uob.sis.dto;

import javax.validation.constraints.NotNull;
import java.util.List;

public class GradeSubmissionRequestDTO {
    @NotNull
    private Long courseSectionId;

    @NotNull
    private List<StudentGradeDTO> studentGrades;

    // Getters and setters

    public static class StudentGradeDTO {
        @NotNull
        private Long studentId;
        @NotNull
        private String gradeValue;

        // Getters and setters
    }
}