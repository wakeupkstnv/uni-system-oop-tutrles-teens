package users.view;

import users.models.GraduateStudent;
import core.CoreSystem;
import core.Language;

public class GraduateStudentView extends StudentView{
	public GraduateStudentView() {
		
	}
    public void displayStudentProfile(GraduateStudent student) {
        if (CoreSystem.getLanguageMode() == Language.RUS) {
            System.out.println("Профиль выпускника:");
            System.out.println("Имя: " + student.getFirstName() + " " + student.getLastName());
            System.out.println("Год выпуска: " + student.getGraduationYear());
        } else if (CoreSystem.getLanguageMode() == Language.ENG) {
            System.out.println("Graduate Student Profile:");
            System.out.println("Name: " + student.getFirstName() + " " + student.getLastName());
            System.out.println("Graduation Year: " + student.getGraduationYear());
        } else if (CoreSystem.getLanguageMode() == Language.KZ) {
            System.out.println("Тулык студент профилі:");
            System.out.println("Аты: " + student.getFirstName() + " " + student.getLastName());
            System.out.println("Бітірген жылы: " + student.getGraduationYear());
        }
    }
}
