// Model class that stores course information
public class Course {
    private String courseId;
    private String courseName;
    private int creditHours;
    private String prerequisite;

    // Constructor to initialize course details
    public Course(String courseId, String courseName, int creditHours, String prerequisite) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.creditHours = creditHours;
        this.prerequisite = prerequisite;
    }

    public String getCourseId() {
        return courseId;
    }

    // Returns formatted course details for display
    public String getCourseDetails() {
        return "Course Details:\n"
                + "Course ID: " + courseId + "\n"
                + "Course Name: " + courseName + "\n"
                + "Credit Hours: " + creditHours + "\n"
                + "Prerequisite: " + prerequisite;
    }
}