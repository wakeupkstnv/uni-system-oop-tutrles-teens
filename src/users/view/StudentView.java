package users.view;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import core.CoreSystem;
import database.Database;
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
		languageMode();
		Vector<Course> c=Database.getInstance().getCourses();
		if (l == 0) {
			System.out.println("List of courses: ");
		} else if (l == 1) {
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
		languageMode();
		Vector<Course> c=Database.getInstance().getCourses();
		if (l==0) {
			System.out.println("Student journal");
		    System.out.println("Course name:             First attestation:             Second attestation:             Final exam: ");
		} else if (l == 1) {
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
//
//	public void showSchedule(Student student) {
//		Vector<Vector<String>> schedule = new Vector<Vector<String>>();
//		Vector<Course> c=Database.getInstance().getCourses();
//		int v=0;
//		if(CoreSystem.getLanguageMode()==core.Language.ENG){
//			v=0;
//			System.out.println("Schedule:");
//		}else if(CoreSystem.getLanguageMode()==core.Language.RUS){
//			v=1;
//			System.out.println("Расписание:");
//
//		}else{
//			System.out.println("Сабақ кестесі:");
//			v=2;
//		}
//		String s="";
//		System.out.println(s);
////		for(Course course:c){
////				Lesson l=findKeyByElement(course.getLessons(), student);
////				schedule.get(l.getdayIndex()).add(l.getLesson());
////		}
//		for (Vector<String> day : schedule) {
//            Collections.sort(day);
//        }
	 public void showSchedule(Student student) {
		Vector<Vector<String>> schedule = new Vector<>(5);
		for (int i = 0; i < 5; i++) {
			schedule.add(new Vector<>());
		}
	
		Vector<Course> courses = Database.getInstance().getCourses();
		languageMode();
		if(l==0) {
			System.out.println("Schedule:");
		} else if (l==1) {
			System.out.println("Расписание:");
		} else {
			System.out.println("Сабақ кестесі:");
		}

		System.out.println("-".repeat(125));
	
		for (int i = 0; i < 5; i++) {
			System.out.printf("%-25s", DAYS_OF_WEEK[i][l]);
		}
		System.out.println();
		System.out.println("-".repeat(125));
	
		for (Course course : courses) {
			Lesson lesson = findKeyByElement(course.getLessons(), student);
			if (lesson != null) {
				schedule.get(lesson.getdayIndex()).add(lesson.getLesson());
			}
		}
	
		for (Vector<String> day : schedule) {
			day.sort(Comparator.comparing(StudentView::extractTime));
		}
	
		int maxSlots = schedule.stream().mapToInt(Vector::size).max().orElse(0);
	
		for (int timeSlot = 0; timeSlot < maxSlots; timeSlot++) {
			for (int day = 0; day < 5; day++) {
				if (timeSlot < schedule.get(day).size()) {
					String[] parts = splitLesson(schedule.get(day).get(timeSlot));
					System.out.printf("%-25s", parts[0]);
				} else {
					System.out.printf("%-25s", " ");
				}
			}
			System.out.println();
	
			for (int day = 0; day < 5; day++) {
				if (timeSlot < schedule.get(day).size()) {
					String[] parts = splitLesson(schedule.get(day).get(timeSlot));
					System.out.printf("%-25s", parts[1]);
				} else {
					System.out.printf("%-25s", " ");
				}
			}
			System.out.println();
		}
	
		System.out.println("-".repeat(125));
	}
	
	
	@SuppressWarnings("unchecked")
	public void showProfile(Student student) {
		super.showProfile(student);
		languageMode();
		if (l == 0) {
			System.out.println("Faculty: " + student.getFaculty().name());
			System.out.println("Major: " + student.getMajor().name());
			System.out.println("Year of study: " + student.getYearOfStudy());
		} else if (l == 1) {
			System.out.println("Факультет: " + student.getFaculty().name());
			System.out.println("Специальность: " + student.getMajor().name());
			System.out.println("Год обучения: " + student.getYearOfStudy());
		} else {
			System.out.println("Факультет: " + student.getFaculty().name());
			System.out.println("Мамандық: " + student.getMajor().name());
			System.out.println("Оқу жылы: " + student.getYearOfStudy());
		}
        if(student.getResearcherProfile()==null||student.getResearcherProfile().getArticles().isEmpty()){
			if(l==0){
				System.out.println(student.getResearcherProfile()==null?"You don't have researcher profile.":"Researcher profile: No articles available.");
			}else if(l==1){
				System.out.println(student.getResearcherProfile()==null?"У вас отсутствует профиль исследователя.":"Профиль исследователя: Нет доступных статей.");
			}else{
				System.out.println(student.getResearcherProfile()==null?"Сізде зертеуші профилі жоқ.":"Зерттеуші профилі: Қол жетімді мақалалар жоқ.");
			}
		}
	}

	private static String extractTime(String lesson) {
		return lesson.split("\n")[1].split("-")[0];
	}
	
	private static String[] splitLesson(String lesson) {
		return lesson.split("\n"); 
	}

	private Lesson findKeyByElement(Map<Lesson, List<Student>> map, Student element) {
        for (Map.Entry<Lesson, List<Student>> entry : map.entrySet()) {
            if (entry.getValue().contains(element)) {
                return entry.getKey();
            }
        }
        return null; 
    }
}

