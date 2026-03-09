// Business layer class responsible for validation and logic
public class CourseService {

    private CourseRepository repository;

    // Constructor creates the data layer object
    public CourseService() {
        repository = new CourseRepository();
    }

    // Method used by the UI to create and store a course
    public Course addCourse(String id, String name, int credit, String prerequisite) {

        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Course ID cannot be empty.");
        }

        if (credit <= 0) {
            throw new IllegalArgumentException("Credit hours must be greater than 0.");
        }

        if (repository.existsById(id)) {
            throw new IllegalArgumentException("Course ID already exists.");
        }

        Course course = new Course(id, name, credit, prerequisite);
        repository.addCourse(course);

        return course;
    }
}