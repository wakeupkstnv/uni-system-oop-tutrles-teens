package Users.Models;




import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.text.ParseException;



/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Admin extends Manager
{
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public Admin(){
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void banUser() {
		// TODO implement me	
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void unBanUser() {
		// TODO implement me	
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void deleteUser() {
		// TODO implement me	
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	// надо добавить логику подключений к базе данных

	public void registerUser() {
	    Scanner scanner = new Scanner(System.in);
	    UserFactory userFactory = new UserFactory(); // Создаем объект фабрики
	    System.out.println(
	            "Please choose a type of User!\n"
	            + "1: Employee \n"
	            + "2: Student \n"
	            + "3: Teacher\n"
	            + "4: Admin\n"
	            + "5: Dean\n"
	            + "6: Researcher\n"
	            + "7: Graduated Student\n"
	            + "8: PHD Student\n"
	            + "9: Master Student\n"
	            + "10: View info about types!\n"
	            + "0: Exit\n");
	    int choice = -1;
	    while (choice != 0) {
	        System.out.print("Enter your choice: ");
	        choice = scanner.nextInt();
	        scanner.nextLine(); // Очистка буфера

	        switch (choice) {
	            case 1: // Employee
	                System.out.println("You chose: Employee");
	                // Чтение данных для Employee
	                System.out.print("Enter ID: ");
	                String empId = scanner.nextLine();
	                System.out.print("Enter First Name: ");
	                String empFirstName = scanner.nextLine();
	                System.out.print("Enter Last Name: ");
	                String empLastName = scanner.nextLine();
	                System.out.print("Enter Birth Date (yyyy-MM-dd): ");
	                String empBirthDateStr = scanner.nextLine();
	                Date empBirthDate = null;
	                try {
	                	empBirthDate = new SimpleDateFormat("yyyy-MM-dd").parse(empBirthDateStr); // Преобразуем строку в объект Date
	                } catch (ParseException e) {
	                    System.out.println("Invalid date format. Please use yyyy-MM-dd.");
	                    continue;
	                }
	                User employee = userFactory.createUser(empId, empFirstName, empLastName, empBirthDate, UserType.EMPLOYEE, scanner);
	                System.out.println("Employee registered: " + employee);
	                break;

	            case 2: // Student
	                System.out.println("You chose: Student");
	                // Чтение данных для Student
	                System.out.print("Enter ID: ");
	                String stuId = scanner.nextLine();
	                System.out.print("Enter First Name: ");
	                String stuFirstName = scanner.nextLine();
	                System.out.print("Enter Last Name: ");
	                String stuLastName = scanner.nextLine();
	                System.out.print("Enter Birth Date (yyyy-MM-dd): ");
	                String stBirthDateStr = scanner.nextLine();
	                Date stBirthDate = null;
	                try {
	                	stBirthDate = new SimpleDateFormat("yyyy-MM-dd").parse(stBirthDateStr); // Преобразуем строку в объект Date
	                } catch (ParseException e) {
	                    System.out.println("Invalid date format. Please use yyyy-MM-dd.");
	                    continue;
	                }
	                User student = userFactory.createUser(stuId, stuFirstName, stuLastName, stBirthDate, UserType.STUDENT, scanner);
	                System.out.println("Student registered: " + student);
	                break;

	            case 3: // Teacher
	                System.out.println("You chose: Teacher");
	                // Чтение данных для Teacher
	                System.out.print("Enter ID: ");
	                String teacherId = scanner.nextLine();
	                System.out.print("Enter First Name: ");
	                String teacherFirstName = scanner.nextLine();
	                System.out.print("Enter Last Name: ");
	                String teacherLastName = scanner.nextLine();
	                System.out.print("Enter Birth Date (yyyy-MM-dd): ");
	                String teBirthDateStr = scanner.nextLine();
	                Date teBirthDate = null;
	                try {
	                	teBirthDate = new SimpleDateFormat("yyyy-MM-dd").parse(teBirthDateStr); // Преобразуем строку в объект Date
	                } catch (ParseException e) {
	                    System.out.println("Invalid date format. Please use yyyy-MM-dd.");
	                    continue;
	                }
	                User teacher = userFactory.createUser(teacherId, teacherFirstName, teacherLastName, teBirthDate, UserType.TEACHER, scanner);
	                System.out.println("Teacher registered: " + teacher);
	                break;

	            case 4: // Admin
	                System.out.println("You chose: Admin");
	                
	                // Чтение данных для Admin
	                System.out.print("Enter ID: ");
	                String adminId = scanner.nextLine();
	                System.out.print("Enter First Name: ");
	                String adminFirstName = scanner.nextLine();
	                System.out.print("Enter Last Name: ");
	                String adminLastName = scanner.nextLine();
	                System.out.print("Enter Birth Date (yyyy-MM-dd): ");
	                String adBirthDateStr = scanner.nextLine();
	                Date adBirthDate = null;
	                try {
	                	adBirthDate = new SimpleDateFormat("yyyy-MM-dd").parse(adBirthDateStr); // Преобразуем строку в объект Date
	                } catch (ParseException e) {
	                    System.out.println("Invalid date format. Please use yyyy-MM-dd.");
	                    continue;
	                }
	                User admin = userFactory.createUser(adminId, adminFirstName, adminLastName, adBirthDate, UserType.ADMIN, scanner);
	                System.out.println("Admin registered: " + admin);
	                break;

	            case 5: // Dean
	                System.out.println("You chose: Dean");
	                // Чтение данных для Dean
	                System.out.print("Enter ID: ");
	                String deanId = scanner.nextLine();
	                System.out.print("Enter First Name: ");
	                String deanFirstName = scanner.nextLine();
	                System.out.print("Enter Last Name: ");
	                String deanLastName = scanner.nextLine();
	                System.out.print("Enter Birth Date (yyyy-MM-dd): ");
	                String deBirthDateStr = scanner.nextLine();
	                Date deBirthDate = null;
	                try {
	                	deBirthDate = new SimpleDateFormat("yyyy-MM-dd").parse(deBirthDateStr); // Преобразуем строку в объект Date
	                } catch (ParseException e) {
	                    System.out.println("Invalid date format. Please use yyyy-MM-dd.");
	                    continue;
	                }
	                User dean = userFactory.createUser(deanId, deanFirstName, deanLastName, deBirthDate, UserType.DEAN, scanner);
	                System.out.println("Dean registered: " + dean);
	                break;

	            case 6: // Researcher
	                System.out.println("You chose: Researcher");
	                // Чтение данных для Researcher
	                System.out.print("Enter ID: ");
	                String researcherId = scanner.nextLine();
	                System.out.print("Enter First Name: ");
	                String researcherFirstName = scanner.nextLine();
	                System.out.print("Enter Last Name: ");
	                String researcherLastName = scanner.nextLine();
	                System.out.print("Enter Birth Date (yyyy-MM-dd): ");
	                String reBirthDateStr = scanner.nextLine();
	                Date reBirthDate = null;
	                try {
	                	reBirthDate = new SimpleDateFormat("yyyy-MM-dd").parse(reBirthDateStr); // Преобразуем строку в объект Date
	                } catch (ParseException e) {
	                    System.out.println("Invalid date format. Please use yyyy-MM-dd.");
	                    continue;
	                }
	                // Используем фабрику для создания Researcher
	                User researcher = userFactory.createUser(researcherId, researcherFirstName, researcherLastName, reBirthDate, UserType.RESEARCHER, scanner);
	                System.out.println("Researcher registered: " + researcher);
	                break;
	            case 7: // Graduated Student
	                System.out.println("You chose: Graduated Student");
	                // Code for Graduated Student
	                System.out.print("Enter ID: ");
	                String graduatedId = scanner.nextLine();
	                System.out.print("Enter First Name: ");
	                String graduatedFirstName = scanner.nextLine();
	                System.out.print("Enter Last Name: ");
	                String graduatedLastName = scanner.nextLine();
	                System.out.print("Enter Birth Date (yyyy-MM-dd): ");
	                String graduatedBirthDateStr = scanner.nextLine();
	                Date graduatedBirthDate = null;
	                try {
	                    graduatedBirthDate = new SimpleDateFormat("yyyy-MM-dd").parse(graduatedBirthDateStr);
	                } catch (ParseException e) {
	                    System.out.println("Invalid date format. Please use yyyy-MM-dd.");
	                    continue;
	                }
	                User graduatedStudent = userFactory.createUser(graduatedId, graduatedFirstName, graduatedLastName, graduatedBirthDate, UserType.GRADUATED_STUDENT, scanner);
	                System.out.println("Graduated Student registered: " + graduatedStudent);
	                break;

	            case 8: // PhD Student
	                System.out.println("You chose: PhD Student");
	                // Code for PhD Student
	                System.out.print("Enter ID: ");
	                String phdId = scanner.nextLine();
	                System.out.print("Enter First Name: ");
	                String phdFirstName = scanner.nextLine();
	                System.out.print("Enter Last Name: ");
	                String phdLastName = scanner.nextLine();
	                System.out.print("Enter Birth Date (yyyy-MM-dd): ");
	                String phdBirthDateStr = scanner.nextLine();
	                Date phdBirthDate = null;
	                try {
	                    phdBirthDate = new SimpleDateFormat("yyyy-MM-dd").parse(phdBirthDateStr);
	                } catch (ParseException e) {
	                    System.out.println("Invalid date format. Please use yyyy-MM-dd.");
	                    continue;
	                }
	                User phdStudent = userFactory.createUser(phdId, phdFirstName, phdLastName, phdBirthDate, UserType.PHD_STUDENT, scanner);
	                System.out.println("PhD Student registered: " + phdStudent);
	                break;

	            case 9: // Master Student
	                System.out.println("You chose: Master Student");
	                // Code for Master Student
	                System.out.print("Enter ID: ");
	                String masterId = scanner.nextLine();
	                System.out.print("Enter First Name: ");
	                String masterFirstName = scanner.nextLine();
	                System.out.print("Enter Last Name: ");
	                String masterLastName = scanner.nextLine();
	                System.out.print("Enter Birth Date (yyyy-MM-dd): ");
	                String masterBirthDateStr = scanner.nextLine();
	                Date masterBirthDate = null;
	                try {
	                    masterBirthDate = new SimpleDateFormat("yyyy-MM-dd").parse(masterBirthDateStr);
	                } catch (ParseException e) {
	                    System.out.println("Invalid date format. Please use yyyy-MM-dd.");
	                    continue;
	                }
	                User masterStudent = userFactory.createUser(masterId, masterFirstName, masterLastName, masterBirthDate, UserType.MASTER_STUDENT, scanner);
	                System.out.println("Master Student registered: " + masterStudent);
	                break;
	                
	            case 10:
	                System.out.println("Info about types:\n" +
	                        "1: Employee - Works at the institution.\n" +
	                        "2: Student - A regular student enrolled in courses.\n" +
	                        "3: Teacher - Teaches classes and evaluates students.\n" +
	                        "4: Admin - Manages administrative tasks.\n" +
	                        "5: Dean - Head of the faculty or department.\n" +
	                        "6: Researcher - Conducts academic or scientific research.\n" +
	                        "7: Graduated Student - A student who has completed their degree.\n" +
	                        "8: PHD Student - Pursuing a Doctorate degree.\n" +
	                        "9: Master Student - Pursuing a Master’s degree.");
	                break;

	            case 0:
	                System.out.println("Exiting registration...");
	                break;

	            default:
	                System.out.println("Invalid choice. Please try again.");
	        }
	    }

	    scanner.close();
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public String viewLogs() {
		// TODO implement me
		return "";
	}
	
}

