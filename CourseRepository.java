import java.util.ArrayList;

// Data layer class responsible for storing courses
public class CourseRepository {
    private ArrayList<Course> courses = new ArrayList<>();

    // Adds a course to the data storage
    public void addCourse(Course course) {
        courses.add(course);
    }

    // Checks if a course ID already exists
    public boolean existsById(String id) {
        for (Course course : courses) {
            if (course.getCourseId().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }
}