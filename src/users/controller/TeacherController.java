package users.controller;

import java.util.List;

import users.models.Teacher;
import users.models.Student;
import users.view.TeacherView;
import core.Database;
import study.utils.Course;

/**
 * Контроллер для управления действиями учителей.
 */
public class TeacherController<Model extends Teacher, View extends TeacherView> extends EmployeeController<Teacher, TeacherView> {

    public TeacherController(Model teacher, View view) {
        super(teacher, view);
    }

    public void showStudents() {
          Teacher teacher = (Teacher) this.getCurrentModel();
          ((TeacherView) this.getCurrentView()).showListOfStudents(teacher);

}

    /**
     * Метод для показа информации о студенте.
     * 
     * @param student Объект студента.
     */
    public void showStudentInfo(Student student) {
    	((TeacherView)this.getCurrentView()).showStudentInfo(student);
    }

    /**
     * Метод для показа расписания учителя.
     */
    public void showSchedule() {
        Teacher teacher = (Teacher)this.getCurrentModel();
        ((TeacherView) this.getCurrentView()).showSchedule(teacher);
    }

    /**
     * Метод для добавления оценки студенту.
     * 
     * @param student Объект студента.
     * @param course  Курс, за который выставляется оценка.
     * @param grade   Оценка.
     */
    public void assignGrade(Student student, Course course, double grade) {
        boolean success = Database.getInstance().addGradeToStudent(student, course, grade);
        if (success) {
            System.out.println("Grade " + grade + " assigned to " + student.getFirstName() + " for course " + course.getName());
        } else {
            System.out.println("Failed to assign grade.");
        }
    }

    /**
     * Метод для назначения курсов преподавателю.
     * 
     * @param courses Список курсов для назначения.
     */
    public void assignCourses(List<Course> courses) {
        Teacher teacher = this.getCurrentModel();
        boolean success = Database.getInstance().assignCoursesToTeacher(teacher, courses);
        if (success) {
            System.out.println("Courses assigned successfully.");
        } else {
            System.out.println("Failed to assign courses.");
        }
    }

    /**
     * Метод для получения всех курсов преподавателя.
     */
    public void showAssignedCourses() {
        Teacher teacher = this.getCurrentModel();
        List<Course> courses = Database.getInstance().getCoursesByTeacher(teacher);

        if (courses == null || courses.isEmpty()) {
            System.out.println("No courses assigned to the teacher.");
        } else {
            System.out.println("Courses assigned to " + teacher.getFirstName() + ":");
            for (Course course : courses) {
                System.out.println(course.getName());
            }
        }
    }
}
