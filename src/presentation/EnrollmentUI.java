package presentation;

import business.*;
import java.util.Scanner;
import java.util.Collection;

/**
 * Presentation Layer Class
 * Handles all user interface interactions
 * Provides menu-driven interface for course enrollment
 */
public class EnrollmentUI {
    private EnrollmentService enrollmentService;
    private Scanner scanner;
    
    /**
     * Constructor initializes the UI components
     */
    public EnrollmentUI() {
        this.enrollmentService = new EnrollmentService();
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * Main menu display and interaction loop
     */
    public void start() {
        Student student = enrollmentService.getCurrentStudent();
        System.out.println("\n" + "=".repeat(60));
        System.out.println("     COURSE ENROLLMENT SYSTEM");
        System.out.println("=".repeat(60));
        System.out.println("Welcome, " + student.getName() + "!");
        System.out.println("Student ID: " + student.getStudentId());
        System.out.println("Major: " + student.getMajor());
        System.out.println("Completed Courses: " + student.getCompletedCourses());
        
        while (true) {
            displayMenu();
            int choice = getUserChoice();
            
            switch (choice) {
                case 1:
                    displayAvailableCourses();
                    break;
                case 2:
                    enrollInCourse();
                    break;
                case 3:  // Changed from 4 to 3
                    System.out.println("\nThank you for using the Enrollment System!");
                    scanner.close();
                    return;
                default:
                    System.out.println("\n❌ Invalid choice. Please try again.");
            }
        }
    }
    
    /**
     * Displays the main menu options
     */
    private void displayMenu() {
        System.out.println("\n" + "-".repeat(40));
        System.out.println("MAIN MENU");
        System.out.println("-".repeat(40));
        System.out.println("1. View Available Courses");
        System.out.println("2. Enroll in a Course");
        System.out.println("3. Exit");  
        System.out.print("\nEnter your choice (1-3): ");  
    }
    
    /**
     * Gets user input for menu choice
     * @return The user's choice as integer
     */
    private int getUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    /**
     * Displays all available courses with their details
     */
    private void displayAvailableCourses() {
        System.out.println("\n" + "-".repeat(60));
        System.out.println("AVAILABLE COURSES");
        System.out.println("-".repeat(60));
        
        Collection<Course> courses = enrollmentService.getAllCourses();
        Student student = enrollmentService.getCurrentStudent();
        
        for (Course course : courses) {
            // Check if student completed this course
            boolean completed = student.getCompletedCourses().stream()
                .anyMatch(c -> c.equalsIgnoreCase(course.getCourseCode()));
            
            System.out.println(course.toString());
            System.out.println("   Prerequisites: " + 
                (course.getPrerequisites().isEmpty() ? "None" : course.getPrerequisites()));
            System.out.println("   Available Seats: " + course.getAvailableSeats());
            
            // Show completion status
            if (completed) {
                System.out.println("   ⭐ COMPLETED - You have already passed this course");
            }
            
            System.out.println();
        }
    }
    
    /**
     * Handles the course enrollment process
     */
    private void enrollInCourse() {
        System.out.println("\n" + "-".repeat(40));
        System.out.println("COURSE ENROLLMENT");
        System.out.println("-".repeat(40));
        
        System.out.print("\nEnter course code to enroll (e.g., itcs113): ");
        String courseCode = scanner.nextLine().toLowerCase();
        
        EnrollmentService.EnrollmentResult result = 
            enrollmentService.enrollInCourse("S001", courseCode);
        
        System.out.println("\n" + "=".repeat(50));
        if (result.isSuccess()) {
            System.out.println("✅ " + result.getMessage());
        } else {
            System.out.println("❌ " + result.getMessage());
        }
        System.out.println("=".repeat(50));
    }
    
    /**
     * Main method to start the application
     */
    public static void main(String[] args) {
        EnrollmentUI ui = new EnrollmentUI();
        ui.start();
    }
}