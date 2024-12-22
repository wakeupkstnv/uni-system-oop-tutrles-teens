package users.view;

import users.models.MasterStudent;
import core.CoreSystem;
import core.Language;

public class MasterStudentView extends GraduateStudentView{
	public MasterStudentView() {
		
	}
    public void displayStudentProfile(MasterStudent student) {
        if (CoreSystem.getLanguageMode() == Language.RUS) {
            System.out.println("Профиль магистранта:");
            System.out.println("Имя: " + student.getFirstName() + " " + student.getLastName());
            System.out.println("Курс магистратуры: " + student.getMasterCourse());
            System.out.println("Год зачисления: " + student.getMasterEnrollmentYear());
        } else if (CoreSystem.getLanguageMode() == Language.ENG) {
            System.out.println("Master Student Profile:");
            System.out.println("Name: " + student.getFirstName() + " " + student.getLastName());
            System.out.println("Master Course: " + student.getMasterCourse());
            System.out.println("Enrollment Year: " + student.getMasterEnrollmentYear());
        } else if (CoreSystem.getLanguageMode() == Language.KZ) {
            System.out.println("Магистрант профилі:");
            System.out.println("Аты: " + student.getFirstName() + " " + student.getLastName());
            System.out.println("Магистратура курсы: " + student.getMasterCourse());
            System.out.println("Оқуға түскен жылы: " + student.getMasterEnrollmentYear());
        }
    }
}