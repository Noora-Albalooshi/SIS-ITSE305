package com.uob.sis.repository;

import com.uob.sis.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByCourseSectionIdAndStatus(Long courseSectionId, String status);
}