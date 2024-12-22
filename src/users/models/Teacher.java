package users.models;

import users.Faculty;
import users.TeacherType;

import java.util.Date;

public class Teacher extends Employee {
    private Faculty faculty;
    private TeacherType teacherType;

    public Teacher() {
        super();
    }

    public Teacher(String id, String firstName, String lastName, String email, String login, Date birthDate,
                   String hashedPassword, Faculty faculty, TeacherType teacherType) {
        super(id, firstName, lastName, email, login, birthDate, hashedPassword);
        this.faculty = faculty;
        this.teacherType = teacherType;
    }

    // Getters and Setters

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
