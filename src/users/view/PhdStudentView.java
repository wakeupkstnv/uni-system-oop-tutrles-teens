package users.view;

import users.models.GraduateStudent;
import users.models.MasterStudent;
import users.models.PhdStudent;
import core.CoreSystem;
import core.Language;

public class PhdStudentView extends MasterStudentView {
	public PhdStudentView() {

	}
    public void displayStudentProfile(PhdStudent student) {
        if (CoreSystem.getLanguageMode() == Language.RUS) {
            System.out.println("Профиль докторанта:");
            System.out.println("Имя: " + student.getFirstName() + " " + student.getLastName());
            System.out.println("Тема диссертации: " + student.getDissertationTitle());
            if (student.getSupervisor() != null) {
                System.out.println("Научный руководитель: " + student.getSupervisor().getUserInstance().getFirstName() + " " + student.getSupervisor().getUserInstance().getLastName());
            }
        } else if (CoreSystem.getLanguageMode() == Language.ENG) {
            System.out.println("PhD Student Profile:");
            System.out.println("Name: " + student.getFirstName() + " " + student.getLastName());
            System.out.println("Dissertation Title: " + student.getDissertationTitle());
            if (student.getSupervisor() != null) {
                System.out.println("Supervisor: " + student.getSupervisor().getUserInstance().getFirstName() + " " + student.getSupervisor().getUserInstance().getLastName());
            }
        } else if (CoreSystem.getLanguageMode() == Language.KZ) {
            System.out.println("Докторант профилі:");
            System.out.println("Аты: " + student.getFirstName() + " " + student.getLastName());
            System.out.println("Диссертация тақырыбы: " + student.getDissertationTitle());
            if (student.getSupervisor() != null) {
                System.out.println("Ғылыми жетекші: " + student.getSupervisor().getUserInstance().getFirstName() + " " + student.getSupervisor().getUserInstance().getLastName());
            }
        }
    }
}
