package users.view;


import post.News;
import users.UserType;
import users.controller.AdminController;
import core.CoreSystem;
import core.Language;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import core.CoreSystem;
import core.Language;


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

		if(CoreSystem.getLanguageMode() == Language.ENG) {
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

				// Generate email
				String email = firstName.toLowerCase().charAt(0) + "_" + lastName.toLowerCase() + "@kuas.jp";
				System.out.println("Generated Email: " + email);

				// Generate login (without @kuas.jp)
				String login = firstName.toLowerCase().charAt(0) + "_" + lastName.toLowerCase();
				System.out.println("Generated Login: " + login);

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
		else if (CoreSystem.getLanguageMode() == Language.KZ) {
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

				// Генерация email
				String email = firstName.toLowerCase().charAt(0) + "_" + lastName.toLowerCase() + "@kuas.jp";
				System.out.println("Теңшелген Email: " + email);

				// Генерация логина (без @kuas.jp)
				String login = firstName.toLowerCase().charAt(0) + "_" + lastName.toLowerCase();
				System.out.println("Теңшелген Логин: " + login);

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

				// Генерация email
				String email = firstName.toLowerCase().charAt(0) + "_" + lastName.toLowerCase() + "@kuas.jp";
				System.out.println("Сгенерированный Email: " + email);

				// Генерация логина (без @kuas.jp)
				String login = firstName.toLowerCase().charAt(0) + "_" + lastName.toLowerCase();
				System.out.println("Сгенерированный Логин: " + login);

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

