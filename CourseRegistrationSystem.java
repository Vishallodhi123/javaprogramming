import java.util.*;

// Class to represent a Course
class Course {
    private String code;
    private String title;
    private String description;
    private int capacity;
    private String schedule;
    private int registeredStudents;

    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.registeredStudents = 0;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getSchedule() {
        return schedule;
    }

    public int getAvailableSlots() {
        return capacity - registeredStudents;
    }

    public boolean registerStudent() {
        if (registeredStudents < capacity) {
            registeredStudents++;
            return true;
        }
        return false;
    }

    public boolean dropStudent() {
        if (registeredStudents > 0) {
            registeredStudents--;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("Course Code: %s | Title: %s | Slots: %d/%d | Schedule: %s",
                code, title, registeredStudents, capacity, schedule);
    }
}

// Class to represent a Student
class Student {
    private String id;
    private String name;
    private List<Course> registeredCourses;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public boolean registerForCourse(Course course) {
        if (course.getAvailableSlots() > 0 && !registeredCourses.contains(course)) {
            registeredCourses.add(course);
            course.registerStudent();
            return true;
        }
        return false;
    }

    public boolean dropCourse(Course course) {
        if (registeredCourses.contains(course)) {
            registeredCourses.remove(course);
            course.dropStudent();
            return true;
        }
        return false;
    }
}
public class CourseRegistrationSystem {
    private List<Course> courses;
    private List<Student> students;

    public CourseRegistrationSystem() {
        this.courses = new ArrayList<>();
        this.students = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void displayCourses() {
        System.out.println("\n--- Available Courses ---");
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    public Student findStudentById(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    public Course findCourseByCode(String code) {
        for (Course course : courses) {
            if (course.getCode().equalsIgnoreCase(code)) {
                return course;
            }
        }
        return null;
    }

    public void displayStudentCourses(Student student) {
        System.out.println("\n--- Registered Courses for " + student.getName() + " ---");
        for (Course course : student.getRegisteredCourses()) {
            System.out.println(course);
        }
    }

    public void registerStudentForCourse(Student student, Course course) {
        if (student.registerForCourse(course)) {
            System.out.println("Successfully registered for course: " + course.getTitle());
        } else {
            System.out.println("Failed to register. The course might be full or already registered.");
        }
    }

    public void dropStudentFromCourse(Student student, Course course) {
        if (student.dropCourse(course)) {
            System.out.println("Successfully dropped course: " + course.getTitle());
        } else {
            System.out.println("Failed to drop the course. You may not be registered for it.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CourseRegistrationSystem system = new CourseRegistrationSystem();

        system.addCourse(new Course("CS101", "Introduction to Programming", "Basics of programming", 3, "MWF 9:00-10:00"));
        system.addCourse(new Course("MATH201", "Calculus I", "Fundamentals of calculus", 2, "TTh 11:00-12:30"));
        system.addCourse(new Course("ENG301", "English Literature", "Study of English literary works", 4, "MW 1:00-2:30"));

        
        system.addStudent(new Student("S1001", "Alice"));
        system.addStudent(new Student("S1002", "Bob"));

        int choice;
        do {
            System.out.println("\n--- Student Course Registration System ---");
            System.out.println("1. Display Available Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. View Registered Courses");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1 -> system.displayCourses();
                case 2 -> {
                    System.out.print("Enter your Student ID: ");
                    String studentId = scanner.nextLine();
                    Student student = system.findStudentById(studentId);
                    if (student == null) {
                        System.out.println("Student not found!");
                        break;
                    }
                    System.out.print("Enter Course Code to register: ");
                    String courseCode = scanner.nextLine();
                    Course course = system.findCourseByCode(courseCode);
                    if (course == null) {
                        System.out.println("Course not found!");
                        break;
                    }
                    system.registerStudentForCourse(student, course);
                }
                case 3 -> {
                    System.out.print("Enter your Student ID: ");
                    String studentId = scanner.nextLine();
                    Student student = system.findStudentById(studentId);
                    if (student == null) {
                        System.out.println("Student not found!");
                        break;
                    }
                    System.out.print("Enter Course Code to drop: ");
                    String courseCode = scanner.nextLine();
                    Course course = system.findCourseByCode(courseCode);
                    if (course == null) {
                        System.out.println("Course not found!");
                        break;
                    }
                    system.dropStudentFromCourse(student, course);
                }
                case 4 -> {
                    System.out.print("Enter your Student ID: ");
                    String studentId = scanner.nextLine();
                    Student student = system.findStudentById(studentId);
                    if (student != null) {
                        system.displayStudentCourses(student);
                    } else {
                        System.out.println("Student not found!");
                    }
                }
                case 5 -> System.out.println("Exiting system. Goodbye!");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }
}
