package users.view;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import Core.CoreSystem;
import Database.Database;
import study.utils.Course;
import study.utils.Lesson;
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
    private static final String[][] DAYS_OF_WEEK = {
		{"Monday", "Понедельник", "Дүйсенбі"},
		{"Tuesday", "Вторник", "Сейсенбі"},
		{"Wednesday", "Среда", "Сәрсенбі"},
		{"Thursday", "Четверг", "Бейсенбі"},
		{"Friday", "Пятница", "Жұма"}
};
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
				if(s.equals(student)){
				Mark m=course.getMark(student);
				System.out.print(course.getTitle()+"             "+m.getFirstAttestation()+"             "+m.getSecondAttestation()+"             "+m.getFinal());
				}
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
		Vector<Vector<String>> schedule = new Vector<Vector<String>>();
		Vector<Course> c=Database.getInstance().getCourses();
		int v=0;
		if(CoreSystem.getLanguageMode()==Core.Language.ENG){
			v=0;
			System.out.println("Schedule:");
		}else if(CoreSystem.getLanguageMode()==Core.Language.RUS){
			v=1;
			System.out.println("Расписание:");

		}else{
			System.out.println("Сабақ кестесі:");
			v=2;
		}
		String s="";
		System.out.println(s);
		for(Course course:c){
				Lesson l=findKeyByElement(course.getLessons(), student);
				schedule.get(l.getdayIndex()).add(l.getLesson());
		}
		for (Vector<String> day : schedule) {
            Collections.sort(day); 
        }
		for (int i = 0; i < 5; i++) {
			s+=DAYS_OF_WEEK[i][v]+":      ";
			for (String lesson : schedule.get(i)) {
				s+=lesson+";  ";
			}
			System.out.println(s);
			s="";
		}
	}
	private Lesson findKeyByElement(HashMap<Lesson, Vector<Student>> map, Student element) {
        for (Map.Entry<Lesson, Vector<Student>> entry : map.entrySet()) {
            if (entry.getValue().contains(element)) {
                return entry.getKey();
            }
        }
        return null; // Element not found
    }
	public void showProfile(Student student) {
		System.out.println(student);

	}
}

