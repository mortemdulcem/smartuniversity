package university;

import university.model.Course;
import university.model.Enrollment;
import university.model.Student;
import university.service.University;
import university.util.CSVExporter;
import university.util.CSVImporter;
import university.util.GradeUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main CLI application for Smart University Management System.
 * Provides command-line interface for managing students, courses, and enrollments.
 * 
 * @author Nurcan Denli Bayir
 * @version 1.0
 */
public final class Main {
    private static final String DATA_DIR = "data";
    private static final Path STUDENTS_FILE = Paths.get(DATA_DIR, "students.csv");
    private static final Path COURSES_FILE = Paths.get(DATA_DIR, "courses.csv");
    private static final Path ENROLLMENTS_FILE = Paths.get(DATA_DIR, "enrollments.csv");

    private static University university;
    private static Scanner scanner;

    public static void main(String[] args) {
        university = new University();
        scanner = new Scanner(System.in);

        System.out.println("Smart University (no inheritance). Type 'help' for commands.");
        runCommandLoop();

        scanner.close();
        System.out.println("Bye.");
    }

    /**
     * Main command loop - reads and processes user commands
     */
    private static void runCommandLoop() {
        boolean running = true;

        while (running) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                continue;
            }

            running = processCommand(input);
        }
    }

    /**
     * Processes a single command
     * @param input User input string
     * @return false if exit command, true otherwise
     */
    private static boolean processCommand(String input) {
        String[] tokens = parseCommand(input);
        if (tokens.length == 0) {
            return true;
        }

        String command = tokens[0].toLowerCase();

        try {
            switch (command) {
                case "help":
                    printHelp();
                    break;

                case "demo":
                    handleDemo();
                    break;

                case "add-student":
                    handleAddStudent(tokens);
                    break;

                case "add-course":
                    handleAddCourse(tokens);
                    break;

                case "enroll":
                    handleEnroll(tokens);
                    break;

                case "grade":
                    handleGrade(tokens);
                    break;

                case "gpa":
                    handleGPA(tokens);
                    break;

                case "list-students":
                    handleListStudents();
                    break;

                case "list-courses":
                    handleListCourses();
                    break;

                case "list-enrollments":
                    handleListEnrollments();
                    break;

                case "save":
                    handleSave();
                    break;

                case "load":
                    handleLoad();
                    break;

                case "exit":
                    return false;

                default:
                    System.out.println("Unknown command. Type 'help' for available commands.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return true;
    }

    /**
     * Parses command string into tokens, respecting quoted strings
     * @param input Command string
     * @return Array of tokens
     */
    private static String[] parseCommand(String input) {
        List<String> tokens = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ' ' && !inQuotes) {
                if (current.length() > 0) {
                    tokens.add(current.toString());
                    current = new StringBuilder();
                }
            } else {
                current.append(c);
            }
        }

        if (current.length() > 0) {
            tokens.add(current.toString());
        }

        return tokens.toArray(new String[0]);
    }

    /**
     * Prints help menu with all available commands
     */
    private static void printHelp() {
        System.out.println("help");
        System.out.println("demo                                    - Load sample data");
        System.out.println("add-student <id> \"<name>\" \"<major>\"");
        System.out.println("add-course <code> \"<title>\" <credits> \"<instructor>\"");
        System.out.println("enroll <studentId> <courseCode>");
        System.out.println("grade <studentId> <courseCode> <percent>");
        System.out.println("gpa <studentId>");
        System.out.println("list-students");
        System.out.println("list-courses");
        System.out.println("list-enrollments");
        System.out.println("save");
        System.out.println("load");
        System.out.println("exit");
    }

    /**
     * Handles add-student command
     */
    private static void handleAddStudent(String[] tokens) {
        if (tokens.length != 4) {
            System.out.println("Usage: add-student <id> \"<name>\" \"<major>\"");
            return;
        }

        try {
            int id = Integer.parseInt(tokens[1]);
            String name = tokens[2];
            String major = tokens[3];

            university.addStudent(id, name, major);
            System.out.println("Added student.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid student ID.");
        }
    }

    /**
     * Handles add-course command
     */
    private static void handleAddCourse(String[] tokens) {
        if (tokens.length != 5) {
            System.out.println("Usage: add-course <code> \"<title>\" <credits> \"<instructor>\"");
            return;
        }

        try {
            String code = tokens[1];
            String title = tokens[2];
            int credits = Integer.parseInt(tokens[3]);
            String instructor = tokens[4];

            university.addCourse(code, title, credits, instructor);
            System.out.println("Added course.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid credits.");
        }
    }

    /**
     * Handles enroll command
     */
    private static void handleEnroll(String[] tokens) {
        if (tokens.length != 3) {
            System.out.println("Usage: enroll <studentId> <courseCode>");
            return;
        }

        try {
            int studentId = Integer.parseInt(tokens[1]);
            String courseCode = tokens[2];

            university.enroll(studentId, courseCode);
            System.out.println("Enrolled.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid student ID.");
        }
    }

    /**
     * Handles grade command
     */
    private static void handleGrade(String[] tokens) {
        if (tokens.length != 4) {
            System.out.println("Usage: grade <studentId> <courseCode> <percent>");
            return;
        }

        try {
            int studentId = Integer.parseInt(tokens[1]);
            String courseCode = tokens[2];
            double percent = Double.parseDouble(tokens[3]);

            university.assignGrade(studentId, courseCode, percent);
            System.out.println("Grade recorded.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format.");
        }
    }

    /**
     * Handles gpa command
     */
    private static void handleGPA(String[] tokens) {
        if (tokens.length != 2) {
            System.out.println("Usage: gpa <studentId>");
            return;
        }

        try {
            int studentId = Integer.parseInt(tokens[1]);
            double gpa = university.computeGpa(studentId);
            System.out.println("GPA for " + studentId + ": " + GradeUtils.formatGPA(gpa));
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid student ID.");
        }
    }

    /**
     * Handles list-students command
     * Output sorted by id
     */
    private static void handleListStudents() {
        List<Student> students = university.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        for (Student student : students) {
            System.out.println(student.toString());
        }
    }

    /**
     * Handles list-courses command
     * Output sorted by code
     */
    private static void handleListCourses() {
        List<Course> courses = university.getAllCourses();
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }

        for (Course course : courses) {
            System.out.println(course.toString());
        }
    }

    /**
     * Handles list-enrollments command
     * Output sorted by (studentId, courseCode)
     */
    private static void handleListEnrollments() {
        List<Enrollment> enrollments = university.getAllEnrollments();
        if (enrollments.isEmpty()) {
            System.out.println("No enrollments found.");
            return;
        }

        for (Enrollment enrollment : enrollments) {
            System.out.println(enrollment.toString());
        }
    }

    /**
     * Handles save command
     */
    private static void handleSave() {
        try {
            CSVExporter.exportStudents(university.getAllStudents(), STUDENTS_FILE);
            CSVExporter.exportCourses(university.getAllCourses(), COURSES_FILE);
            CSVExporter.exportEnrollments(university.getAllEnrollments(), ENROLLMENTS_FILE);
            System.out.println("Saved to ./data");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    /**
     * Handles load command
     */
    private static void handleLoad() {
        try {
            university.clearAll();

            if (CSVImporter.fileExists(STUDENTS_FILE)) {
                List<Student> students = CSVImporter.importStudents(STUDENTS_FILE);
                for (Student student : students) {
                    try {
                        university.addStudent(student);
                    } catch (IllegalArgumentException e) {
                        System.err.println("Warning: " + e.getMessage());
                    }
                }
            }

            if (CSVImporter.fileExists(COURSES_FILE)) {
                List<Course> courses = CSVImporter.importCourses(COURSES_FILE);
                for (Course course : courses) {
                    try {
                        university.addCourse(course);
                    } catch (IllegalArgumentException e) {
                        System.err.println("Warning: " + e.getMessage());
                    }
                }
            }

            if (CSVImporter.fileExists(ENROLLMENTS_FILE)) {
                List<Enrollment> enrollments = CSVImporter.importEnrollments(ENROLLMENTS_FILE);
                for (Enrollment enrollment : enrollments) {
                    university.addEnrollment(enrollment);
                }
            }

            System.out.println("Loaded from ./data");
        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }

    /**
     * Handles demo command - loads sample data for testing
     */
    private static void handleDemo() {
        try {
            university.clearAll();
            
            // Add sample students
            university.addStudent(1001, "Ali Yilmaz", "Bilgisayar Muhendisligi");
            university.addStudent(1002, "Ayse Demir", "Yazilim Muhendisligi");
            university.addStudent(1003, "Mehmet Kaya", "Elektrik Muhendisligi");
            
            // Add sample courses
            university.addCourse("CS101", "Nesne Yonelimli Programlama", 4, "Prof. Dr. Ahmet Oz");
            university.addCourse("MATH201", "Lineer Cebir", 3, "Doc. Dr. Fatma Ada");
            university.addCourse("EE301", "Devre Analizi", 3, "Dr. Veli Can");
            
            // Enroll students
            university.enroll(1001, "CS101");
            university.enroll(1001, "MATH201");
            university.enroll(1002, "CS101");
            university.enroll(1003, "EE301");
            
            // Assign grades
            university.assignGrade(1001, "CS101", 86);
            university.assignGrade(1001, "MATH201", 72);
            university.assignGrade(1002, "CS101", 90);
            university.assignGrade(1003, "EE301", 78);
            
            System.out.println("Demo data loaded!");
            System.out.println("  3 students, 3 courses, 4 enrollments");
            System.out.println("  Try: list-students, list-courses, gpa 1001");
        } catch (Exception e) {
            System.out.println("Error loading demo data: " + e.getMessage());
        }
    }
}
