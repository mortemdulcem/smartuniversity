package university.util;

import university.model.Course;
import university.model.Enrollment;
import university.model.Student;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Utility class for exporting data to CSV files.
 * Handles proper CSV formatting with quoted strings.
 * No inheritance - composition only design.
 * 
 * @author Nurcan Denli Bayir
 * @version 1.0
 */
public final class CSVExporter {

    private CSVExporter() {}

    /**
     * Exports a list of students to a CSV file.
     * Format: id,name,major
     * 
     * @param students List of students to export
     * @param filePath Path to the output file
     * @throws IOException if file writing fails
     */
    public static void exportStudents(List<Student> students, Path filePath) throws IOException {
        ensureDirectoryExists(filePath);
        
        try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardCharsets.UTF_8)) {
            writer.write("id,name,major");
            writer.newLine();
            
            for (Student student : students) {
                writer.write(student.toCSV());
                writer.newLine();
            }
        }
    }

    /**
     * Exports a list of courses to a CSV file.
     * Format: code,title,credits,instructor
     * 
     * @param courses List of courses to export
     * @param filePath Path to the output file
     * @throws IOException if file writing fails
     */
    public static void exportCourses(List<Course> courses, Path filePath) throws IOException {
        ensureDirectoryExists(filePath);
        
        try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardCharsets.UTF_8)) {
            writer.write("code,title,credits,instructor");
            writer.newLine();
            
            for (Course course : courses) {
                writer.write(course.toCSV());
                writer.newLine();
            }
        }
    }

    /**
     * Exports a list of enrollments to a CSV file.
     * Format: studentId,courseCode,gradePercent
     * 
     * @param enrollments List of enrollments to export
     * @param filePath Path to the output file
     * @throws IOException if file writing fails
     */
    public static void exportEnrollments(List<Enrollment> enrollments, Path filePath) throws IOException {
        ensureDirectoryExists(filePath);
        
        try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardCharsets.UTF_8)) {
            writer.write("studentId,courseCode,gradePercent");
            writer.newLine();
            
            for (Enrollment enrollment : enrollments) {
                writer.write(enrollment.toCSV());
                writer.newLine();
            }
        }
    }

    /**
     * Ensures the parent directory of the file exists.
     * 
     * @param filePath Path to the file
     * @throws IOException if directory creation fails
     */
    private static void ensureDirectoryExists(Path filePath) throws IOException {
        Path parent = filePath.getParent();
        if (parent != null && !Files.exists(parent)) {
            Files.createDirectories(parent);
        }
    }
}
