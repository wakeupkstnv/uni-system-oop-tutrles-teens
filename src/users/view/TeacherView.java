package users.view;

import users.models.Student;
import users.models.Teacher;
import java.util.List;

import core.CoreSystem;
import core.Language;

/**
 * Представление учителя (TeacherView).
 */
public class TeacherView extends EmployeeView {

    
    public void showListOfStudents(List<Student> students) {
        
    }

   
   public void showStudentInfo(Student student) {
    if (student == null) {
        if (CoreSystem.getLanguageMode() == Language.RUS) {
            System.out.println("Студент не найден.");
        } else if (CoreSystem.getLanguageMode() == Language.ENG) {
            System.out.println("Student not found.");
        } else if (CoreSystem.getLanguageMode() == Language.KZ) {
            System.out.println("Студент табылмады.");
        }
    } else {
        if (CoreSystem.getLanguageMode() == Language.RUS) {
            System.out.println("Информация о студенте:");
            System.out.println("Имя: " + student.getFirstName() + " " + student.getLastName());
            System.out.println("Электронная почта: " + student.getEmail());
            System.out.println("Дата рождения: " + student.getBirthDate());
            System.out.println("Факультет: " + student.getFaculty().getName());
            System.out.println("Специальность: " + student.getMajor().getName());
            System.out.println("Год обучения: " + student.getYearOfStudy());
        } else if (CoreSystem.getLanguageMode() == Language.ENG) {
            System.out.println("Student Info:");
            System.out.println("Name: " + student.getFirstName() + " " + student.getLastName());
            System.out.println("Email: " + student.getEmail());
            System.out.println("Birth Date: " + student.getBirthDate());
            System.out.println("Faculty: " + student.getFaculty().getName());
            System.out.println("Major: " + student.getMajor().getName());
            System.out.println("Year of Study: " + student.getYearOfStudy());
        } else if (CoreSystem.getLanguageMode() == Language.KZ) {
            System.out.println("Студент туралы ақпарат:");
            System.out.println("Аты: " + student.getFirstName() + " " + student.getLastName());
            System.out.println("Электрондық пошта: " + student.getEmail());
            System.out.println("Туу күні: " + student.getBirthDate());
            System.out.println("Факультет: " + student.getFaculty().getName());
            System.out.println("Мамандығы: " + student.getMajor().getName());
            System.out.println("Оқу жылы: " + student.getYearOfStudy());
        }
    }
}


    
    public void showSchedule(Teacher teacher) {
       
    }
}
