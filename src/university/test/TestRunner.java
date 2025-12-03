package university.test;

/**
 * Main test runner for Smart University System.
 * Runs all unit tests and reports results.
 * No inheritance - composition only design.
 * 
 * @author Nurcan Denli Bayir
 * @version 1.0
 */
public final class TestRunner {

    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("  Smart University - Unit Tests");
        System.out.println("========================================\n");

        runGradeUtilsTests();
        runStudentTests();
        runCourseTests();
        runEnrollmentTests();
        runUniversityTests();
        runGPATests();

        System.out.println("\n========================================");
        System.out.println("  TEST RESULTS");
        System.out.println("========================================");
        System.out.println("  Passed: " + passed);
        System.out.println("  Failed: " + failed);
        System.out.println("  Total:  " + (passed + failed));
        System.out.println("========================================");

        if (failed > 0) {
            System.exit(1);
        }
    }

    private static void runGradeUtilsTests() {
        System.out.println("[GradeUtils Tests]");
        
        test("toGradePoint(95) == 4.0", 
            GradeUtilsTest.testGradePoint95());
        test("toGradePoint(90) == 4.0", 
            GradeUtilsTest.testGradePoint90());
        test("toGradePoint(86) == 3.7", 
            GradeUtilsTest.testGradePoint86());
        test("toGradePoint(72) == 2.7", 
            GradeUtilsTest.testGradePoint72());
        test("toGradePoint(50) == 1.0", 
            GradeUtilsTest.testGradePoint50());
        test("toGradePoint(45) == 0.0", 
            GradeUtilsTest.testGradePoint45());
        test("toGradePoint boundary 89.9 == 3.7", 
            GradeUtilsTest.testBoundary899());
        test("toGradePoint boundary 85 == 3.7", 
            GradeUtilsTest.testBoundary85());
        
        System.out.println();
    }

    private static void runStudentTests() {
        System.out.println("[Student Tests]");
        
        test("Create valid student", 
            StudentTest.testCreateValidStudent());
        test("Student getters work correctly", 
            StudentTest.testStudentGetters());
        test("Invalid ID throws exception", 
            StudentTest.testInvalidIdThrows());
        test("Blank name throws exception", 
            StudentTest.testBlankNameThrows());
        test("Student toCSV format", 
            StudentTest.testToCSV());
        test("Student fromCSV parsing", 
            StudentTest.testFromCSV());
        
        System.out.println();
    }

    private static void runCourseTests() {
        System.out.println("[Course Tests]");
        
        test("Create valid course", 
            CourseTest.testCreateValidCourse());
        test("Course getters work correctly", 
            CourseTest.testCourseGetters());
        test("Invalid credits throws exception", 
            CourseTest.testInvalidCreditsThrows());
        test("Blank code throws exception", 
            CourseTest.testBlankCodeThrows());
        test("Course toCSV format", 
            CourseTest.testToCSV());
        test("Course fromCSV parsing", 
            CourseTest.testFromCSV());
        
        System.out.println();
    }

    private static void runEnrollmentTests() {
        System.out.println("[Enrollment Tests]");
        
        test("Create ungraded enrollment", 
            EnrollmentTest.testCreateUngradedEnrollment());
        test("Create graded enrollment", 
            EnrollmentTest.testCreateGradedEnrollment());
        test("isGraded returns correct value", 
            EnrollmentTest.testIsGraded());
        test("Invalid studentId throws exception", 
            EnrollmentTest.testInvalidStudentIdThrows());
        test("Grade out of range throws exception", 
            EnrollmentTest.testGradeOutOfRangeThrows());
        
        System.out.println();
    }

    private static void runUniversityTests() {
        System.out.println("[University Tests]");
        
        test("Add and retrieve student", 
            UniversityTest.testAddAndGetStudent());
        test("Add and retrieve course", 
            UniversityTest.testAddAndGetCourse());
        test("Enroll student in course", 
            UniversityTest.testEnrollStudent());
        test("Assign grade to enrollment", 
            UniversityTest.testAssignGrade());
        test("Duplicate student throws exception", 
            UniversityTest.testDuplicateStudentThrows());
        test("Duplicate course throws exception", 
            UniversityTest.testDuplicateCourseThrows());
        test("Duplicate enrollment throws exception", 
            UniversityTest.testDuplicateEnrollmentThrows());
        test("Enroll non-existent student throws", 
            UniversityTest.testEnrollNonExistentStudentThrows());
        test("Enroll in non-existent course throws", 
            UniversityTest.testEnrollNonExistentCourseThrows());
        
        System.out.println();
    }

    private static void runGPATests() {
        System.out.println("[GPA Calculation Tests]");
        
        test("GPA calculation (86% + 72%) = 3.27", 
            GPATest.testGPACalculation());
        test("GPA with no graded enrollments = 0.0", 
            GPATest.testGPANoGradedEnrollments());
        test("GPA single course 90% = 4.0", 
            GPATest.testGPASingleCourse());
        test("GPA credit weighting is correct", 
            GPATest.testGPACreditWeighting());
        
        System.out.println();
    }

    private static void test(String name, boolean result) {
        if (result) {
            System.out.println("  [PASS] " + name);
            passed++;
        } else {
            System.out.println("  [FAIL] " + name);
            failed++;
        }
    }
}
