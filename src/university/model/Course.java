package university.model;

/**
 * Course entity representing a university course.
 * Uses encapsulation with private fields and public getters/setters.
 * No inheritance - composition only design.
 * 
 * @author Nurcan Denli Bayir
 * @version 1.0
 */
public final class Course {
    private String code;
    private String title;
    private int credits;
    private String instructor;

    /**
     * Creates a new Course with the specified details.
     * 
     * @param code Course code (unique identifier, cannot be blank)
     * @param title Course title (cannot be blank)
     * @param credits Credit hours (must be positive)
     * @param instructor Instructor name (cannot be blank)
     * @throws IllegalArgumentException if any validation fails
     */
    public Course(String code, String title, int credits, String instructor) {
        if (code == null || code.trim().isEmpty()) {
            throw new IllegalArgumentException("Course code cannot be blank");
        }
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Course title cannot be blank");
        }
        if (credits <= 0) {
            throw new IllegalArgumentException("Credits must be positive");
        }
        if (instructor == null || instructor.trim().isEmpty()) {
            throw new IllegalArgumentException("Instructor name cannot be blank");
        }
        
        this.code = code.trim().toUpperCase();
        this.title = title.trim();
        this.credits = credits;
        this.instructor = instructor.trim();
    }

    // Getters
    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public int getCredits() {
        return credits;
    }

    public String getInstructor() {
        return instructor;
    }

    // Setters
    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Course title cannot be blank");
        }
        this.title = title.trim();
    }

    public void setCredits(int credits) {
        if (credits <= 0) {
            throw new IllegalArgumentException("Credits must be positive");
        }
        this.credits = credits;
    }

    public void setInstructor(String instructor) {
        if (instructor == null || instructor.trim().isEmpty()) {
            throw new IllegalArgumentException("Instructor name cannot be blank");
        }
        this.instructor = instructor.trim();
    }

    /**
     * Returns a formatted string representation of the course.
     * Format: CODE | Title | Xcr | Instructor
     */
    @Override
    public String toString() {
        return String.format("%s | %s | %dcr | %s", code, title, credits, instructor);
    }

    /**
     * Converts course data to CSV format.
     * @return CSV line: "code","title",credits,"instructor"
     */
    public String toCSV() {
        return String.format("\"%s\",\"%s\",%d,\"%s\"", code, title, credits, instructor);
    }

    /**
     * Creates a Course from CSV line.
     * @param csvLine CSV formatted string
     * @return new Course instance
     */
    public static Course fromCSV(String csvLine) {
        String[] parts = parseCSVLine(csvLine);
        if (parts.length < 4) {
            throw new IllegalArgumentException("Invalid CSV format for Course");
        }
        
        String code = parts[0].trim();
        String title = parts[1].trim();
        int credits = Integer.parseInt(parts[2].trim());
        String instructor = parts[3].trim();
        
        return new Course(code, title, credits, instructor);
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
