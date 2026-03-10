package com.uob.sis.entity;

import javax.persistence.*;

@Entity
@Table(name = "enrollment")
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_section_id")
    private CourseSection courseSection;

    private LocalDateTime enrollmentDate;
    private String status; // e.g., "ENROLLED", "DROPPED"

    // Getters and setters
}