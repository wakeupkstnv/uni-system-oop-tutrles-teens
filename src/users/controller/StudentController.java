package users.controller;

import database.Database;
import study.utils.Course;
import users.models.Student;
import users.view.StudentView;
import users.view.UserView;

import java.util.Vector;

import javax.xml.crypto.Data;

public class StudentController <Model extends Student, View extends StudentView> extends UserController<Student, StudentView>{
	public StudentController(Model currentModel, View currentView) {
		super(currentModel,currentView);
	}
    public boolean registerForCourse(Course course){
        boolean state = Database.getInstance().getRegistationState();
        if (state){
            if (course.getCapacity() < course.getStudents().size() + 1 && this.getCurrentModel().getCredits() + course.getCredits() > 25) {
//                this.currentView.showMessage();
                return false;
            } else{
                Database.getInstance().addCourseToStudent(this.currentModel, course);
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
