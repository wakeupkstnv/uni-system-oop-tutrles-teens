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

	public static void showRegisterUser(AdminController adminController, BufferedReader reader) throws IOException {
		System.out.println(
				"Please choose a type of User!\n"
						+ "1: Employee \n"
						+ "2: Student \n"
						+ "3: Teacher\n"
						+ "4: Admin\n"
						+ "5: Dean\n"
						+ "6: Graduated Student\n"
						+ "7: PhD Student\n"
						+ "8: Master Student\n"
						+ "9: Manager\n"
						+ "10: View info about types!\n"
						+ "0: Exit\n");

		int choice;
		try {
			choice = Integer.parseInt(reader.readLine());
		} catch (NumberFormatException e) {
			System.out.println("Invalid choice! Please enter a valid number.");
			return;
		}

		try {
			System.out.print("Enter ID: ");
			String uuid = reader.readLine();

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

			adminController.registerUser(choice, uuid, firstName, lastName, email, login, birthDate, reader);

		} catch (IOException e) {
			System.out.println("An error occurred while reading input. Please try again.");
		}
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

