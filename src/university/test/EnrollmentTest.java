package university.test;

import university.model.Enrollment;

/**
 * Unit tests for Enrollment class.
 * Tests creation, grading, and validation.
 * No inheritance - composition only design.
 * 
 * @author Nurcan Denli Bayir
 * @version 1.0
 */
public final class EnrollmentTest {

    private EnrollmentTest() {}

    public static boolean testCreateUngradedEnrollment() {
        try {
            Enrollment e = new Enrollment(1001, "CS101");
            return e != null && !e.isGraded();
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean testCreateGradedEnrollment() {
        try {
            Enrollment e = new Enrollment(1001, "CS101", 85.0);
            return e != null && e.isGraded() && e.getGradePercent() == 85.0;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean testIsGraded() {
        Enrollment ungraded = new Enrollment(1001, "CS101");
        Enrollment graded = new Enrollment(1001, "CS101", 75.0);
        
        return !ungraded.isGraded() && graded.isGraded();
    }

    public static boolean testSetGrade() {
        Enrollment e = new Enrollment(1001, "CS101");
        e.setGradePercent(90.0);
        return e.isGraded() && e.getGradePercent() == 90.0;
    }

    public static boolean testInvalidStudentIdThrows() {
        try {
            new Enrollment(0, "CS101");
            return false;
        } catch (IllegalArgumentException e) {
            return true;
        }
    }

    public static boolean testNegativeStudentIdThrows() {
        try {
            new Enrollment(-1, "CS101");
            return false;
        } catch (IllegalArgumentException e) {
            return true;
        }
    }

    public static boolean testBlankCourseCodeThrows() {
        try {
            new Enrollment(1001, "");
            return false;
        } catch (IllegalArgumentException e) {
            return true;
        }
    }

    public static boolean testGradeOutOfRangeThrows() {
        try {
            new Enrollment(1001, "CS101", 101.0);
            return false;
        } catch (IllegalArgumentException e) {
            return true;
        }
    }

    public static boolean testNegativeGradeThrows() {
        try {
            new Enrollment(1001, "CS101", -1.0);
            return false;
        } catch (IllegalArgumentException e) {
            return true;
        }
    }

    public static boolean testEnrollmentGetters() {
        Enrollment e = new Enrollment(1001, "CS101", 85.0);
        return e.getStudentId() == 1001 &&
               e.getCourseCode().equals("CS101") &&
               e.getGradePercent() == 85.0;
    }
}
