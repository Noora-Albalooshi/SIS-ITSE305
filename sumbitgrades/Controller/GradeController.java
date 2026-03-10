package com.uob.sis.controller;

import com.uob.sis.dto.GradeSubmissionRequestDTO;
import com.uob.sis.dto.GradeSubmissionResponseDTO;
import com.uob.sis.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/grades")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @PostMapping("/submit")
    public ResponseEntity<GradeSubmissionResponseDTO> submitGrades(
            @Valid @RequestBody GradeSubmissionRequestDTO request,
            @AuthenticationPrincipal UserDetails currentUser) {

        // Extract faculty ID from authenticated user (implementation depends on security setup)
        Long facultyId = extractFacultyId(currentUser);

        GradeSubmissionResponseDTO response = gradeService.submitFinalGrades(request, facultyId);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

    private Long extractFacultyId(UserDetails userDetails) {
        // In a real app, you would get the faculty ID from the user details or a service
        // For simplicity, assume the username is the faculty ID (or you have a custom UserDetails)
        return Long.parseLong(userDetails.getUsername());
    }
}