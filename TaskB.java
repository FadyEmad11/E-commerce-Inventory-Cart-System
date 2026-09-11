import java.util.ArrayList;
import java.util.List;

// 1. Abstract Course Class
abstract class Course {
    private String name;

    public Course(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract String getType();

    @Override
    public String toString() {
        return "[" + getType() + " Course] " + name;
    }
}

// 2. Concrete Course Types (Constructor Chaining using super)
class TheoryCourse extends Course {
    public TheoryCourse(String name) {
        super(name);
    }

    @Override
    public String getType() {
        return "Theory";
    }
}

class LabCourse extends Course {
    public LabCourse(String name) {
        super(name);
    }

    @Override
    public String getType() {
        return "Lab";
    }
}

// 3. Student Class
class Student {
    private String name;
    private List<Course> enrolledCourses; // Polymorphic collection

    public Student(String name) {
        this.name = name;
        this.enrolledCourses = new ArrayList<Course>();
    }

    public String getName() {
        return name;
    }

    public void enroll(Course course) {
        enrolledCourses.add(course);
    }

    public void displayCourses() {
        System.out.println("Courses for Student (" + name + "):");
        for (int i = 0; i < enrolledCourses.size(); i++) {
            System.out.println(" - " + enrolledCourses.get(i).toString());
        }
    }
}

// 4. Department Class
class Department {
    private String departmentName;
    private List<Course> courses;     // Polymorphic List
    private List<Student> students;

    public Department(String departmentName) {
        this.departmentName = departmentName;
        this.courses = new ArrayList<Course>();
        this.students = new ArrayList<Student>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void displayDepartmentInfo() {
        System.out.println("==================================================");
        System.out.println("Department: " + departmentName);
        System.out.println("---------------- Offered Courses -----------------");
        for (int i = 0; i < courses.size(); i++) {
            System.out.println((i + 1) + ". " + courses.get(i).toString());
        }

        System.out.println("---------------- Enrolled Students ---------------");
        for (int i = 0; i < students.size(); i++) {
            students.get(i).displayCourses();
        }
        System.out.println("==================================================\n");
    }
}

// 5. Main Class to run Task B
public class TaskB {
    public static void main(String[] args) {
        // Create Department
        Department dept = new Department("Computer Science & Software Testing");

        // Create Courses (Polymorphism)
        Course javaBasics = new TheoryCourse("Java OOP Fundamentals");
        Course softwareTestingLab = new LabCourse("Manual & Automated Testing Lab");
        Course dbTheory = new TheoryCourse("Database Systems Design");
        Course apiLab = new LabCourse("API & Postman Testing Practical");

        dept.addCourse(javaBasics);
        dept.addCourse(softwareTestingLab);
        dept.addCourse(dbTheory);
        dept.addCourse(apiLab);

        // Create Students & Enroll
        Student student1 = new Student("Fady Emad");
        student1.enroll(javaBasics);
        student1.enroll(softwareTestingLab);
        student1.enroll(apiLab);

        Student student2 = new Student("Ahmed Ali");
        student2.enroll(javaBasics);
        student2.enroll(dbTheory);

        dept.addStudent(student1);
        dept.addStudent(student2);

        // Display Everything
        dept.displayDepartmentInfo();
    }
}
