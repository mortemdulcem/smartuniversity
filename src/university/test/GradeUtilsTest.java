package university.test;

import university.util.GradeUtils;

/**
 * Unit tests for GradeUtils class.
 * Tests grade point mapping and boundary conditions.
 * No inheritance - composition only design.
 * 
 * @author Nurcan Denli Bayir
 * @version 1.0
 */
public final class GradeUtilsTest {

    private GradeUtilsTest() {}

    public static boolean testGradePoint95() {
        return Math.abs(GradeUtils.toGradePoint(95) - 4.0) < 0.001;
    }

    public static boolean testGradePoint90() {
        return Math.abs(GradeUtils.toGradePoint(90) - 4.0) < 0.001;
    }

    public static boolean testGradePoint86() {
        return Math.abs(GradeUtils.toGradePoint(86) - 3.7) < 0.001;
    }

    public static boolean testGradePoint72() {
        return Math.abs(GradeUtils.toGradePoint(72) - 2.7) < 0.001;
    }

    public static boolean testGradePoint50() {
        return Math.abs(GradeUtils.toGradePoint(50) - 1.0) < 0.001;
    }

    public static boolean testGradePoint45() {
        return Math.abs(GradeUtils.toGradePoint(45) - 0.0) < 0.001;
    }

    public static boolean testBoundary899() {
        return Math.abs(GradeUtils.toGradePoint(89.9) - 3.7) < 0.001;
    }

    public static boolean testBoundary85() {
        return Math.abs(GradeUtils.toGradePoint(85) - 3.7) < 0.001;
    }

    public static boolean testAllBoundaries() {
        boolean allPass = true;
        
        allPass &= GradeUtils.toGradePoint(100) == 4.0;
        allPass &= GradeUtils.toGradePoint(90) == 4.0;
        allPass &= GradeUtils.toGradePoint(89.9) == 3.7;
        allPass &= GradeUtils.toGradePoint(85) == 3.7;
        allPass &= GradeUtils.toGradePoint(84.9) == 3.3;
        allPass &= GradeUtils.toGradePoint(80) == 3.3;
        allPass &= GradeUtils.toGradePoint(79.9) == 3.0;
        allPass &= GradeUtils.toGradePoint(75) == 3.0;
        allPass &= GradeUtils.toGradePoint(74.9) == 2.7;
        allPass &= GradeUtils.toGradePoint(70) == 2.7;
        allPass &= GradeUtils.toGradePoint(69.9) == 2.3;
        allPass &= GradeUtils.toGradePoint(65) == 2.3;
        allPass &= GradeUtils.toGradePoint(64.9) == 2.0;
        allPass &= GradeUtils.toGradePoint(60) == 2.0;
        allPass &= GradeUtils.toGradePoint(59.9) == 1.7;
        allPass &= GradeUtils.toGradePoint(55) == 1.7;
        allPass &= GradeUtils.toGradePoint(54.9) == 1.0;
        allPass &= GradeUtils.toGradePoint(50) == 1.0;
        allPass &= GradeUtils.toGradePoint(49.9) == 0.0;
        allPass &= GradeUtils.toGradePoint(0) == 0.0;
        
        return allPass;
    }
}
