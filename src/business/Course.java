package business;
import java.util.List;

/**
 * Represents a Course entity in the enrollment system.
 * This class encapsulates all course-related data and business rules.
 */
public class Course {
    // ==================== INSTANCE VARIABLES ====================
    private String courseCode;          // Unique identifier for the course (e.g., "itcs113")
    private String courseName;           // Full name of the course
    private int credits;                  // Number of credit hours (usually 3)
    private int maxSeats;                 // Maximum student capacity
    private int currentEnrollment;         // Current number of enrolled students
    private List<String> prerequisites;    // List of course codes required before enrollment
    
    // ==================== CONSTRUCTOR ====================
    /**
     * Constructs a new Course with all required attributes.
     * Initializes currentEnrollment to 0 by default.
     * 
     * @param courseCode    Unique course identifier
     * @param courseName    Full course name
     * @param credits       Credit hours value
     * @param maxSeats      Maximum seating capacity
     * @param prerequisites List of prerequisite course codes
     */
    public Course(String courseCode, String courseName, int credits, 
                  int maxSeats, List<String> prerequisites) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.credits = credits;
        this.maxSeats = maxSeats;
        this.currentEnrollment = 0;  // Default value
        this.prerequisites = prerequisites;
    }
    
    // ==================== GETTERS & SETTERS ====================
    
    /**
     * @return The unique course code
     */
    public String getCourseCode() {
        return courseCode;
    }
    
    /**
     * @param courseCode The new course code to set
     */
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
    
    /**
     * @return The full course name
     */
    public String getCourseName() {
        return courseName;
    }
    
    /**
     * @param courseName The new course name to set
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    
    /**
     * @return Number of credit hours
     */
    public int getCredits() {
        return credits;
    }
    
    /**
     * @param credits The new credit hours value
     */
    public void setCredits(int credits) {
        this.credits = credits;
    }
    
    /**
     * @return Maximum seats available
     */
    public int getMaxSeats() {
        return maxSeats;
    }
    
    /**
     * @param maxSeats The new maximum seats value
     */
    public void setMaxSeats(int maxSeats) {
        this.maxSeats = maxSeats;
    }
    
    /**
     * @return Current number of enrolled students
     */
    public int getCurrentEnrollment() {
        return currentEnrollment;
    }
    
    /**
     * @param currentEnrollment The new enrollment count
     */
    public void setCurrentEnrollment(int currentEnrollment) {
        this.currentEnrollment = currentEnrollment;
    }
    
    /**
     * @return List of prerequisite course codes
     */
    public List<String> getPrerequisites() {
        return prerequisites;
    }
    
    /**
     * @param prerequisites The new list of prerequisites
     */
    public void setPrerequisites(List<String> prerequisites) {
        this.prerequisites = prerequisites;
    }
    
    // ==================== BUSINESS LOGIC METHODS ====================
    
    /**
     * Checks if the course has available seats for enrollment.
     * Business Rule: Students can only enroll if current enrollment < max seats.
     * 
     * @return true if seats are available, false if course is full
     */
    public boolean hasAvailableSeats() {
        return currentEnrollment < maxSeats;
    }
    
    /**
     * Calculates the number of remaining seats.
     * 
     * @return Number of available seats (maxSeats - currentEnrollment)
     */
    public int getAvailableSeats() {
        return maxSeats - currentEnrollment;
    }
    
    // ==================== OVERRIDDEN METHODS ====================
    
    /**
     * Returns a formatted string representation of the course.
     * Format: "courseCode - courseName (credits credits) | Seats: currentEnrollment/maxSeats"
     * 
     * @return Formatted course information
     */
    @Override
    public String toString() {
        return String.format("%s - %s (%d credits) | Seats: %d/%d", 
            courseCode, courseName, credits, currentEnrollment, maxSeats);
    }
}