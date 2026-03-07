import java.util.Scanner;

class Course {

    private String courseId;
    private String courseName;
    private int creditHours;
    private String prerequisite;

    // constructor to create a course object
    public Course(String courseId, String courseName, int creditHours, String prerequisite) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.creditHours = creditHours;
        this.prerequisite = prerequisite;
    }

    // method to display course information
    public void displayCourse() {
        System.out.println("\nCourse Details:");
        System.out.println("Course ID: " + courseId);
        System.out.println("Course Name: " + courseName);
        System.out.println("Credit Hours: " + creditHours);
        System.out.println("Prerequisite: " + prerequisite);
    }
}

public class CreateCourse {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("===== Create Course =====");

        // ask admin to enter course id
        System.out.print("Enter Course ID: ");
        String id = input.nextLine();

        // ask admin to enter course name
        System.out.print("Enter Course Name: ");
        String name = input.nextLine();

        // ask admin to enter credit hours
        System.out.print("Enter Credit Hours: ");
        int credit = input.nextInt();
        input.nextLine();

        // ask admin to enter prerequisite course
        System.out.print("Enter Prerequisite Course (or None): ");
        String prerequisite = input.nextLine();

        // create a new course object using the entered data
        Course course = new Course(id, name, credit, prerequisite);

        // confirmation message
        System.out.println("\n Course added successfully!");
        course.displayCourse();
        input.close();
    }
}