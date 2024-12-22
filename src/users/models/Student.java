package users.models;

import users.Faculty;

import core.CoreSystem;
import core.Language;

import java.util.Date;

public class Student extends User implements CanBecomeResearcher {
    private Faculty faculty;
    private Major major;
    private int yearOfStudy;
    private Researcher researcherProfile;
    private int credits;

    public Student() {
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
            } else {
                throw new IllegalArgumentException("Мамандық факультетке сәйкес келмейді.");
            }
        }

        this.yearOfStudy = yearOfStudy;
    }

    public Student(String id, String firstName, String lastName, String email, String login,
                   Date birthDate, String hashedPassword) {
        super(id, firstName, lastName, email, login, birthDate, hashedPassword);
    }

    // Getters and Setters

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        if (major.getFaculty() == this.faculty) {
            this.major = major;
        } else {
            if(CoreSystem.getLanguageMode() == Language.RUS){
                System.out.println("Ошибка: выбранная специальность не соответствует факультету.");
            } else if(CoreSystem.getLanguageMode() == Language.ENG){
                System.out.println("Error: the selected major does not match the faculty.");
            } else {
                System.out.println("Қате: таңдалған мамандық факультетке сәйкес келмейді.");
            }
        }
    }

    public int getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(int yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    public Researcher getResearcherProfile() {
        return researcherProfile;
    }

    public void setResearcherProfile(Researcher researcherProfile) {
        this.researcherProfile = researcherProfile;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    @Override
    public void becomeResearcher() {
        this.researcherProfile = new Researcher(this.uuid, this, 0.0);
    }
}
