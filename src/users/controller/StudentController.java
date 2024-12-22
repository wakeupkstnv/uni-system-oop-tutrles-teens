package users.controller;

import study.utils.Course;
import users.models.Student;
import users.view.StudentView;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Vector;

public class StudentController <Model extends Student, View extends StudentView> extends UserController<Student, StudentView>{
	public StudentController(Model currentModel, View currentView) {
		super(currentModel,currentView);
	}
    public boolean registerForCourse(BufferedReader reader) throws IOException {
        System.out.println("Введите UUID курса: ");
        String Text = reader.readLine();

        boolean state = database.getInstance().getRegistationState();
        if (state) {
            Vector<Course> courses = database.getInstance().getCourses();
            for (Course course : courses) {
                if (course.getUuid().equals(Text) && course.getCapacity() > course.getStudents().size() + 1 && this.getCurrentModel().getCredits() + course.getCredits() < 25) {
                    database.getInstance().addCourseToStudent(this.currentModel, course);
                    course.getStudents().add(this.getCurrentModel());
                    return true;
                } else {
                    if(this.getCurrentModel().getCredits() + course.getCredits() > 25){
                        System.out.println("У студента перебор кредитов");
                        return false;
                    }
                    else{
                        System.out.println("Места на курсы закончились: ");
                        return false;
                    }


                }
            }
        }
        System.out.println("Курс с таким UUID не найден!: ");
        return false;
    }
    public boolean registerForCourse(Course course){
        boolean state = database.getInstance().getRegistationState();
        if (state){
            if (course.getCapacity() < course.getStudents().size() + 1 && this.getCurrentModel().getCredits() + course.getCredits() > 25) {
//                this.currentView.showMessage();
                return false;
            } else{
                database.getInstance().addCourseToStudent(this.currentModel, course);
                course.getStudents().add(this.getCurrentModel());
                return true;
            }
        }
        return false;
    }

    public void checkStudentCourses() {
        Student student = this.getCurrentModel();
        StudentView sv = new StudentView();
        System.out.println("Transcript for " + student.getFirstName() + ":");
        sv.showListOfCourse(student);

    }

    public void viewMarks(){
        Student student = this.getCurrentModel();
        StudentView sv = new StudentView();
        sv.showMarks(student);
    }
    
    public void viewSchedule() {
    	Student student = this.getCurrentModel();
        StudentView sv = new StudentView();
        sv.showSchedule(student);
    }
    
    
    public void seeProfile() {
    	Student student = this.getCurrentModel();
        StudentView sv = new StudentView();
        sv.showProfile(student);
    }

}
