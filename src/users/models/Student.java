package users.models;
import users.Faculty;

import java.util.Date;
import java.util.Vector;

import papers.ResearchPaper;

/**
 * Класс Student
 */
public class Student extends User implements CanBecomeResearcher {
    private Faculty faculty; // Факультет
    private Major major; // Специальность
    private int yearOfStudy; // Год обучения
    private Researcher reseacherProfile;

    /**
     * Конструктор для создания объекта Student
     */
	public Student(){
		super();
	}
    public Student(String id, String firstName, String lastName, String email, String login,
                   Date birthDate, String hashedPassword, Vector<String> notifications,
                   Faculty faculty, Major major, int yearOfStudy) {
        super(id, firstName, lastName, email, login, birthDate, hashedPassword, notifications);
        
        this.faculty = faculty;
        
        // Проверка соответствия факультета и специальности
        if (major.getFaculty() == faculty) {
            this.major = major;
        } else {
            throw new IllegalArgumentException("Специальность не соответствует факультету.");
        }
        
        this.yearOfStudy = yearOfStudy;
    }

    
    public Student(String id, String firstName, String lastName, String email, String login,
            Date birthDate, String hashedPassword, Vector<String> notifications) {
    		super(id, firstName, lastName, email, login, birthDate, hashedPassword, notifications);
 
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
            System.out.println("Ошибка: выбранная специальность не соответствует факультету.");
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
                this.id, 
                this.firstName, 
                this.lastName, 
                this.email, 
                this.login, 
                this.birthDate, 
                this.hashedPassword, 
                this.getAllNotifications(), 
                0.0, // Здесь можно задать индекс H по умолчанию
                new Vector<ResearchPaper>() // Пустой список статей на начальном этапе
            );
    }
}
