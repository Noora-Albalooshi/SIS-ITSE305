package com.uob.sis.entity;

import javax.persistence.*;

@Entity
@Table(name = "assessment_score")
public class AssessmentScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "assessment_id")
    private Assessment assessment;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    private Double score;

    // Getters and setters
}