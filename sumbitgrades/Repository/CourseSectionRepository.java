package com.uob.sis.repository;

import com.uob.sis.entity.CourseSection;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CourseSectionRepository extends JpaRepository<CourseSection, Long> {
    Optional<CourseSection> findByIdAndFacultyId(Long courseSectionId, Long facultyId);
}