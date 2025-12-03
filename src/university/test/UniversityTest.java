package university.test;

import university.service.University;
import university.model.Student;
import university.model.Course;

/**
 * Unit tests for University service class.
 * Tests CRUD operations and business logic.
 * No inheritance - composition only design.
 * 
 * @author Nurcan Denli Bayir
 * @version 1.0
 */
public final class UniversityTest {

    private UniversityTest() {}

    public static boolean testAddAndGetStudent() {
        University uni = new University();
        uni.addStudent(1001, "Alice Johnson", "CS");
        Student s = uni.getStudent(1001);
        return s != null && s.getName().equals("Alice Johnson");
    }

    public static boolean testAddAndGetCourse() {
        University uni = new University();
        uni.addCourse("CS101", "Programming", 4, "Dr. Smith");
        Course c = uni.getCourse("CS101");
        return c != null && c.getTitle().equals("Programming");
    }

    public static boolean testEnrollStudent() {
        University uni = new University();
        uni.addStudent(1001, "Alice", "CS");
        uni.addCourse("CS101", "Programming", 4, "Dr. Smith");
        
        try {
            uni.enroll(1001, "CS101");
            return uni.getEnrollmentCount() == 1;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean testAssignGrade() {
        University uni = new University();
        uni.addStudent(1001, "Alice", "CS");
        uni.addCourse("CS101", "Programming", 4, "Dr. Smith");
        uni.enroll(1001, "CS101");
        
        try {
            uni.assignGrade(1001, "CS101", 85.0);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean testDuplicateStudentThrows() {
        University uni = new University();
        uni.addStudent(1001, "Alice", "CS");
        
        try {
            uni.addStudent(1001, "Bob", "SE");
            return false;
        } catch (IllegalArgumentException e) {
            return e.getMessage().contains("already exists");
        }
    }

    public static boolean testDuplicateCourseThrows() {
        University uni = new University();
        uni.addCourse("CS101", "Programming", 4, "Dr. Smith");
        
        try {
            uni.addCourse("CS101", "Another Course", 3, "Dr. Jones");
            return false;
        } catch (IllegalArgumentException e) {
            return e.getMessage().contains("already exists");
        }
    }

    public static boolean testDuplicateEnrollmentThrows() {
        University uni = new University();
        uni.addStudent(1001, "Alice", "CS");
        uni.addCourse("CS101", "Programming", 4, "Dr. Smith");
        uni.enroll(1001, "CS101");
        
        try {
            uni.enroll(1001, "CS101");
            return false;
        } catch (IllegalArgumentException e) {
            return e.getMessage().contains("already enrolled");
        }
    }

    public static boolean testEnrollNonExistentStudentThrows() {
        University uni = new University();
        uni.addCourse("CS101", "Programming", 4, "Dr. Smith");
        
        try {
            uni.enroll(9999, "CS101");
            return false;
        } catch (IllegalArgumentException e) {
            return e.getMessage().contains("not found");
        }
    }

    public static boolean testEnrollNonExistentCourseThrows() {
        University uni = new University();
        uni.addStudent(1001, "Alice", "CS");
        
        try {
            uni.enroll(1001, "FAKE999");
            return false;
        } catch (IllegalArgumentException e) {
            return e.getMessage().contains("not found");
        }
    }

    public static boolean testGradeNonExistentEnrollmentThrows() {
        University uni = new University();
        uni.addStudent(1001, "Alice", "CS");
        uni.addCourse("CS101", "Programming", 4, "Dr. Smith");
        
        try {
            uni.assignGrade(1001, "CS101", 85.0);
            return false;
        } catch (IllegalArgumentException e) {
            return e.getMessage().contains("not found");
        }
    }

    public static boolean testListStudentsSorted() {
        University uni = new University();
        uni.addStudent(1003, "Charlie", "CS");
        uni.addStudent(1001, "Alice", "CS");
        uni.addStudent(1002, "Bob", "CS");
        
        java.util.List<Student> students = uni.getAllStudents();
        return students.get(0).getId() == 1001 &&
               students.get(1).getId() == 1002 &&
               students.get(2).getId() == 1003;
    }

    public static boolean testListCoursesSorted() {
        University uni = new University();
        uni.addCourse("MATH201", "Math", 3, "Dr. A");
        uni.addCourse("CS101", "CS", 4, "Dr. B");
        uni.addCourse("EE301", "EE", 3, "Dr. C");
        
        java.util.List<Course> courses = uni.getAllCourses();
        return courses.get(0).getCode().equals("CS101") &&
               courses.get(1).getCode().equals("EE301") &&
               courses.get(2).getCode().equals("MATH201");
    }
}
