package users.models;

import java.util.Date;
import java.util.Vector;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class PhdStudent extends MasterStudent
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	private String dissertationTitle;
	private Researcher supervisor;
	public PhdStudent(){
		super();
	}

    public PhdStudent(String id, String firstName, String lastName, String email, String login, Date birthDate,
            String hashedPassword, int masterCourse, int masterEnrollmentYear,
            String dissertationTitle, Researcher supervisor) {
		super(id,firstName,lastName,email,login,birthDate,hashedPassword,masterCourse,masterEnrollmentYear);
		this.dissertationTitle=dissertationTitle;
		this.supervisor=supervisor;
    }

	// Геттеры и сеттеры для dissertationTitle
	public String getDissertationTitle() {
		return dissertationTitle;
	}

	public void setDissertationTitle(String dissertationTitle) {
		this.dissertationTitle = dissertationTitle;
	}

	// Геттеры и сеттеры для supervisor
	public Researcher getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Researcher supervisor) {
		this.supervisor = supervisor;
	}

}

