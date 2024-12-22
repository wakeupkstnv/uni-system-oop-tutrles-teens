package users.models;

import java.util.Date;

public class GraduateStudent extends Student {
	protected Researcher researcherProfile;
	private int graduationYear;

	public GraduateStudent() {
		super();
	}

	public GraduateStudent(String id, String firstName, String lastName, String email, String login,
						   Date birthDate, String hashedPassword,
						   int graduationYear) {
		super(id, firstName, lastName, email, login, birthDate, hashedPassword);
		this.graduationYear = graduationYear;
		this.researcherProfile = new Researcher(this.uuid, this, 0.0);
	}

	public GraduateStudent(String id, String firstName, String lastName, String email, String login,
						   Date birthDate, String hashedPassword) {
		super(id, firstName, lastName, email, login, birthDate, hashedPassword);
		this.researcherProfile = new Researcher(this.uuid, this, 0.0);
	}

	// Getters and Setters

	public int getGraduationYear() {
		return graduationYear;
	}

	public void setGraduationYear(int graduationYear) {
		this.graduationYear = graduationYear;
	}
}
