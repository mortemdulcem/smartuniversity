package university.test;

import university.model.Course;

/**
 * Unit tests for Course class.
 * Tests creation, validation, and CSV operations.
 * No inheritance - composition only design.
 * 
 * @author Nurcan Denli Bayir
 * @version 1.0
 */
public final class CourseTest {

    private CourseTest() {}

    public static boolean testCreateValidCourse() {
        try {
            Course c = new Course("CS101", "Programming", 4, "Dr. Smith");
            return c != null;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean testCourseGetters() {
        Course c = new Course("CS101", "Object-Oriented Programming", 4, "Dr. Smith");
        return c.getCode().equals("CS101") &&
               c.getTitle().equals("Object-Oriented Programming") &&
               c.getCredits() == 4 &&
               c.getInstructor().equals("Dr. Smith");
    }

    public static boolean testInvalidCreditsThrows() {
        try {
            new Course("CS101", "Test", 0, "Dr. Test");
            return false;
        } catch (IllegalArgumentException e) {
            return true;
        }
    }

    public static boolean testNegativeCreditsThrows() {
        try {
            new Course("CS101", "Test", -1, "Dr. Test");
            return false;
        } catch (IllegalArgumentException e) {
            return true;
        }
    }

    public static boolean testBlankCodeThrows() {
        try {
            new Course("", "Test", 3, "Dr. Test");
            return false;
        } catch (IllegalArgumentException e) {
            return true;
        }
    }

    public static boolean testBlankTitleThrows() {
        try {
            new Course("CS101", "", 3, "Dr. Test");
            return false;
        } catch (IllegalArgumentException e) {
            return true;
        }
    }

    public static boolean testBlankInstructorThrows() {
        try {
            new Course("CS101", "Test", 3, "");
            return false;
        } catch (IllegalArgumentException e) {
            return true;
        }
    }

    public static boolean testToCSV() {
        Course c = new Course("CS101", "Programming", 4, "Dr. Smith");
        String csv = c.toCSV();
        return csv.contains("CS101") && csv.contains("Programming") && 
               csv.contains("4") && csv.contains("Dr. Smith");
    }

    public static boolean testFromCSV() {
        try {
            Course c = Course.fromCSV("\"CS101\",\"Object-Oriented Programming\",4,\"Dr. Smith\"");
            return c.getCode().equals("CS101") &&
                   c.getTitle().equals("Object-Oriented Programming") &&
                   c.getCredits() == 4 &&
                   c.getInstructor().equals("Dr. Smith");
        } catch (Exception e) {
            return false;
        }
    }
}
