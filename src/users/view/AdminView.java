package users.view;


import post.News;
import users.controller.AdminController;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class AdminView extends ManagerView
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public AdminView(){
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	public static void showRegisterUser(AdminController adminController, BufferedReader reader) {
		try {
			System.out.print("Enter ID: ");
			String id = reader.readLine();
			System.out.print("Enter First Name: ");
			String firstName = reader.readLine();
			System.out.print("Enter Last Name: ");
			String lastName = reader.readLine();
			System.out.print("Enter Email: ");
			String email = reader.readLine();
			System.out.print("Enter Login: ");
			String login = reader.readLine();
			System.out.print("Enter Birth Date (yyyy-MM-dd): ");
			String birthDateStr = reader.readLine();
			Date birthDate = null;
			try {
				birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(birthDateStr);
			} catch (ParseException e) {
				System.out.println("Invalid date format. Please use yyyy-MM-dd.");
				return;
			}
			} catch (IOException e) {
				System.out.println("An error occurred while reading input. Please try again.");
			}

		adminController.registerUser(uuid, ..);
	}

	public void showLoginResult(boolean success) {
		// TODO implement me
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void showLogoutMessage() {
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
	
	public void showSubscriptionResult(boolean subscribed) {
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
	
	public void showError(String msg) {
		// TODO implement me
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void showAllLogs() {
		// TODO implement me
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void showUserLogs(String id) {
		// TODO implement me
	}
	
}

