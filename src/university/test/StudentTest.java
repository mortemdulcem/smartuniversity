package university.test;

import university.model.Student;

/**
 * Unit tests for Student class.
 * Tests creation, validation, and CSV operations.
 * No inheritance - composition only design.
 * 
 * @author Nurcan Denli Bayir
 * @version 1.0
 */
public final class StudentTest {

    private StudentTest() {}

    public static boolean testCreateValidStudent() {
        try {
            Student s = new Student(1001, "Ali Veli", "CS");
            return s != null;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean testStudentGetters() {
        Student s = new Student(1001, "Ali Veli", "Computer Science");
        return s.getId() == 1001 &&
               s.getName().equals("Ali Veli") &&
               s.getMajor().equals("Computer Science");
    }

    public static boolean testInvalidIdThrows() {
        try {
            new Student(0, "Test", "CS");
            return false;
        } catch (IllegalArgumentException e) {
            return true;
        }
    }

    public static boolean testNegativeIdThrows() {
        try {
            new Student(-1, "Test", "CS");
            return false;
        } catch (IllegalArgumentException e) {
            return true;
        }
    }

    public static boolean testBlankNameThrows() {
        try {
            new Student(1, "", "CS");
            return false;
        } catch (IllegalArgumentException e) {
            return true;
        }
    }

    public static boolean testNullNameThrows() {
        try {
            new Student(1, null, "CS");
            return false;
        } catch (IllegalArgumentException e) {
            return true;
        }
    }

    public static boolean testBlankMajorThrows() {
        try {
            new Student(1, "Test", "");
            return false;
        } catch (IllegalArgumentException e) {
            return true;
        }
    }

    public static boolean testToCSV() {
        Student s = new Student(1001, "Alice Johnson", "CS");
        String csv = s.toCSV();
        return csv.contains("1001") && csv.contains("Alice Johnson") && csv.contains("CS");
    }

    public static boolean testFromCSV() {
        try {
            Student s = Student.fromCSV("1001,\"Alice Johnson\",\"CS\"");
            return s.getId() == 1001 &&
                   s.getName().equals("Alice Johnson") &&
                   s.getMajor().equals("CS");
        } catch (Exception e) {
            return false;
        }
    }
}
