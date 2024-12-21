package users.models;

import java.util.Date;
import java.util.Vector;

import papers.ResearchPaper;

/**
 * Класс для магистранта, наследующий GraduateStudent
 */
public class MasterStudent extends GraduateStudent {
    private int masterCourse;          // Курс магистратуры
    private int masterEnrollmentYear;  // Год зачисления на магистратуру

    /**
     * Конструктор для создания объекта MasterStudent
     */
	public MasterStudent(){
		super();
	}
    public MasterStudent(String id, String firstName, String lastName, String email, String login,
                         Date birthDate, String hashedPassword,
                         int masterCourse, int masterEnrollmentYear) {
        super(id, firstName, lastName, email, login, birthDate, hashedPassword);
        this.masterCourse = masterCourse;
        this.masterEnrollmentYear = masterEnrollmentYear;
        this.reseacherProfile = new Researcher(
                this.uuid, 
                this,
                0.0
            );    }

    /**
     * Геттер для курса магистратуры
     */
    public int getMasterCourse() {
        return masterCourse;
    }

    /**
     * Сеттер для курса магистратуры
     */
    public void setMasterCourse(int masterCourse) {
        this.masterCourse = masterCourse;
    }

    /**
     * Геттер для года зачисления на магистратуру
     */
    public int getMasterEnrollmentYear() {
        return masterEnrollmentYear;
    }

    /**
     * Сеттер для года зачисления на магистратуру
     */
    public void setMasterEnrollmentYear(int masterEnrollmentYear) {
        this.masterEnrollmentYear = masterEnrollmentYear;
    }
}
