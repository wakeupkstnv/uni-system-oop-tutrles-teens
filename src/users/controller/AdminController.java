package users.controller;

import java.util.Date;
import java.util.Vector;
import java.io.BufferedReader;


import database.Database;
import users.models.*;

import users.models.User;

import users.UserType;
import users.view.AdminView;

public class AdminController<Model extends Admin, View extends AdminView> extends ManagerController<Admin, AdminView> {
    private Database db;


    public AdminController(Database db) {
        super();
        this.db = db;
    }

    public void setModel(Manager model, AdminView view) {
        this.currentModel = model;
        this.currentView = view;
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
    public void registerUser(int choice, String uuid, String firstName, String lastName, String email, String login, Date birthDate, BufferedReader reader) {

        while (choice != 0) {
            try {

                switch (choice) {
                    case 1:
                        registerSpecificUser(UserType.EMPLOYEE, uuid, firstName, lastName, email, login, birthDate, new UserFactory(), reader);
                        break;

                    case 2:
                        registerSpecificUser(UserType.STUDENT, uuid, firstName, lastName, email, login, birthDate, new UserFactory(), reader);
                        break;

                    case 3:
                        registerSpecificUser(UserType.TEACHER, uuid, firstName, lastName, email, login, birthDate, new UserFactory(), reader);
                        break;

                    case 4:
                        registerSpecificUser(UserType.ADMIN, uuid, firstName, lastName, email, login, birthDate, new UserFactory(), reader);
                        break;

                    case 5:
                        registerSpecificUser(UserType.DEAN, uuid, firstName, lastName, email, login, birthDate, new UserFactory(), reader);
                        break;

                    case 6:
                        registerSpecificUser(UserType.GRADUATED_STUDENT, uuid, firstName, lastName, email, login, birthDate, new UserFactory(), reader);
                        break;

                    case 7:
                        registerSpecificUser(UserType.PHD_STUDENT, uuid, firstName, lastName, email, login, birthDate, new UserFactory(), reader);
                        break;

                    case 8:
                        registerSpecificUser(UserType.MASTER_STUDENT, uuid, firstName, lastName, email, login, birthDate, new UserFactory(), reader);
                        break;

                    case 9:
                        registerSpecificUser(UserType.MANAGER, uuid, firstName, lastName, email, login, birthDate, new UserFactory(), reader);
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
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format. Please enter a valid integer.");
            }
        }
    }

    /**
     * Метод для регистрации конкретного типа пользователя
     */
    private void registerSpecificUser(UserType userType, String uuid, String firstName, String lastName, String email, String login, Date birthDate, UserFactory userFactory, BufferedReader reader) {


        db.addLog(""+userType);
        db.addUser(userFactory.createUser(uuid, firstName, lastName, email, login, birthDate, userType, reader));
        System.out.println(userType + " successfully registered!");
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
