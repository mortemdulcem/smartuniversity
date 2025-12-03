package university.util;

import university.model.Course;
import university.model.Enrollment;
import university.model.Student;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for importing data from CSV files.
 * Handles CSV parsing with quoted strings and various formats.
 * No inheritance - composition only design.
 * 
 * @author Nurcan Denli Bayir
 * @version 1.0
 */
public final class CSVImporter {

    private CSVImporter() {}

    /**
     * Imports students from a CSV file.
     * Expected format: id,name,major (with header row)
     * 
     * @param filePath Path to the CSV file
     * @return List of imported students
     * @throws IOException if file reading fails
     */
    public static List<Student> importStudents(Path filePath) throws IOException {
        List<Student> students = new ArrayList<>();
        
        try (BufferedReader reader = Files.newBufferedReader(filePath, StandardCharsets.UTF_8)) {
            String line;
            boolean isFirstLine = true;
            int lineNumber = 0;
            
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                line = line.trim();
                
                if (line.isEmpty()) {
                    continue;
                }
                
                if (isFirstLine) {
                    isFirstLine = false;
                    if (line.toLowerCase().contains("id") && line.toLowerCase().contains("name")) {
                        continue;
                    }
                }
                
                try {
                    Student student = Student.fromCSV(line);
                    students.add(student);
                } catch (Exception e) {
                    System.err.println("Warning: Skipping invalid student at line " + lineNumber + ": " + e.getMessage());
                }
            }
        }
        
        return students;
    }

    /**
     * Imports courses from a CSV file.
     * Expected format: code,title,credits,instructor (with header row)
     * 
     * @param filePath Path to the CSV file
     * @return List of imported courses
     * @throws IOException if file reading fails
     */
    public static List<Course> importCourses(Path filePath) throws IOException {
        List<Course> courses = new ArrayList<>();
        
        try (BufferedReader reader = Files.newBufferedReader(filePath, StandardCharsets.UTF_8)) {
            String line;
            boolean isFirstLine = true;
            int lineNumber = 0;
            
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                line = line.trim();
                
                if (line.isEmpty()) {
                    continue;
                }
                
                if (isFirstLine) {
                    isFirstLine = false;
                    if (line.toLowerCase().contains("code") && line.toLowerCase().contains("title")) {
                        continue;
                    }
                }
                
                try {
                    Course course = Course.fromCSV(line);
                    courses.add(course);
                } catch (Exception e) {
                    System.err.println("Warning: Skipping invalid course at line " + lineNumber + ": " + e.getMessage());
                }
            }
        }
        
        return courses;
    }

    /**
     * Imports enrollments from a CSV file.
     * Expected format: studentId,courseCode,gradePercent (with header row)
     * 
     * @param filePath Path to the CSV file
     * @return List of imported enrollments
     * @throws IOException if file reading fails
     */
    public static List<Enrollment> importEnrollments(Path filePath) throws IOException {
        List<Enrollment> enrollments = new ArrayList<>();
        
        try (BufferedReader reader = Files.newBufferedReader(filePath, StandardCharsets.UTF_8)) {
            String line;
            boolean isFirstLine = true;
            int lineNumber = 0;
            
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                line = line.trim();
                
                if (line.isEmpty()) {
                    continue;
                }
                
                if (isFirstLine) {
                    isFirstLine = false;
                    if (line.toLowerCase().contains("studentid") || 
                        (line.toLowerCase().contains("student") && line.toLowerCase().contains("course"))) {
                        continue;
                    }
                }
                
                try {
                    Enrollment enrollment = Enrollment.fromCSV(line);
                    enrollments.add(enrollment);
                } catch (Exception e) {
                    System.err.println("Warning: Skipping invalid enrollment at line " + lineNumber + ": " + e.getMessage());
                }
            }
        }
        
        return enrollments;
    }

    /**
     * Checks if a CSV file exists and is readable.
     * 
     * @param filePath Path to check
     * @return true if file exists and is readable
     */
    public static boolean fileExists(Path filePath) {
        return Files.exists(filePath) && Files.isReadable(filePath);
    }
}
