package university.util;

/**
 * Utility class for grade calculations and conversions.
 * Implements the 4.0 GPA scale as specified in the project requirements.
 * No inheritance - composition only design.
 * 
 * @author Nurcan Denli Bayir
 * @version 1.0
 */
public final class GradeUtils {

    private GradeUtils() {}

    /**
     * Converts a percentage grade directly to grade points (4.0 scale).
     * Uses the exact mapping specified in the project requirements:
     * 
     * 90-100  -> 4.0
     * 85-89.9 -> 3.7
     * 80-84.9 -> 3.3
     * 75-79.9 -> 3.0
     * 70-74.9 -> 2.7
     * 65-69.9 -> 2.3
     * 60-64.9 -> 2.0
     * 55-59.9 -> 1.7
     * 50-54.9 -> 1.0
     * <50     -> 0.0
     * 
     * @param percent Grade percentage (0-100)
     * @return Grade points on 4.0 scale
     * @throws IllegalArgumentException if percent is out of range
     */
    public static double toGradePoint(double percent) {
        if (percent < 0 || percent > 100) {
            throw new IllegalArgumentException("Grade percentage must be between 0 and 100");
        }

        if (percent >= 90) return 4.0;
        if (percent >= 85) return 3.7;
        if (percent >= 80) return 3.3;
        if (percent >= 75) return 3.0;
        if (percent >= 70) return 2.7;
        if (percent >= 65) return 2.3;
        if (percent >= 60) return 2.0;
        if (percent >= 55) return 1.7;
        if (percent >= 50) return 1.0;
        return 0.0;
    }

    /**
     * Formats a GPA value to 2 decimal places.
     * 
     * @param gpa GPA value
     * @return Formatted GPA string
     */
    public static String formatGPA(double gpa) {
        return String.format("%.2f", gpa);
    }
}
