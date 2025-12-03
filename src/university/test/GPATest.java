package university.test;

import university.service.University;

/**
 * Unit tests for GPA calculation.
 * Tests credit-weighted average and edge cases.
 * No inheritance - composition only design.
 * 
 * @author Nurcan Denli Bayir
 * @version 1.0
 */
public final class GPATest {

    private GPATest() {}

    /**
     * Tests the main GPA calculation from the PDF sample:
     * CS101: 4 credits, 86% -> 3.7 grade points
     * MATH201: 3 credits, 72% -> 2.7 grade points
     * GPA = (3.7*4 + 2.7*3) / (4+3) = (14.8 + 8.1) / 7 = 22.9 / 7 = 3.27
     */
    public static boolean testGPACalculation() {
        University uni = new University();
        uni.addStudent(1001, "Alice", "CS");
        uni.addCourse("CS101", "Programming", 4, "Dr. Smith");
        uni.addCourse("MATH201", "Discrete Math", 3, "Dr. Ada");
        uni.enroll(1001, "CS101");
        uni.enroll(1001, "MATH201");
        uni.assignGrade(1001, "CS101", 86);
        uni.assignGrade(1001, "MATH201", 72);
        
        double gpa = uni.computeGpa(1001);
        double expected = 3.27;
        
        return Math.abs(gpa - expected) < 0.01;
    }

    /**
     * Tests GPA with no graded enrollments returns 0.0
     */
    public static boolean testGPANoGradedEnrollments() {
        University uni = new University();
        uni.addStudent(1001, "Alice", "CS");
        uni.addCourse("CS101", "Programming", 4, "Dr. Smith");
        uni.enroll(1001, "CS101");
        
        double gpa = uni.computeGpa(1001);
        return gpa == 0.0;
    }

    /**
     * Tests GPA with single course at 90% = 4.0
     */
    public static boolean testGPASingleCourse() {
        University uni = new University();
        uni.addStudent(1001, "Alice", "CS");
        uni.addCourse("CS101", "Programming", 4, "Dr. Smith");
        uni.enroll(1001, "CS101");
        uni.assignGrade(1001, "CS101", 90);
        
        double gpa = uni.computeGpa(1001);
        return Math.abs(gpa - 4.0) < 0.001;
    }

    /**
     * Tests that credit weighting is correct.
     * Course A: 1 credit, 90% -> 4.0
     * Course B: 4 credits, 50% -> 1.0
     * GPA = (4.0*1 + 1.0*4) / (1+4) = (4.0 + 4.0) / 5 = 8.0 / 5 = 1.6
     */
    public static boolean testGPACreditWeighting() {
        University uni = new University();
        uni.addStudent(1001, "Alice", "CS");
        uni.addCourse("CS101", "Easy", 1, "Dr. A");
        uni.addCourse("CS201", "Hard", 4, "Dr. B");
        uni.enroll(1001, "CS101");
        uni.enroll(1001, "CS201");
        uni.assignGrade(1001, "CS101", 90);
        uni.assignGrade(1001, "CS201", 50);
        
        double gpa = uni.computeGpa(1001);
        double expected = 1.6;
        
        return Math.abs(gpa - expected) < 0.01;
    }

    /**
     * Tests GPA with failing grade (below 50%)
     */
    public static boolean testGPAWithFailingGrade() {
        University uni = new University();
        uni.addStudent(1001, "Alice", "CS");
        uni.addCourse("CS101", "Programming", 4, "Dr. Smith");
        uni.enroll(1001, "CS101");
        uni.assignGrade(1001, "CS101", 40);
        
        double gpa = uni.computeGpa(1001);
        return gpa == 0.0;
    }

    /**
     * Tests GPA with perfect score (100%)
     */
    public static boolean testGPAPerfectScore() {
        University uni = new University();
        uni.addStudent(1001, "Alice", "CS");
        uni.addCourse("CS101", "Programming", 4, "Dr. Smith");
        uni.enroll(1001, "CS101");
        uni.assignGrade(1001, "CS101", 100);
        
        double gpa = uni.computeGpa(1001);
        return Math.abs(gpa - 4.0) < 0.001;
    }

    /**
     * Tests GPA ignores ungraded enrollments in denominator
     */
    public static boolean testGPAIgnoresUngraded() {
        University uni = new University();
        uni.addStudent(1001, "Alice", "CS");
        uni.addCourse("CS101", "Programming", 4, "Dr. Smith");
        uni.addCourse("CS201", "Advanced", 3, "Dr. Jones");
        uni.enroll(1001, "CS101");
        uni.enroll(1001, "CS201");
        uni.assignGrade(1001, "CS101", 90);
        
        double gpa = uni.computeGpa(1001);
        return Math.abs(gpa - 4.0) < 0.001;
    }
}
