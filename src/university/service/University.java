package university.service;

import university.model.Course;
import university.model.Enrollment;
import university.model.Student;
import university.util.GradeUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Main service class for university operations.
 * Manages students, courses, and enrollments using composition.
 * No inheritance - composition only design.
 * 
 * @author Nurcan Denli Bayir
 * @version 1.0
 */
public final class University {
    private final Map<Integer, Student> students;
    private final Map<String, Course> courses;
    private final List<Enrollment> enrollments;

    /**
     * Creates a new University instance with empty collections.
     */
    public University() {
        this.students = new HashMap<>();
        this.courses = new HashMap<>();
        this.enrollments = new ArrayList<>();
    }

    // ==================== STUDENT OPERATIONS ====================

    /**
     * Adds a new student to the university.
     * 
     * @param id Student ID
     * @param name Student name
     * @param major Student's major
     * @throws IllegalArgumentException if student ID already exists
     */
    public void addStudent(int id, String name, String major) {
        if (students.containsKey(id)) {
            throw new IllegalArgumentException("Student with ID " + id + " already exists");
        }
        Student student = new Student(id, name, major);
        students.put(id, student);
    }

    /**
     * Adds an existing Student object.
     * 
     * @param student Student to add
     * @throws IllegalArgumentException if student ID already exists
     */
    public void addStudent(Student student) {
        if (students.containsKey(student.getId())) {
            throw new IllegalArgumentException("Student with ID " + student.getId() + " already exists");
        }
        students.put(student.getId(), student);
    }

    /**
     * Gets a student by ID.
     * 
     * @param id Student ID
     * @return Student or null if not found
     */
    public Student getStudent(int id) {
        return students.get(id);
    }

    /**
     * Gets all students as a list, sorted by ID.
     * 
     * @return List of all students sorted by id
     */
    public List<Student> getAllStudents() {
        return students.values().stream()
                .sorted(Comparator.comparingInt(Student::getId))
                .collect(Collectors.toList());
    }

    /**
     * Gets the total number of students.
     * 
     * @return Student count
     */
    public int getStudentCount() {
        return students.size();
    }

    // ==================== COURSE OPERATIONS ====================

    /**
     * Adds a new course to the university.
     * 
     * @param code Course code
     * @param title Course title
     * @param credits Credit hours
     * @param instructor Instructor name
     * @throws IllegalArgumentException if course code already exists
     */
    public void addCourse(String code, String title, int credits, String instructor) {
        String normalizedCode = code.trim().toUpperCase();
        if (courses.containsKey(normalizedCode)) {
            throw new IllegalArgumentException("Course with code " + normalizedCode + " already exists");
        }
        Course course = new Course(code, title, credits, instructor);
        courses.put(normalizedCode, course);
    }

    /**
     * Adds an existing Course object.
     * 
     * @param course Course to add
     * @throws IllegalArgumentException if course code already exists
     */
    public void addCourse(Course course) {
        String normalizedCode = course.getCode().toUpperCase();
        if (courses.containsKey(normalizedCode)) {
            throw new IllegalArgumentException("Course with code " + normalizedCode + " already exists");
        }
        courses.put(normalizedCode, course);
    }

    /**
     * Gets a course by code.
     * 
     * @param code Course code
     * @return Course or null if not found
     */
    public Course getCourse(String code) {
        return courses.get(code.trim().toUpperCase());
    }

    /**
     * Gets all courses as a list, sorted by code.
     * 
     * @return List of all courses sorted by code
     */
    public List<Course> getAllCourses() {
        return courses.values().stream()
                .sorted(Comparator.comparing(Course::getCode))
                .collect(Collectors.toList());
    }

    /**
     * Gets the total number of courses.
     * 
     * @return Course count
     */
    public int getCourseCount() {
        return courses.size();
    }

    // ==================== ENROLLMENT OPERATIONS ====================

    /**
     * Enrolls a student in a course.
     * 
     * @param studentId Student ID
     * @param courseCode Course code
     * @throws IllegalArgumentException if validation fails
     */
    public void enroll(int studentId, String courseCode) {
        Student student = getStudent(studentId);
        if (student == null) {
            throw new IllegalArgumentException("Student not found: " + studentId);
        }

        Course course = getCourse(courseCode);
        if (course == null) {
            throw new IllegalArgumentException("Course not found: " + courseCode);
        }

        // Check for duplicate enrollment
        String normalizedCode = courseCode.trim().toUpperCase();
        boolean alreadyEnrolled = enrollments.stream()
                .anyMatch(e -> e.getStudentId() == studentId && 
                              e.getCourseCode().equals(normalizedCode));
        if (alreadyEnrolled) {
            throw new IllegalArgumentException("Student is already enrolled in " + normalizedCode);
        }

        Enrollment enrollment = new Enrollment(studentId, courseCode);
        enrollments.add(enrollment);
    }

    /**
     * Adds an existing Enrollment object.
     * 
     * @param enrollment Enrollment to add
     */
    public void addEnrollment(Enrollment enrollment) {
        enrollments.add(enrollment);
    }

    /**
     * Assigns a grade to a student's enrollment.
     * 
     * @param studentId Student ID
     * @param courseCode Course code
     * @param gradePercent Grade percentage (0-100)
     * @throws IllegalArgumentException if enrollment not found or grade out of range
     */
    public void assignGrade(int studentId, String courseCode, double gradePercent) {
        if (gradePercent < 0 || gradePercent > 100) {
            throw new IllegalArgumentException("Grade must be between 0 and 100");
        }
        
        String normalizedCode = courseCode.trim().toUpperCase();
        
        Enrollment enrollment = enrollments.stream()
                .filter(e -> e.getStudentId() == studentId && 
                            e.getCourseCode().equals(normalizedCode))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Enrollment not found for student " + studentId + " in course " + normalizedCode));
        
        enrollment.setGradePercent(gradePercent);
    }

    /**
     * Gets all enrollments, sorted by (studentId, courseCode).
     * 
     * @return List of all enrollments sorted
     */
    public List<Enrollment> getAllEnrollments() {
        return enrollments.stream()
                .sorted(Comparator.comparingInt(Enrollment::getStudentId)
                        .thenComparing(Enrollment::getCourseCode))
                .collect(Collectors.toList());
    }

    /**
     * Gets enrollments for a specific student.
     * 
     * @param studentId Student ID
     * @return List of student's enrollments
     */
    public List<Enrollment> getStudentEnrollments(int studentId) {
        return enrollments.stream()
                .filter(e -> e.getStudentId() == studentId)
                .sorted(Comparator.comparing(Enrollment::getCourseCode))
                .collect(Collectors.toList());
    }

    /**
     * Gets the total number of enrollments.
     * 
     * @return Enrollment count
     */
    public int getEnrollmentCount() {
        return enrollments.size();
    }

    // ==================== GPA CALCULATION ====================

    /**
     * Calculates the GPA for a student using credit-weighted average.
     * Uses ONLY graded enrollments.
     * Formula: GPA = Σ(gradePoint × credits) / Σ(credits)
     * If no graded enrollments, returns 0.0
     * 
     * @param studentId Student ID
     * @return GPA on 4.0 scale
     * @throws IllegalArgumentException if student not found
     */
    public double computeGpa(int studentId) {
        if (getStudent(studentId) == null) {
            throw new IllegalArgumentException("Student not found: " + studentId);
        }

        double totalPoints = 0.0;
        int totalCredits = 0;

        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudentId() == studentId && enrollment.isGraded()) {
                Course course = getCourse(enrollment.getCourseCode());
                if (course != null) {
                    double gradePoint = GradeUtils.toGradePoint(enrollment.getGradePercent());
                    int credits = course.getCredits();
                    
                    totalPoints += gradePoint * credits;
                    totalCredits += credits;
                }
            }
        }

        if (totalCredits == 0) {
            return 0.0;
        }

        return totalPoints / totalCredits;
    }

    /**
     * Clears all data from the university.
     */
    public void clearAll() {
        students.clear();
        courses.clear();
        enrollments.clear();
    }
}
