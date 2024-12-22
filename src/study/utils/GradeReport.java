package study.utils;


import java.util.Vector;
import users.models.Student;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class GradeReport
{
	private Student student;
	private Vector<Course> courses;
	private Vector<Mark> mark;

	public GradeReport(Student student){
		this.student = student;
	}

	public void getGradeReportStudent() {
		// TODO implement me
	}
	
	public Vector<Mark> getMarks() {
		// TODO implement me
		return null;	
	}
	
	public Vector<Course> getStudentCourses() {
		// TODO implement me
		return null;	
	}
	
}

