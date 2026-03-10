package com.uob.sis.repository;

import com.uob.sis.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface GradeRepository extends JpaRepository<Grade, Long> {
    Optional<Grade> findByEnrollmentIdAndIsFinalTrue(Long enrollmentId);
}