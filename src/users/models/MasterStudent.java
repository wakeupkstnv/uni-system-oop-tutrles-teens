package users.models;

import java.util.Date;

public class MasterStudent extends GraduateStudent {
    private int masterCourse;
    private int masterEnrollmentYear;

    public MasterStudent() {
        super();
    }

    public MasterStudent(String id, String firstName, String lastName, String email, String login,
                         Date birthDate, String hashedPassword,
                         int masterCourse, int masterEnrollmentYear) {
        super(id, firstName, lastName, email, login, birthDate, hashedPassword);
        this.masterCourse = masterCourse;
        this.masterEnrollmentYear = masterEnrollmentYear;
        this.researcherProfile = new Researcher(this.uuid, this, 0.0);
    }

    public int getMasterCourse() {
        return masterCourse;
    }

    public void setMasterCourse(int masterCourse) {
        this.masterCourse = masterCourse;
    }

    public int getMasterEnrollmentYear() {
        return masterEnrollmentYear;
    }

    public void setMasterEnrollmentYear(int masterEnrollmentYear) {
        this.masterEnrollmentYear = masterEnrollmentYear;
    }
}
