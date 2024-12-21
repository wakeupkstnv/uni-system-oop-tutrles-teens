package users.view;

import java.util.Vector;

import Core.CoreSystem;
import Database.Database;
import study.utils.Course;
import study.utils.Mark;
import users.models.Student;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

@SuppressWarnings("rawtypes")
public class StudentView extends UserView
{

	public void showListOfCourse(Student student) {
		Vector<Course> c=Database.getInstance().getCourses();
		if (CoreSystem.getLanguageMode() == Core.Language.ENG) {
			System.out.println("List of courses: ");
		} else if (CoreSystem.getLanguageMode() == Core.Language.RUS) {
			System.out.println("Список курсов: ");
		} else {
			System.out.println("Курстар тізімі: ");
			
		}
		
		for(Course course:c){
			for(Student s1:course.getStudents()){
				if(s1.equals(student)){
					System.out.println(course.getTitle());
					break;
				}
			}
		}
	}
	
	
	public void showMarks(Student student) {
		Vector<Course> c=Database.getInstance().getCourses();
		if (CoreSystem.getLanguageMode() == Core.Language.ENG) {
			System.out.println("Student journal");
		    System.out.println("Course name:             First attestation:             Second attestation:             Final exam: ");
		} else if (CoreSystem.getLanguageMode() == Core.Language.RUS) {
			System.out.println("Журнал обучающегося");
			System.out.println("Название курса:             Первая аттестация:             Вторая аттестация:             Файнал: ");
		} else {
			System.out.println("Студент журналы");
			System.out.println("Курстың атауы:             Бірінші аттестация:             Екінші аттестация:             Қорытынды бағалау: ");
							
		}
		
		for(Course course:c){
			for(Student s:course.getStudents()){
				Mark m=course.getMark(student);
				System.out.print(course.getTitle()+"             "+m.getFirstAttestation()+"             "+m.getSecondAttestation()+"             "+m.getFinal());
			}
		}
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void showSchedule(Student student) {
		Vector<Course> c=Database.getInstance().getStudentCourses(student);
		String s="Schedule: \nMonday  Tuesday  Wednesday  Thursday  Friday  Saturday  Sunday\n";
		for(Course course:c){
			System.out.println(course.getName()+getLesson());
		}
		}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void showProfile(Student student) {
		System.out.println(student);

	}
}

