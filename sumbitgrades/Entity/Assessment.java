package com.uob.sis.entity;

import javax.persistence.*;

@Entity
@Table(name = "assessment")
public class Assessment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_section_id")
    private CourseSection courseSection;

    private String name; // e.g., "Midterm", "Final"
    private Double weight;
    private boolean required; // must have score to submit final grade

    // Getters and setters
}