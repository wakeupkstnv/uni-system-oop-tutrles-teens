package users.models;

import java.util.Date;

public class PhdStudent extends MasterStudent {
	private String dissertationTitle;
	private Researcher supervisor;

	public PhdStudent() {
		super();
	}

	public PhdStudent(String id, String firstName, String lastName, String email, String login, Date birthDate,
					  String hashedPassword, int masterCourse, int masterEnrollmentYear,
					  String dissertationTitle, Researcher supervisor) {
		super(id, firstName, lastName, email, login, birthDate, hashedPassword, masterCourse, masterEnrollmentYear);
		this.dissertationTitle = dissertationTitle;
		this.supervisor = supervisor;
	}

	public String getDissertationTitle() {
		return dissertationTitle;
	}

	public void setDissertationTitle(String dissertationTitle) {
		this.dissertationTitle = dissertationTitle;
	}

	public Researcher getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Researcher supervisor) {
		this.supervisor = supervisor;
	}
}
