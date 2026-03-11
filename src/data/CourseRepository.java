package data;

import business.Course;
import java.util.*;

/**
 * Data Access Layer class simulating database operations.
 * Manages in-memory storage for courses and student enrollments.
 * 
 */
public class CourseRepository {
    // ==================== CONSTANTS ====================
    private static final String COURSE_NOT_FOUND_DEBUG = "Debug: Course not found - ";
    private static final String NO_SEATS_DEBUG = "Debug: No available seats";
    private static final String ALREADY_ENROLLED_DEBUG = "Debug: Already enrolled";
    private static final String ENROLLMENT_SUCCESS_DEBUG = "Debug: Enrollment successful for ";
    
    // ==================== DATA STRUCTURES ====================
    private Map<String, Course> courses;                    // Key: courseCode, Value: Course object
    private Map<String, List<String>> studentEnrollments;   // Key: studentId, Value: List of courseCodes
    
    // ==================== CONSTRUCTOR ====================
    /**
     * Initializes repository with empty data structures and populates sample data.
     */
    public CourseRepository() {
        courses = new HashMap<>();
        studentEnrollments = new HashMap<>();
        initializeSampleData();
    }
    
    // ==================== DATA INITIALIZATION ====================
    
    /**
     * Populates the repository with sample courses for testing.
     * Creates 4 courses with proper prerequisite relationships.
     */
    private void initializeSampleData() {
        // Course 1: Java Programming (Foundation course - no prerequisites)
        Course javaCourse = new Course(
            "itcs113", 
            "Java Programming 1", 
            3, 
            30,
            new ArrayList<>()  // No prerequisites
        );
        
        // Course 2: Data Structures (Requires Java)
        Course dsCourse = new Course(
            "itcs214", 
            "Data Structures", 
            3, 
            25,
            Arrays.asList("itcs113")  // Requires Java Programming
        );
        
        // Course 3: Algorithms (Requires Data Structures)
        Course algoCourse = new Course(
            "itcs347", 
            "Algorithms", 
            3, 
            20,
            Arrays.asList("itcs214")  // Requires Data Structures
        );
        
        // Course 4: Database Systems (No prerequisites)
        Course dbCourse = new Course(
            "itcs285", 
            "Database Systems", 
            3, 
            30,
            new ArrayList<>()  // No prerequisites
        );
        
        // Store courses in map with courseCode as key
        courses.put(javaCourse.getCourseCode(), javaCourse);
        courses.put(dsCourse.getCourseCode(), dsCourse);
        courses.put(algoCourse.getCourseCode(), algoCourse);
        courses.put(dbCourse.getCourseCode(), dbCourse);
    }
    
    // ==================== READ OPERATIONS ====================
    
    /**
     * Finds a course by its code with case-insensitive search.
     * Time Complexity: O(n) where n is number of courses
     * 
     * @param courseCode The course code to search for
     * @return Course object if found, null otherwise
     */
    public Course findCourseByCode(String courseCode) {
        for (Map.Entry<String, Course> entry : courses.entrySet()) {
            if (entry.getKey().equalsIgnoreCase(courseCode)) {
                return entry.getValue();
            }
        }
        return null;
    }
    
    /**
     * Retrieves all courses in the system.
     * 
     * @return Collection of all Course objects
     */
    public Collection<Course> getAllCourses() {
        return courses.values();
    }
    
    /**
     * Checks if a student is enrolled in a specific course.
     * Performs case-insensitive comparison.
     * 
     * @param studentId The student ID to check
     * @param courseCode The course code to check
     * @return true if enrolled, false otherwise
     */
    public boolean isStudentEnrolled(String studentId, String courseCode) {
        List<String> enrollments = studentEnrollments.getOrDefault(studentId, new ArrayList<>());
        
        for (String enrolled : enrollments) {
            if (enrolled.equalsIgnoreCase(courseCode)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Gets all courses a student is enrolled in.
     * 
     * @param studentId The student ID
     * @return List of course codes (empty list if none)
     */
    public List<String> getStudentEnrollments(String studentId) {
        return studentEnrollments.getOrDefault(studentId, new ArrayList<>());
    }
    
    // ==================== WRITE OPERATIONS ====================
    
    /**
     * Enrolls a student in a course after validating all conditions.
     * This is a transactional operation that updates both course and enrollment records.
     * 
     * Business Rules:
     * 1. Course must exist
     * 2. Seats must be available
     * 3. Student must not be already enrolled
     * 
     * @param studentId The student to enroll
     * @param courseCode The course to enroll in
     * @return true if enrollment successful, false otherwise
     */
    public boolean enrollStudent(String studentId, String courseCode) {
        Course course = findCourseByCode(courseCode);
        
        // Validation Rule 1: Course exists
        if (course == null) {
            System.out.println(COURSE_NOT_FOUND_DEBUG + courseCode);
            return false;
        }
        
        // Validation Rule 2: Seats available
        if (course.getCurrentEnrollment() >= course.getMaxSeats()) {
            System.out.println(NO_SEATS_DEBUG);
            return false;
        }
        
        // Validation Rule 3: Not already enrolled
        if (isStudentEnrolled(studentId, courseCode)) {
            System.out.println(ALREADY_ENROLLED_DEBUG);
            return false;
        }
        
        // Process enrollment
        // Get or create student's enrollment list, then add course
        studentEnrollments.computeIfAbsent(studentId, k -> new ArrayList<>())
                         .add(course.getCourseCode());
        
        // Update course enrollment count
        course.setCurrentEnrollment(course.getCurrentEnrollment() + 1);
        
        System.out.println(ENROLLMENT_SUCCESS_DEBUG + courseCode);
        return true;
    }
}