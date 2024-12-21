package users.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Database.Database;
import users.models.User;
import users.UserType;
import users.models.Employee;
import users.models.Manager;
import users.models.Student;
import users.models.Dean;
import users.models.Teacher;
import users.models.Researcher;

public class AdminController extends ManagerController {
    private Database db;
    private User currentUser;

    public AdminController(Database db) {
        super();
        this.db = db;
    }

    public void setModel(User user) {
        this.currentUser = user;
    }

    public void banUser(User user) {
        User u = Database.getInstance().getUsers().stream().filter(n->n.equals(user)).findFirst().orElse(null);
        if (u == null) return; // TODO: эксепшены пилите тут создаете их там
        u.setBanned(true);
    }

    public void unBanUser(User user) {
        User u = Database.getInstance().getUsers().stream().filter(n->n.equals(user)).findFirst().orElse(null);
        if (u == null) return; // TODO: эксепшены пилите тут создаете их там
        u.setBanned(false);
    }

    public void deleteUser(User user) {
        db.removeUser(user);
        System.out.println("User " + user.getLogin() + " has been deleted.");
    }

    /**
     * Метод для регистрации пользователей через BufferedReader
     */
    public void registerUser() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        UserFactory userFactory = new UserFactory(); // Создаем объект фабрики
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
        int choice = -1;
        while (choice != 0) {
            try {
                System.out.print("Enter your choice: ");
                String input = reader.readLine();
                choice = Integer.parseInt(input);

                switch (choice) {
                    case 1:
                        registerSpecificUser(reader, userFactory, UserType.EMPLOYEE, "Employee");
                        break;

                    case 2:
                        registerSpecificUser(reader, userFactory, UserType.STUDENT, "Student");
                        break;

                    case 3:
                        registerSpecificUser(reader, userFactory, UserType.TEACHER, "Teacher");
                        break;

                    case 4:
                        registerSpecificUser(reader, userFactory, UserType.ADMIN, "Admin");
                        break;

                    case 5:
                        registerSpecificUser(reader, userFactory, UserType.DEAN, "Dean");
                        break;

                    case 6:
                        registerSpecificUser(reader, userFactory, UserType.GRADUATED_STUDENT, "Graduated Student");
                        break;

                    case 7:
                        registerSpecificUser(reader, userFactory, UserType.PHD_STUDENT, "PhD Student");
                        break;

                    case 8:
                        registerSpecificUser(reader, userFactory, UserType.MASTER_STUDENT, "Master Student");
                        break;

                    case 9:
                        registerSpecificUser(reader, userFactory, UserType.MANAGER, "Manager");
                        break;

                    case 10:
                        displayUserTypes();
                        break;

                    case 0:
                        System.out.println("Exiting...");
                        break;

                    default:
                        System.out.println("Invalid choice! Please try again.");
                }
            } catch (IOException e) {
                System.out.println("An error occurred while reading input. Please try again.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format. Please enter a valid integer.");
            }
        }
    }

    /**
     * Метод для регистрации конкретного типа пользователя
     */
    private void registerSpecificUser(BufferedReader reader, UserFactory userFactory, UserType userType, String userTypeName) {
        try {
            System.out.println("You chose: " + userTypeName);
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

            User user = userFactory.createUser(id, firstName, lastName, email, login, birthDate, userType, reader);
            String logEntry = userTypeName + " registered: " + user;
            db.addLog(logEntry);
            db.addUser(user);
            System.out.println(userTypeName + " successfully registered!");

        } catch (IOException e) {
            System.out.println("An error occurred while reading input. Please try again.");
        }
    }

    /**
     * Метод для отображения информации о типах пользователей
     */
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

    /**
     * Метод для просмотра логов
     */
    public Vector<String> viewLogs() {
        return db.getAllLogs();
    }
}
