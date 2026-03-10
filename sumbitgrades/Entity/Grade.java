package com.uob.sis.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "grade")
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "enrollment_id")
    private Enrollment enrollment;

    @ManyToOne
    @JoinColumn(name = "submitted_by")
    private Faculty submittedBy;

    private String gradeValue; // e.g., "A", "B+", 85.5
    private LocalDateTime submissionDate;
    private boolean isFinal; // true for final grade

    // Getters and setters
}