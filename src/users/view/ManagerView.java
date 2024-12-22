package users.view;


import post.News;
import post.Request;
import post.Urgency;
import users.controller.ManagerController;
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
			System.out.println(r.getUuid()+"|"+r.getLastName()+" "+r.getFirstName()+"|"+"Request{"+"State="+( r.getSigned()?"Completed":"Rejected")+", urgency="+r.getUrgency().name()+", description='" + r.getDescription()+'}');
			}else if(CoreSystem.getLanguageMode() == core.Language.RUS){
				System.out.println(r.getAuthor().getUuid()+"|"+r.getAuthor().getLastName()+" "+r.getAuthor().getFirstName()+"|"+"Запрос{"+"Состояние="+( r.getSigned()?"Выполнен":"Откланено")+", срочность="+r.getUrgency().name()+", описание='" + r.getDescription()+'}');
			}else{	System.out.println(r.getAuthor().getUuid()+"|"+r.getAuthor().getLastName()+" "+r.getAuthor().getFirstName()+"|"+"Сұрау{"+"Жағдайы="+( r.getSigned()?"Орындалды":"Бас тартылды")+", шұғылдық="+r.getUrgency().name()+", сиппатама='" + r.getDescription()+'}');
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
	
	public Teacher showTeacherDetails(String teachedId) {
		// TODO implement me
		return null;	
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void showCourseList() {
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void showCourseDetails(String courseId) {
		// TODO implement me	
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void showEmployeeRequests() {
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Request showEmployeeRequestsDetails(String id) {
		// TODO implement me
		return null;	
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void showStudentsList() {
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void showRequests() {
		// TODO implement me
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
}

