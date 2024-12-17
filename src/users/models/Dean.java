package users.models;


import post.Request;
import users.Faculty;

import java.util.Date;
import java.util.Vector;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Dean extends Employee
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private Vector<Request> allRequests;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private Faculty faculty;

	/**
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 *
	 * @param uuid
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param login
	 * @param birthDate
	 * @param middleName
	 * @param year
	 * @generated
	 */
	public Dean(String uuid, String firstName, String lastName, String email, String login, Date birthDate, String middleName, int year) {
		super(uuid, firstName, lastName, email, login, birthDate, middleName, year);
		allRequests = new Vector<>();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void rejectRequest(Request request) {
		request.setSigned(false);
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
		request.setSigned(true);
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void redirectRequest(Request request, Manager manager) {

	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	}

