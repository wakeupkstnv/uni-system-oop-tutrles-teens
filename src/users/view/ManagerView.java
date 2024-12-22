package users.view;


import post.News;
import post.Request;
import post.Urgency;
import study.utils.Course;
import users.controller.ManagerController;
import users.models.Employee;
import users.models.Student;
import users.models.Teacher;

import java.util.Vector;

import core.CoreSystem;
import database.Database;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class ManagerView extends EmployeeView implements CanViewRequest
{
	public ManagerView(){
		super();
	}

	public void showTeacherList() {
		if (CoreSystem.getLanguageMode() == core.Language.ENG) {
			System.out.println("List of teachers: ");
		} else if (CoreSystem.getLanguageMode() == core.Language.RUS) {
			System.out.println("Список преподавателей: ");
		} else {
			System.out.println("Мұғалімдер тізімі: ");
			
		}
		System.out.printf("-".repeat(50)+"\n");
		for(Teacher r:Database.getInstance().getTeachers()){
			System.out.printf("%-15s",r.getUuid());
			System.out.printf("%-15s","|  "+r.getLastName()+" "+r.getFirstName()+" "+r.getTeacherType().name()+"\n");
		    }
			System.out.printf("-".repeat(50)+"\n");
		}
	
	
	public void showTeacherDetails(String teachedId) {
		Teacher teacher = Database.getInstance().getTeachers().stream().filter(t->t.getUuid().equals(teachedId)).findFirst().orElse(null);
		if(teacher==null){
			if (CoreSystem.getLanguageMode() == core.Language.ENG) {
				System.out.println("Teacher not found.");
			} else if (CoreSystem.getLanguageMode() == core.Language.RUS) {
				System.out.println("Преподаватель не найден.");
			} else {
				System.out.println("Мұғалім табылмады.");
			}
			return;
		}
		if (CoreSystem.getLanguageMode() == core.Language.ENG) {
			System.out.println("Teacher details: ");
			System.out.printf("-".repeat(50)+"\n");
		    System.out.printf("%-15s","ID: "+teacher.getUuid()+"\n");
		    System.out.printf("%-15s","Name: "+teacher.getLastName()+" "+teacher.getFirstName()+"\n");
		    System.out.printf("%-15s","Faculty: "+teacher.getFaculty().name()+"\n");
		    System.out.printf("%-15s","Type: "+teacher.getTeacherType().name()+"\n");
		    System.out.printf("%-15s","Email: "+teacher.getEmail()+"\n");
		    System.out.printf("%-15s","BirthDate: "+teacher.getBirthDate()+"\n");
		    System.out.printf("-".repeat(50)+"\n");  
		} else if (CoreSystem.getLanguageMode() == core.Language.RUS) {
			System.out.println("Информация о преподавателе: ");
			System.out.printf("-".repeat(50)+"\n");
		    System.out.printf("%-15s","ID: "+teacher.getUuid()+"\n");
		    System.out.printf("%-15s","Имя: "+teacher.getLastName()+" "+teacher.getFirstName()+"\n");
		    System.out.printf("%-15s","Факультет: "+teacher.getFaculty().name()+"\n");
		    System.out.printf("%-15s","Тип: "+teacher.getTeacherType().name()+"\n");
		    System.out.printf("%-15s","Электронная почта: "+teacher.getEmail()+"\n");
		    System.out.printf("%-15s","Дата рождения: "+teacher.getBirthDate()+"\n");
		    System.out.printf("-".repeat(50)+"\n");
		} else {
			System.out.println("Мұғалім туралы мәлімет: ");
			System.out.printf("-".repeat(50)+"\n");
		    System.out.printf("%-15s","ID: "+teacher.getUuid()+"\n");
		    System.out.printf("%-15s","Аты: "+teacher.getLastName()+" "+teacher.getFirstName()+"\n");
		    System.out.printf("%-15s","Факультет: "+teacher.getFaculty().name()+"\n");
		    System.out.printf("%-15s","Түрі: "+teacher.getTeacherType().name()+"\n");
		    System.out.printf("%-15s","Электрондық пошта: "+teacher.getEmail()+"\n");
		    System.out.printf("%-15s","Туған күні: "+teacher.getBirthDate()+"\n");
		    System.out.printf("-".repeat(50)+"\n");
		}
	}
	
	
	public void showCourseList() {
		if (CoreSystem.getLanguageMode() == core.Language.ENG) {
			System.out.println("List of courses: ");
			System.out.printf("%-15s"+"|  "+"%-15s"+"|  "+"%-15s"+"|  "+"%-15s\n","ID","Title","Capacity","Credits");
		} else if (CoreSystem.getLanguageMode() == core.Language.RUS) {
			System.out.println("Список курсов: ");
			System.out.printf("%-15s"+"|  "+"%-15s"+"|  "+"%-15s"+"|  "+"%-15s\n","ID","Название","Вместимость","Кредиты");
		} else {
			System.out.println("Курстар тізімі: ");
			System.out.printf("%-15s"+"|  "+"%-15s"+"|  "+"%-15s"+"|  "+"%-15s\n","ID","Атауы","Қабылдайтын мүшелер","Кредиттер");
			
		}
		System.out.printf("-".repeat(50)+"\n");
		for(Course c: Database.getInstance().getCourses()){
			System.out.printf("%-15s",c.getUuid());
			System.out.printf("%-15s","|  "+c.getTitle());
			System.out.printf("%-15s","|  "+c.getCredits()+"\n");
		    }
			System.out.printf("-".repeat(50)+"\n");
		}
	

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void showCourseDetails(String courseId) {
		Course course = Database.getInstance().getCourses().stream().filter(t->t.getUuid().equals(courseId)).findFirst().orElse(null);
		if(course==null){
			if (CoreSystem.getLanguageMode() == core.Language.ENG) {
				System.out.println("Course not found.");
			} else if (CoreSystem.getLanguageMode() == core.Language.RUS) {
				System.out.println("Курс не найден.");
			} else {
				System.out.println("Курс табылмады.");
			}
			return;
		}
		if (CoreSystem.getLanguageMode() == core.Language.ENG) {
			System.out.println("Course details: ");
			System.out.printf("-".repeat(50)+"\n");
		    System.out.printf("%-15s","ID: "+course.getUuid()+"\n");
		    System.out.printf("%-15s","Title: "+course.getTitle()+"\n");
		    System.out.printf("%-15s","Credits: "+course.getCredits()+"\n");
		    System.out.printf("%-15s","Capacity: "+course.getCapacity()+"\n");
			System.out.printf("%-15s","Year: "+course.getYear()+"\n");
		    System.out.printf("-".repeat(50)+"\n");  
		} else if (CoreSystem.getLanguageMode() == core.Language.RUS) {
			System.out.println("Информация о курсе: ");
			System.out.printf("-".repeat(50)+"\n");
		    System.out.printf("%-15s","ID: "+course.getUuid()+"\n");
		    System.out.printf("%-15s","Названия: "+course.getTitle()+"\n");
		    System.out.printf("%-15s","Кредиты: "+course.getCredits()+"\n");
		    System.out.printf("%-15s","Вместимость: "+course.getCapacity()+"\n");
			System.out.printf("%-15s","Год: "+course.getYear()+"\n");
		    System.out.printf("-".repeat(50)+"\n");
		} else {
			System.out.println("Курс туралы мәлімет: ");
			System.out.printf("-".repeat(50)+"\n");
		    System.out.printf("%-15s","ID: "+course.getUuid()+"\n");
		    System.out.printf("%-15s","Аты: "+course.getTitle()+"\n");
		    System.out.printf("%-15s","Кредиттер: "+course.getCredits()+"\n");
		    System.out.printf("%-15s","Орындар көлемі: "+course.getCapacity()+"\n");
			System.out.printf("%-15s","Жыл: "+course.getYear()+"\n");
		    System.out.printf("-".repeat(50)+"\n");
		}	
	}
	
	public void showEmployeeRequests() {
		Vector<Request> requests = getRequestsOfEmployees();
		if (CoreSystem.getLanguageMode() == core.Language.ENG) {
			System.out.println("List of employee requests: ");
		} else if (CoreSystem.getLanguageMode() == core.Language.RUS) {
			System.out.println("Список запросов работников: ");
		} else {
			System.out.println("Қызметкерлердің сұраулар тізімі: ");
			
		}
		System.out.printf("-".repeat(50)+"\n");
		if (requests.isEmpty()) {
			if (CoreSystem.getLanguageMode() == core.Language.RUS) {
				System.out.printf("22s-%","Нет запросов.\n");
			} else if (CoreSystem.getLanguageMode() == core.Language.ENG) {
				System.out.printf("22s-%","No requests available.\n");
			} else if (CoreSystem.getLanguageMode() == core.Language.KZ) {
				System.out.printf("22s-%","Сұраулар жоқ.\n");
			}
		} else {
			for(Request r:requests){
				if(CoreSystem.getLanguageMode() == core.Language.ENG){
					System.out.println(r.getAuthor().getUuid()+"|"+r.getAuthor().getLastName()+" "+r.getAuthor().getFirstName()+"|"+"Request{"+"State="+( r.getSigned()?"Completed":"Rejected")+", urgency="+r.getUrgency().name()+", description='" + r.getDescription()+'}');
				}else if(CoreSystem.getLanguageMode() == core.Language.RUS){
				System.out.println(r.getAuthor().getUuid()+"|"+r.getAuthor().getLastName()+" "+r.getAuthor().getFirstName()+"|"+"Запрос{"+"Состояние="+( r.getSigned()?"Выполнен":"Откланено")+", срочность="+r.getUrgency().name()+", описание='" + r.getDescription()+'}');
			}else{	System.out.println(r.getAuthor().getUuid()+"|"+r.getAuthor().getLastName()+" "+r.getAuthor().getFirstName()+"|"+"Сұрау{"+"Жағдайы="+( r.getSigned()?"Орындалды":"Бас тартылды")+", шұғылдық="+r.getUrgency().name()+", сиппатама='" + r.getDescription()+'}');
		}
		    }
		    }
		}

	public void showEmployeeRequestsDetails(String id) {
		Vector<Request> requests = getRequestsOfEmployees();
		Request request = requests.stream().filter(r->r.getAuthor().getUuid().equals(id)).findFirst().orElse(null);
		if (CoreSystem.getLanguageMode() == core.Language.ENG) {
			System.out.println("List of employee requests: ");
		} else if (CoreSystem.getLanguageMode() == core.Language.RUS) {
			System.out.println("Список запросов работников: ");
		} else {
			System.out.println("Қызметкерлердің сұраулар тізімі: ");
			
		}
		System.out.printf("-".repeat(50)+"\n");
		if (request==null) {
			if (CoreSystem.getLanguageMode() == core.Language.RUS) {
				System.out.printf("22s-%","Запрос не найден.\n");
			} else if (CoreSystem.getLanguageMode() == core.Language.ENG) {
				System.out.printf("22s-%","Request not found.\n");
			} else if (CoreSystem.getLanguageMode() == core.Language.KZ) {
				System.out.printf("22s-%","Сұрау табылмады.\n");
			}
		} else {
			for(Request r:requests){
				if(r.getAuthor().getUuid().equals(id)){
					if(CoreSystem.getLanguageMode() == core.Language.ENG){
						System.out.println(r.getAuthor().getUuid()+"|"+r.getAuthor().getLastName()+" "+r.getAuthor().getFirstName()+"|"+"Request{"+"State="+( r.getSigned()?"Completed":"Rejected")+", urgency="+r.getUrgency().name()+", description='" + r.getDescription()+'}');
					}else if(CoreSystem.getLanguageMode() == core.Language.RUS){
					System.out.println(r.getAuthor().getUuid()+"|"+r.getAuthor().getLastName()+" "+r.getAuthor().getFirstName()+"|"+"Запрос{"+"Состояние="+( r.getSigned()?"Выполнен":"Откланено")+", срочность="+r.getUrgency().name()+", описание='" + r.getDescription()+'}');
				}else{	System.out.println(r.getAuthor().getUuid()+"|"+r.getAuthor().getLastName()+" "+r.getAuthor().getFirstName()+"|"+"Сұрау{"+"Жағдайы="+( r.getSigned()?"Орындалды":"Бас тартылды")+", шұғылдық="+r.getUrgency().name()+", сиппатама='" + r.getDescription()+'}');
			}
				}
			}
			System.out.println();
		}
	}
	
	
	public void showStudentsList() {
		if (CoreSystem.getLanguageMode() == core.Language.ENG) {
			System.out.println("List of students: ");
		} else if (CoreSystem.getLanguageMode() == core.Language.RUS) {
			System.out.println("Список студентов: ");
		} else {
			System.out.println("Студенттер тізімі: ");
			
		}
		System.out.printf("-".repeat(50)+"\n");
		for(Student s:Database.getInstance().getStudents()){
			System.out.printf("%-15s",s.getUuid());
			System.out.printf("%-15s","|  "+s.getLastName()+" "+s.getFirstName()+"| Faculty: " + s.getFaculty().name()+"\n");
		    }
		}
	
	

	@Override
	public void viewAllRequest() {
		if (CoreSystem.getLanguageMode() == core.Language.ENG) {
			System.out.println("List of requests: ");
		} else if (CoreSystem.getLanguageMode() == core.Language.RUS) {
			System.out.println("Список запросов: ");
		} else {
			System.out.println("Сұраулар тізімі: ");
			
		}
		System.out.printf("-".repeat(50)+"\n");
		if (Database.getInstance().getRequests().isEmpty()) {
			if (CoreSystem.getLanguageMode() == core.Language.RUS) {
				System.out.printf("22s-%","Нет запросов.\n");
			} else if (CoreSystem.getLanguageMode() == core.Language.ENG) {
				System.out.printf("22s-%","No requests available.\n");
			} else if (CoreSystem.getLanguageMode() == core.Language.KZ) {
				System.out.printf("22s-%","Сұраулар жоқ.\n");
			}
		} else {
			
			for(Request r:Database.getInstance().getRequests()){
				if(r.getUrgency()!=null&&r.getUrgency()!=Urgency.HIGH){
				if(CoreSystem.getLanguageMode() == core.Language.ENG){
					System.out.println(r.getAuthor().getUuid()+"|"+r.getAuthor().getLastName()+" "+r.getAuthor().getFirstName()+"|"+"Request{"+"State="+( r.getSigned()?"Completed":"Rejected")+", urgency="+r.getUrgency().name()+", description='" + r.getDescription()+'}');
				}else if(CoreSystem.getLanguageMode() == core.Language.RUS){
				System.out.println(r.getAuthor().getUuid()+"|"+r.getAuthor().getLastName()+" "+r.getAuthor().getFirstName()+"|"+"Запрос{"+"Состояние="+( r.getSigned()?"Выполнен":"Откланено")+", срочность="+r.getUrgency().name()+", описание='" + r.getDescription()+'}');
			}else{	System.out.println(r.getAuthor().getUuid()+"|"+r.getAuthor().getLastName()+" "+r.getAuthor().getFirstName()+"|"+"Сұрау{"+"Жағдайы="+( r.getSigned()?"Орындалды":"Бас тартылды")+", шұғылдық="+r.getUrgency().name()+", сиппатама='" + r.getDescription()+'}');
		}
		    }
		    }
		}
	}
	private Vector<Request> getRequestsOfEmployees(){
		Vector<Request> requests = new Vector<>();
		for(Request r:Database.getInstance().getRequests()){
			if(r.getUrgency()!=null&&r.getUrgency()!=Urgency.HIGH){
				for(Employee e: Database.getInstance().getEmployees()){
					if(r.getAuthor().getUuid().equals(e.getUuid())){
				       requests.add(r);
					}
				}
			}
		}
		return requests;
	}
}

