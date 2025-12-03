package university.model;

/**
 * Student entity representing a university student.
 * Uses encapsulation with private fields and public getters/setters.
 * No inheritance - composition only design.
 * 
 * @author Nurcan Denli Bayir
 * @version 1.0
 */
public final class Student {
    private int id;
    private String name;
    private String major;

    /**
     * Creates a new Student with the specified details.
     * 
     * @param id Student ID (must be positive)
     * @param name Student full name (cannot be blank)
     * @param major Student's major/department (cannot be blank)
     * @throws IllegalArgumentException if any validation fails
     */
    public Student(int id, String name, String major) {
        if (id <= 0) {
            throw new IllegalArgumentException("Student ID must be positive");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be blank");
        }
        if (major == null || major.trim().isEmpty()) {
            throw new IllegalArgumentException("Student major cannot be blank");
        }
        
        this.id = id;
        this.name = name.trim();
        this.major = major.trim();
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMajor() {
        return major;
    }

    // Setters
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be blank");
        }
        this.name = name.trim();
    }

    public void setMajor(String major) {
        if (major == null || major.trim().isEmpty()) {
            throw new IllegalArgumentException("Student major cannot be blank");
        }
        this.major = major.trim();
    }

    /**
     * Returns a formatted string representation of the student.
     * Format: ID | Name | Major
     */
    @Override
    public String toString() {
        return String.format("%d | %s | %s", id, name, major);
    }

    /**
     * Converts student data to CSV format.
     * @return CSV line: id,"name","major"
     */
    public String toCSV() {
        return String.format("%d,\"%s\",\"%s\"", id, name, major);
    }

    /**
     * Creates a Student from CSV line.
     * @param csvLine CSV formatted string
     * @return new Student instance
     */
    public static Student fromCSV(String csvLine) {
        String[] parts = parseCSVLine(csvLine);
        if (parts.length < 3) {
            throw new IllegalArgumentException("Invalid CSV format for Student");
        }
        
        int id = Integer.parseInt(parts[0].trim());
        String name = parts[1].trim();
        String major = parts[2].trim();
        
        return new Student(id, name, major);
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
