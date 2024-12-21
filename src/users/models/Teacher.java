package users.models;

import java.util.Vector;

import users.Faculty;
import users.TeacherType;

import java.util.Date;

/**
 * Класс для представления преподавателя, наследует от Employee
 */
public class Teacher extends Employee {
    private Faculty faculty;       // Факультет 
    private TeacherType teacherType; // Тип преподавателя (TUTOR, SENIOR_LECTURE, LECTURE, ASSISTENT)

    /**
     * Конструктор для инициализации Teacher
     */
    public Teacher(String id, String firstName, String lastName, String email, String login, Date birthDate, 
                   String hashedPassword, Faculty faculty, TeacherType teacherType) {
        super(id, firstName, lastName, email, login, birthDate, hashedPassword);
        this.faculty = faculty;
        this.teacherType = teacherType;
    }

    // Геттеры и сеттеры

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public TeacherType getTeacherType() {
        return teacherType;
    }

    public void setTeacherType(TeacherType teacherType) {
        this.teacherType = teacherType;
    }

	public String getUuid(){
		return this.uuid;
	}
    /**
     * Переопределение метода toString() для вывода информации о преподавателе
     */
    @Override
    public String toString() {
        return "Teacher {" +
                "ID: " + getUuid() +
                ", Name: " + getFirstName() + " " + getLastName() +
                ", Faculty: " + faculty +
                ", Type: " + teacherType +
                ", Email: " + getEmail() +
                ", BirthDate: " + getBirthDate() +
                "}";
    }
}
