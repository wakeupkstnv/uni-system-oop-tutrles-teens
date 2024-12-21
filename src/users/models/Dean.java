package users.models;


import post.Request;
import users.Faculty;
import users.TeacherType;

import java.util.Date;
import java.util.Vector;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Dean extends Manager
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	
	private Faculty faculty;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public Dean(String id, String firstName, String lastName, String email, String login, Date birthDate, 
			String hashedPassword, Faculty faculty) {
		super(id, firstName, lastName, email, login, birthDate, hashedPassword);
		this.faculty = faculty;
		this.allRequests = new Vector<>();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void rejectRequest(Request request) {
		if(allRequests.contains(request)) {
			 allRequests.remove(request);
			 request.setSigned();
		}
		// TODO implement me
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void signRequest(Request request) {
		// TODO implement me
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void redirectRequest(Request request, Manager manager) {
		// TODO implement me
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	}

