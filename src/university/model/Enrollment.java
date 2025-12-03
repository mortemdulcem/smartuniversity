package university.model;

/**
 * Enrollment entity representing a student's enrollment in a course.
 * Uses encapsulation with private fields and public getters/setters.
 * No inheritance - composition only design.
 * 
 * @author Nurcan Denli Bayir
 * @version 1.0
 */
public final class Enrollment {
    private int studentId;
    private String courseCode;
    private Double gradePercent;

    /**
     * Creates a new Enrollment with the specified details.
     * 
     * @param studentId Student ID (must be positive)
     * @param courseCode Course code (cannot be blank)
     * @throws IllegalArgumentException if any validation fails
     */
    public Enrollment(int studentId, String courseCode) {
        if (studentId <= 0) {
            throw new IllegalArgumentException("Student ID must be positive");
        }
        if (courseCode == null || courseCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Course code cannot be blank");
        }
        
        this.studentId = studentId;
        this.courseCode = courseCode.trim().toUpperCase();
        this.gradePercent = null;
    }

    /**
     * Creates an Enrollment with a grade (for CSV import).
     */
    public Enrollment(int studentId, String courseCode, Double gradePercent) {
        if (studentId <= 0) {
            throw new IllegalArgumentException("Student ID must be positive");
        }
        if (courseCode == null || courseCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Course code cannot be blank");
        }
        if (gradePercent != null && (gradePercent < 0 || gradePercent > 100)) {
            throw new IllegalArgumentException("Grade must be between 0 and 100");
        }
        
        this.studentId = studentId;
        this.courseCode = courseCode.trim().toUpperCase();
        this.gradePercent = gradePercent;
    }

    // Getters
    public int getStudentId() {
        return studentId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public Double getGradePercent() {
        return gradePercent;
    }

    /**
     * Checks if this enrollment has been graded.
     * @return true if a grade has been assigned
     */
    public boolean isGraded() {
        return gradePercent != null;
    }

    // Setters
    /**
     * Assigns a grade to this enrollment.
     * @param gradePercent Grade percentage (0-100)
     * @throws IllegalArgumentException if grade is out of range
     */
    public void setGradePercent(Double gradePercent) {
        if (gradePercent != null) {
            if (gradePercent < 0 || gradePercent > 100) {
                throw new IllegalArgumentException("Grade must be between 0 and 100");
            }
        }
        this.gradePercent = gradePercent;
    }

    /**
     * Returns a formatted string representation of the enrollment.
     */
    @Override
    public String toString() {
        String gradeStr = gradePercent != null ? 
                String.format("%.1f", gradePercent) : "";
        return String.format("%d,%s,%s", studentId, courseCode, gradeStr);
    }

    /**
     * Converts enrollment data to CSV format.
     * @return CSV line: studentId,courseCode,gradePercent
     */
    public String toCSV() {
        String gradeStr = gradePercent != null ? String.format("%.1f", gradePercent) : "";
        return String.format("%d,%s,%s", studentId, courseCode, gradeStr);
    }

    /**
     * Creates an Enrollment from CSV line.
     * @param csvLine CSV formatted string
     * @return new Enrollment instance
     */
    public static Enrollment fromCSV(String csvLine) {
        String[] parts = parseCSVLine(csvLine);
        if (parts.length < 2) {
            throw new IllegalArgumentException("Invalid CSV format for Enrollment");
        }
        
        int studentId = Integer.parseInt(parts[0].trim());
        String courseCode = parts[1].trim();
        
        Double gradePercent = null;
        if (parts.length >= 3 && !parts[2].trim().isEmpty()) {
            gradePercent = Double.parseDouble(parts[2].trim());
        }
        
        return new Enrollment(studentId, courseCode, gradePercent);
    }

    /**
     * Parses a CSV line respecting quoted fields.
     */
    private static String[] parseCSVLine(String line) {
        java.util.List<String> result = new java.util.ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;
        
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            
            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                result.add(current.toString());
                current = new StringBuilder();
            } else {
                current.append(c);
            }
        }
        result.add(current.toString());
        
        return result.toArray(new String[0]);
    }
}
