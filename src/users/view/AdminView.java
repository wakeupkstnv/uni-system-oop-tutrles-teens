package users.view;


import Core.Language;
import post.News;
import users.UserType;
import users.controller.AdminController;
import Core.CoreSystem;
import Core.Language;

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

		if(language == Language.ENG) {
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
		else if (language == Language.KZ) {
			System.out.println(
					"Пайдаланушы түрін таңдаңыз!\n"
							+ "1: Қызметкер \n"
							+ "2: Студент \n"
							+ "3: Мұғалім\n"
							+ "4: Админ\n"
							+ "5: Декан\n"
							+ "6: Бітірген студент\n"
							+ "7: PhD студенті\n"
							+ "8: Магистрант\n"
							+ "9: Менеджер\n"
							+ "10: Түрлер туралы ақпаратты көру!\n"
							+ "0: Шығу\n");

			int choice = reader.read();

			try {
				System.out.print("ID енгізіңіз: ");
				String uuid = reader.readLine();
				System.out.print("Аты енгізіңіз: ");
				String firstName = reader.readLine();
				System.out.print("Тегі енгізіңіз: ");
				String lastName = reader.readLine();
				System.out.print("Электрондық пошта енгізіңіз: ");
				String email = reader.readLine();
				System.out.print("Логин енгізіңіз: ");
				String login = reader.readLine();
				System.out.print("Туған күн (yyyy-MM-dd) енгізіңіз: ");
				String birthDateStr = reader.readLine();
				Date birthDate = null;
				try {
					birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(birthDateStr);
				} catch (ParseException e) {
					System.out.println("Қате күн форматы. Күні yyyy-MM-dd форматында болу керек.");
					return;
				}
				adminController.registerUser(choice, uuid, firstName, lastName, email, login, birthDate, reader);
				} catch (IOException e) {
					System.out.println("Кіріс деректерін оқу кезінде қате пайда болды. Қайтадан әрекет етіңіз.");
				}

		}
		else {
			System.out.println(
					"Пожалуйста, выберите тип пользователя!\n"
							+ "1: Сотрудник \n"
							+ "2: Студент \n"
							+ "3: Преподаватель\n"
							+ "4: Администратор\n"
							+ "5: Декан\n"
							+ "6: Выпускник\n"
							+ "7: PhD студент\n"
							+ "8: Магистрант\n"
							+ "9: Менеджер\n"
							+ "10: Просмотреть информацию о типах!\n"
							+ "0: Выход\n");

			int choice = reader.read();

			try {
				System.out.print("Введите ID: ");
				String uuid = reader.readLine();
				System.out.print("Введите имя: ");
				String firstName = reader.readLine();
				System.out.print("Введите фамилию: ");
				String lastName = reader.readLine();
				System.out.print("Введите Email: ");
				String email = reader.readLine();
				System.out.print("Введите логин: ");
				String login = reader.readLine();
				System.out.print("Введите дату рождения (yyyy-MM-dd): ");
				String birthDateStr = reader.readLine();
				Date birthDate = null;
				try {
					birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(birthDateStr);
				} catch (ParseException e) {
					System.out.println("Неверный формат даты. Пожалуйста, используйте формат yyyy-MM-dd.");
					return;
				}
				adminController.registerUser(choice, uuid, firstName, lastName, email, login, birthDate, reader);
				} catch (IOException e) {
					System.out.println("Произошла ошибка при чтении данных. Пожалуйста, попробуйте снова.");
				}

		}
		
	}
	
	private void displayUserTypes() {
        System.out.println("Displaying information about user types...");
        System.out.println("1: Employee - Works in the company or organization.");
        System.out.println("2: Student - A student enrolled in the university.");
        System.out.println("3: Teacher - An academic staff member.");
        System.out.println("4: Admin - Manages the platform or system.");
        System.out.println("5: Dean - Head of the department or faculty.");
        System.out.println("6: Researcher - Conducts scientific research.");
        System.out.println("7: Graduated Student - A student who has completed their degree.");
        System.out.println("8: PhD Student - A student pursuing a Doctor of Philosophy degree.");
        System.out.println("9: Master Student - A student pursuing a Master's degree.");
        System.out.println("10: View info about user types - Displays user types.");
    }

	
	public void registerSpecificUserSucces(UserType userType) {
		System.out.println(userType + " successfully registered!");
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

