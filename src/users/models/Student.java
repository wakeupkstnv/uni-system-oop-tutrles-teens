package users.models;
import users.Faculty;

import java.util.Date;

import core.CoreSystem;
import core.Language;

/**
 * Класс Student
 */
public class Student extends User implements CanBecomeResearcher {
    private Faculty faculty; // Факультет
    private Major major; // Специальность
    private int yearOfStudy; // Год обучения
    private Researcher reseacherProfile;

    private int credits;

    /**
     * Конструктор для создания объекта Student
     */
	public Student(){
		super();
	
	}
    public Student(String id, String firstName, String lastName, String email, String login,
                   Date birthDate, String hashedPassword,
                   Faculty faculty, Major major, int yearOfStudy) {
        super(id, firstName, lastName, email, login, birthDate, hashedPassword);
        
        this.faculty = faculty;
        
        // Проверка соответствия факультета и специальности
        if (major.getFaculty() == faculty) {
            this.major = major;
        } else {
            if(CoreSystem.getLanguageMode() == Language.RUS){
                throw new IllegalArgumentException("Специальность не соответствует факультету.");
            } else if(CoreSystem.getLanguageMode() == Language.ENG){
                throw new IllegalArgumentException("The speciality does not match the faculty.");
            }else{
                throw new IllegalArgumentException("Мамандық факультетке сәйкес келмейді.");
            }
        }
        
        this.yearOfStudy = yearOfStudy;
    }

    
    public Student(String id, String firstName, String lastName, String email, String login,
            Date birthDate, String hashedPassword) {
    		super(id, firstName, lastName, email, login, birthDate, hashedPassword);
 
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    /**
     * Геттер для факультета
     */


    public Faculty getFaculty() {
        return faculty;
    }

    /**
     * Сеттер для факультета
     */
    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    /**
     * Геттер для специальности
     */
    public Major getMajor() {
        return major;
    }

    /**
     * Сеттер для специальности
     */
    public void setMajor(Major major) {
        // Проверка соответствия специальности выбранному факультету
        if (major.getFaculty() == this.faculty) {
            this.major = major;
        } else {
            if(CoreSystem.getLanguageMode() == Language.RUS){
                System.out.println("Ошибка: выбранная специальность не соответствует факультету.");
            } else if(CoreSystem.getLanguageMode() == Language.ENG){
                System.out.println("Error: the selected major does not match the faculty.");
            }else{
                System.out.println("Қате: таңдалған мамандық факультетке сәйкес келмейді.");
            }
        }
    }

    /**
     * Геттер для года обучения
     */
    public int getYearOfStudy() {
        return yearOfStudy;
    }

    /**
     * Сеттер для года обучения
     */
    public void setYearOfStudy(int yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    @Override
    public void becomeResearcher() {
        this.reseacherProfile = new Researcher(
                this.uuid, 
                this,
                0.0
                );
       }
}
