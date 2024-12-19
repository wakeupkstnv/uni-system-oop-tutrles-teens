package users.controller;





import java.text.SimpleDateFormat;
import papers.ResearchPaper;
import users.models.User;
import users.UserType;
import users.controller.AdminController;
import users.TeacherType;
import users.models.Employee;
import users.models.Manager;
import users.models.Student;
import users.models.Dean;
import users.models.Teacher;
import users.models.Researcher;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;
import java.util.Vector;

import Database.Database;


// TODO ban and unBan methods.


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class AdminController extends ManagerController
{
	private Database db;
	private User currentUser;

    public AdminController(Database db) {
        super();
    	this.db = db;
        // You can initialize with some default actions if needed
    }

    // Set the current user (for example, the admin performing the actions)
    public void setModel(User user) {
        this.currentUser = user;
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
	
	public void deleteUser(User user) {
		// TODO implement me	
		db.removeUser(user);
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
                + "10: Manager\n"
                + "11: View info about types!\n"
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
                    System.out.print("Enter Email: ");
                    String empEmail = scanner.nextLine();
                    System.out.print("Enter Login: ");
                    String empLogin = scanner.nextLine();
                    System.out.print("Enter Birth Date (yyyy-MM-dd): ");
                    String empBirthDateStr = scanner.nextLine();
                    Date empBirthDate = null;
                    try {
                        empBirthDate = new SimpleDateFormat("yyyy-MM-dd").parse(empBirthDateStr); // Преобразуем строку в объект Date
                    } catch (ParseException e) {
                        System.out.println("Invalid date format. Please use yyyy-MM-dd.");
                        continue;
                    }
                    User employee = userFactory.createUser(empId, empFirstName, empLastName, empEmail, empLogin, empBirthDate, UserType.EMPLOYEE, scanner);
                    String empLog = "Employee registered: " + employee; 
                    db.addLog(empLog);
                    db.addUser(employee);
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
                    System.out.print("Enter Email: ");
                    String stuEmail = scanner.nextLine();
                    System.out.print("Enter Login: ");
                    String stuLogin = scanner.nextLine();
                    System.out.print("Enter Birth Date (yyyy-MM-dd): ");
                    String stBirthDateStr = scanner.nextLine();
                    Date stBirthDate = null;
                    try {
                        stBirthDate = new SimpleDateFormat("yyyy-MM-dd").parse(stBirthDateStr); // Преобразуем строку в объект Date
                    } catch (ParseException e) {
                        System.out.println("Invalid date format. Please use yyyy-MM-dd.");
                        continue;
                    }
                    User student = userFactory.createUser(stuId, stuFirstName, stuLastName, stuEmail, stuLogin, stBirthDate, UserType.STUDENT, scanner);
                    String stdLog = "Student registered: " + student; 
                    db.addLog(stdLog);
                    db.addUser(student);
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
                    System.out.print("Enter Email: ");
                    String teacherEmail = scanner.nextLine();
                    System.out.print("Enter Login: ");
                    String teacherLogin = scanner.nextLine();
                    System.out.print("Enter Birth Date (yyyy-MM-dd): ");
                    String teBirthDateStr = scanner.nextLine();
                    Date teBirthDate = null;
                    try {
                        teBirthDate = new SimpleDateFormat("yyyy-MM-dd").parse(teBirthDateStr); // Преобразуем строку в объект Date
                    } catch (ParseException e) {
                        System.out.println("Invalid date format. Please use yyyy-MM-dd.");
                        continue;
                    }
                    User teacher = userFactory.createUser(teacherId, teacherFirstName, teacherLastName, teacherEmail, teacherLogin, teBirthDate, UserType.TEACHER, scanner);
                    String teaLog = "Teacher registered: " + teacher; 
                    db.addLog(teaLog);
                    db.addUser(teacher);
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
                    System.out.print("Enter Email: ");
                    String adminEmail = scanner.nextLine();
                    System.out.print("Enter Login: ");
                    String adminLogin = scanner.nextLine();
                    System.out.print("Enter Birth Date (yyyy-MM-dd): ");
                    String adBirthDateStr = scanner.nextLine();
                    Date adBirthDate = null;
                    try {
                        adBirthDate = new SimpleDateFormat("yyyy-MM-dd").parse(adBirthDateStr); // Преобразуем строку в объект Date
                    } catch (ParseException e) {
                        System.out.println("Invalid date format. Please use yyyy-MM-dd.");
                        continue;
                    }
                    User admin = userFactory.createUser(adminId, adminFirstName, adminLastName, adminEmail, adminLogin, adBirthDate, UserType.ADMIN, scanner);
                    String admLog = "Admin registered: " + admin; 
                    db.addLog(admLog);
                    db.addUser(admin);
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
                    System.out.print("Enter Email: ");
                    String deanEmail = scanner.nextLine();
                    System.out.print("Enter Login: ");
                    String deanLogin = scanner.nextLine();
                    System.out.print("Enter Birth Date (yyyy-MM-dd): ");
                    String deBirthDateStr = scanner.nextLine();
                    Date deBirthDate = null;
                    try {
                        deBirthDate = new SimpleDateFormat("yyyy-MM-dd").parse(deBirthDateStr); // Преобразуем строку в объект Date
                    } catch (ParseException e) {
                        System.out.println("Invalid date format. Please use yyyy-MM-dd.");
                        continue;
                    }
                    User dean = userFactory.createUser(deanId, deanFirstName, deanLastName, deanEmail, deanLogin, deBirthDate, UserType.DEAN, scanner);
                    String deaLog = "Dean registered: " + dean; 
                    db.addLog(deaLog);
                    db.addUser(dean);
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
                    System.out.print("Enter Email: ");
                    String researcherEmail = scanner.nextLine();
                    System.out.print("Enter Login: ");
                    String researcherLogin = scanner.nextLine();
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
                    User researcher = userFactory.createUser(researcherId, researcherFirstName, researcherLastName, researcherEmail, researcherLogin, reBirthDate, UserType.RESEARCHER, scanner);
                    String reaLog = "Researcher registered: " + researcher; 
                    db.addLog(reaLog);
                    db.addUser(researcher);
                    break;

                case 7: // Graduated Student
                    System.out.println("You chose: Graduated Student");
                    // Код для Graduated Student
                    System.out.print("Enter ID: ");
                    String graduatedId = scanner.nextLine();
                    System.out.print("Enter First Name: ");
                    String graduatedFirstName = scanner.nextLine();
                    System.out.print("Enter Last Name: ");
                    String graduatedLastName = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String graduatedEmail = scanner.nextLine();
                    System.out.print("Enter Login: ");
                    String graduatedLogin = scanner.nextLine();
                    System.out.print("Enter Birth Date (yyyy-MM-dd): ");
                    String graduatedBirthDateStr = scanner.nextLine();
                    Date graduatedBirthDate = null;
                    try {
                        graduatedBirthDate = new SimpleDateFormat("yyyy-MM-dd").parse(graduatedBirthDateStr);
                    } catch (ParseException e) {
                        System.out.println("Invalid date format. Please use yyyy-MM-dd.");
                        continue;
                    }
                    // Создаем объект Graduated Student
                    User graduatedStudent = userFactory.createUser(graduatedId, graduatedFirstName, graduatedLastName, graduatedEmail, graduatedLogin, graduatedBirthDate, UserType.GRADUATED_STUDENT, scanner);
                    String grdLog = "Graduated Student registered: " + graduatedStudent; 
                    db.addLog(grdLog);
                    db.addUser(graduatedStudent);
                    break;

                case 8: // PhD Student
                    System.out.println("You chose: PhD Student");
                    // Чтение данных для PhD Student
                    System.out.print("Enter ID: ");
                    String phdId = scanner.nextLine();
                    System.out.print("Enter First Name: ");
                    String phdFirstName = scanner.nextLine();
                    System.out.print("Enter Last Name: ");
                    String phdLastName = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String phdEmail = scanner.nextLine();
                    System.out.print("Enter Login: ");
                    String phdLogin = scanner.nextLine();
                    System.out.print("Enter Birth Date (yyyy-MM-dd): ");
                    String phdBirthDateStr = scanner.nextLine();
                    Date phdBirthDate = null;
                    try {
                        phdBirthDate = new SimpleDateFormat("yyyy-MM-dd").parse(phdBirthDateStr); // Преобразуем строку в объект Date
                    } catch (ParseException e) {
                        System.out.println("Invalid date format. Please use yyyy-MM-dd.");
                        continue;
                    }
                    User phdStudent = userFactory.createUser(phdId, phdFirstName, phdLastName, phdEmail, phdLogin, phdBirthDate, UserType.PHD_STUDENT, scanner);
                    String phdLog = "PhD Student registered: " + phdStudent; 
                    db.addLog(phdLog);
                    db.addUser(phdStudent);
                    break;

                case 9: // Master Student
                    System.out.println("You chose: Master Student");
                    // Чтение данных для Master Student
                    System.out.print("Enter ID: ");
                    String masterId = scanner.nextLine();
                    System.out.print("Enter First Name: ");
                    String masterFirstName = scanner.nextLine();
                    System.out.print("Enter Last Name: ");
                    String masterLastName = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String masterEmail = scanner.nextLine();
                    System.out.print("Enter Login: ");
                    String masterLogin = scanner.nextLine();
                    System.out.print("Enter Birth Date (yyyy-MM-dd): ");
                    String masterBirthDateStr = scanner.nextLine();
                    Date masterBirthDate = null;
                    try {
                        masterBirthDate = new SimpleDateFormat("yyyy-MM-dd").parse(masterBirthDateStr); // Преобразуем строку в объект Date
                    } catch (ParseException e) {
                        System.out.println("Invalid date format. Please use yyyy-MM-dd.");
                        continue;
                    }
                    User masterStudent = userFactory.createUser(masterId, masterFirstName, masterLastName, masterEmail, masterLogin, masterBirthDate, UserType.MASTER_STUDENT, scanner);
                    String mstLog = "Master Student registered: " + masterStudent; 
                    db.addLog(mstLog);
                    db.addUser(masterStudent);
                    break;
                    
                case 10:
                	System.out.println("You chose: Manager");
                    // Чтение данных для PhD Student
                    System.out.print("Enter ID: ");
                    String manId = scanner.nextLine();
                    System.out.print("Enter First Name: ");
                    String manFirstName = scanner.nextLine();
                    System.out.print("Enter Last Name: ");
                    String manLastName = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String manEmail = scanner.nextLine();
                    System.out.print("Enter Login: ");
                    String manLogin = scanner.nextLine();
                    System.out.print("Enter Birth Date (yyyy-MM-dd): ");
                    String manBirthDateStr = scanner.nextLine();
                    Date manBirthDate = null;
                    try {
                        manBirthDate = new SimpleDateFormat("yyyy-MM-dd").parse(manBirthDateStr); // Преобразуем строку в объект Date
                    } catch (ParseException e) {
                        System.out.println("Invalid date format. Please use yyyy-MM-dd.");
                        continue;
                    }
                    User manager = userFactory.createUser(manId, manFirstName, manLastName, manEmail, manLogin, manBirthDate, UserType.MANAGER, scanner);
                    String mngLog = "Manager registered: " + manager; 
                    db.addLog(mngLog);
                    db.addUser(manager);
                case 11: // View info about types
                    System.out.println("Displaying information about user types...");
                    // Можете добавить вывод информации о каждом типе пользователя
                    System.out.println("1: Employee - Works in the company or organization.");
                    System.out.println("2: Student - A student enrolled in the university.");
                    System.out.println("3: Teacher - An academic staff member.");
                    System.out.println("4: Admin - Manages the platform or system.");
                    System.out.println("5: Dean - Head of the department or faculty.");
                    System.out.println("6: Researcher - Conducts scientific research.");
                    System.out.println("7: Graduated Student - A student who has completed their degree.");
                    System.out.println("8: PhD Student - A student pursuing a Doctor of Philosophy degree.");
                    System.out.println("9: Master Student - A student pursuing a Master's degree.");
                    System.out.println("10: View info about types - Displays user types.");
                    break;

                case 0: // Exit
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Vector<String> viewLogs() {
		// TODO implement me
		return db.getAllLogs();
	}
	
}

