package users.models;

import java.util.Date;
import java.util.Vector;

import papers.ResearchPaper;
import users.Faculty;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class GraduateStudent extends Student
{
	protected Researcher reseacherProfile;
	private int graduationYear;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public GraduateStudent(){
		super();
	}
	public GraduateStudent(String id, String firstName, String lastName, String email, String login,
            Date birthDate, String hashedPassword,
            int	graduationYear){
		super(id, firstName, lastName, email, login, birthDate, hashedPassword);
		this.graduationYear = graduationYear;
		this.reseacherProfile = new Researcher(
                this.uuid, 
                this,
                0.0
            );
	}
	public GraduateStudent(String id, String firstName, String lastName, String email, String login,
            Date birthDate, String hashedPassword){
		super(id, firstName, lastName, email, login, birthDate, hashedPassword);
		this.reseacherProfile = new Researcher(
                this.uuid, 
                this,
                0.0
            );

	}
		

}

