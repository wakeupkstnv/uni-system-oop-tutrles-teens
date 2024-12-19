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
            String hashedPassword, Vector<String> notifications, int masterCourse, int masterEnrollmentYear,
            String dissertationTitle, Researcher supervisor) {
        //TODO Auto-generated constructor stub
		super(id,firstName,lastName,email,login,birthDate,hashedPassword,notifications,masterCourse,masterEnrollmentYear);
		this.dissertationTitle=dissertationTitle;
		this.supervisor=supervisor;
    }

}

