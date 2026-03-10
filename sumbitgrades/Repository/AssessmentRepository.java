package com.uob.sis.repository;

import com.uob.sis.entity.Assessment;
import com.uob.sis.entity.AssessmentScore;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AssessmentRepository extends JpaRepository<Assessment, Long> {
    List<Assessment> findByCourseSectionIdAndRequiredTrue(Long courseSectionId);
}

public interface AssessmentScoreRepository extends JpaRepository<AssessmentScore, Long> {
    List<AssessmentScore> findByAssessmentInAndStudentId(List<Assessment> assessments, Long studentId);
}