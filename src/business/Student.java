package business;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Student entity in the enrollment system.
 * Contains personal information and academic history.
 */
public class Student {
    // ==================== INSTANCE VARIABLES ====================
    private String studentId;           // Unique student identifier
    private String name;                 // Student's full name
    private String major;                 // Academic major/department
    private int enrollmentYear;           // Year of admission
    private List<String> completedCourses; // Courses successfully completed
    
    // ==================== CONSTRUCTOR ====================
    /**
     * Constructs a new Student with basic information.
     * Initializes completedCourses as an empty ArrayList.
     * 
     * @param studentId      Unique student identifier
     * @param name           Student's full name
     * @param major          Student's major field of study
     * @param enrollmentYear Year of enrollment
     */
    public Student(String studentId, String name, String major, int enrollmentYear) {
        this.studentId = studentId;
        this.name = name;
        this.major = major;
        this.enrollmentYear = enrollmentYear;
        this.completedCourses = new ArrayList<>(); // Initialize empty list
    }
    
    // ==================== GETTERS & SETTERS ====================
    
    /**
     * @return The unique student ID
     */
    public String getStudentId() {
        return studentId;
    }
    
    /**
     * @param studentId The new student ID to set
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    
    /**
     * @return Student's full name
     */
    public String getName() {
        return name;
    }
    
    /**
     * @param name The new name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * @return Student's major
     */
    public String getMajor() {
        return major;
    }
    
    /**
     * @param major The new major to set
     */
    public void setMajor(String major) {
        this.major = major;
    }
    
    /**
     * @return Year of enrollment
     */
    public int getEnrollmentYear() {
        return enrollmentYear;
    }
    
    /**
     * @param enrollmentYear The new enrollment year
     */
    public void setEnrollmentYear(int enrollmentYear) {
        this.enrollmentYear = enrollmentYear;
    }
    
    /**
     * @return List of completed course codes
     */
    public List<String> getCompletedCourses() {
        return completedCourses;
    }
    
    /**
     * @param completedCourses The new list of completed courses
     */
    public void setCompletedCourses(List<String> completedCourses) {
        this.completedCourses = completedCourses;
    }
    
    // ==================== BUSINESS METHODS ====================
    
    /**
     * Adds a course to student's completed courses list.
     * Prevents duplicate entries.
     * 
     * @param courseCode The completed course code to add
     */
    public void addCompletedCourse(String courseCode) {
        if (!completedCourses.contains(courseCode)) {
            completedCourses.add(courseCode);
        }
    }
    
    // ==================== OVERRIDDEN METHODS ====================
    
    /**
     * Returns formatted student information.
     * Format: "studentId - name (major, Class of year)"
     * 
     * @return Formatted student string
     */
    @Override
    public String toString() {
        return String.format("%s - %s (%s, Class of %d)", 
            studentId, name, major, enrollmentYear);
    }
}