import database.Database;
import post.News;
import study.utils.Course;
import users.Faculty;
import users.controller.AdminController;
import users.controller.ManagerController;
import users.controller.UserController;
import users.models.Admin;
import users.models.Manager;
import users.models.User;
import users.view.AdminView;
import users.view.ManagerView;
import users.view.UserView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        UserController<User, UserView> userController = new UserController<>();

        Admin admin = new Admin("12", "tamir", "kustanayev", "t_kustanayev@kbtu.kz", "t_kustanayev", new Date(), "123");

        // Добавляем пароль для администратора в базу данных
        Database.getInstance().addUserPassword(admin, "123");

        AdminController adminController = new AdminController<>(admin, new AdminView());
        AdminView.showRegisterUser(adminController, reader);

        while (true){
            System.out.println("=== WSP System ===");
            System.out.println("1. Вход");
            System.out.println("2. Выход");
            System.out.print("Выберите опцию: ");
            String choice = reader.readLine();

            switch (choice){
                case "1":
                    handleLogin(reader, userController);
                    break;
                case "2":
                    System.out.println("Выход из системы. До свидания!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Неверный выбор. Пожалуйста, попробуйте снова.");
            }
        }
    }

    private static void handleLogin(BufferedReader reader, UserController<User, UserView> userController) throws IOException {
        System.out.print("Введите логин: ");
        String login = reader.readLine();
        System.out.print("Введите пароль: ");
        String password = reader.readLine();

        // Получаем пароли из базы данных

        User u = Database.getInstance().getUsers()
                .stream()
                .filter(n->n.getLogin().equals(login))
                .findFirst()
                .orElse(null);

        // Проверяем, существует ли пользователь с данным логином
        if (u != null && Database.getInstance().getUserPasswords().containsKey(u)) {
            System.out.println("Неверный логин или пароль. Пожалуйста, попробуйте снова.");
            return;
        }

        // Проверяем, совпадает ли пароль
        String storedPassword = Database.getInstance().getUserPasswords().get(u.getLogin());
        if (storedPassword.equals(password)) {
            System.out.println("Успешный вход! Добро пожаловать, " + u.getFirstName() + "!");
            navigateUserRole(u, reader);
        } else {
            System.out.println("Неверный логин или пароль. Пожалуйста, попробуйте снова.");
        }
    }

    private static void navigateUserRole(User user, BufferedReader reader) throws IOException {
        if (user instanceof Admin) {
            AdminView adminView = new AdminView();
            AdminController<Admin, AdminView> adminController = new AdminController<>((Admin) user, adminView);
            adminMenu(adminController, reader);
        } else if (user instanceof Manager) {
            ManagerView managerView = new ManagerView();
            ManagerController<Manager, ManagerView> managerController = new ManagerController<>((Manager) user, managerView);
            managerMenu(managerController, reader);
        } else {
            UserView userView = new UserView();
            UserController userController = new UserController<>(user, userView);
            userMenu(userController, reader);
        }
    }

    private static void adminMenu(AdminController<Admin, AdminView> adminController, BufferedReader reader) throws IOException {
        while (true){
            System.out.println("\n=== Меню Администратора ===");
            System.out.println("1. Просмотреть профиль");
            System.out.println("2. Просмотреть уведомления");
            System.out.println("3. Управление пользователями");
            System.out.println("4. Выход");
            System.out.print("Выберите опцию: ");
            String choice = reader.readLine();

            switch (choice){
                case "1":
                    adminController.viewProfile();
                    break;
                case "2":
                    adminController.viewNotifactions();
                    break;
                case "3":
                    // Реализуйте методы управления пользователями
                    System.out.println("Функция управления пользователями пока не реализована.");
                    break;
                case "4":
                    System.out.println("Выход из учетной записи администратора.");
                    return;
                default:
                    System.out.println("Неверный выбор. Пожалуйста, попробуйте снова.");
            }
        }
    }

    private static void managerMenu(ManagerController<Manager, ManagerView> managerController, BufferedReader reader) throws IOException {
        while (true){
            System.out.println("\n=== Меню Менеджера ===");
            System.out.println("1. Просмотреть профиль");
            System.out.println("2. Просмотреть уведомления");
            System.out.println("3. Управление проектами");
            System.out.println("4. Выход");
            System.out.print("Выберите опцию: ");
            String choice = reader.readLine();

            switch (choice){
                case "1":
                    managerController.viewProfile();
                    break;
                case "2":
                    managerController.viewNotifactions();
                    break;
                case "3":
                    // Реализуйте методы управления проектами
                    System.out.println("Функция управления проектами пока не реализована.");
                    break;
                case "4":
                    System.out.println("Выход из учетной записи менеджера.");
                    return;
                default:
                    System.out.println("Неверный выбор. Пожалуйста, попробуйте снова.");
            }
        }
    }

    private static void userMenu(UserController<User, UserView> userController, BufferedReader reader) throws IOException {
        while (true){
            System.out.println("\n=== Меню Пользователя ===");
            System.out.println("1. Просмотреть профиль");
            System.out.println("2. Просмотреть уведомления");
            System.out.println("3. Просмотреть статьи");
            System.out.println("4. Просмотреть новости");
            System.out.println("5. Выход");
            System.out.print("Выберите опцию: ");
            String choice = reader.readLine();

            switch (choice){
                case "1":
                    userController.viewProfile();
                    break;
                case "2":
                    userController.viewNotifactions();
                    break;
                case "3":
                    userController.viewPapers();
                    break;
                case "4":
                    userController.viewNews();
                    break;
                case "5":
                    System.out.println("Выход из учетной записи пользователя.");
                    return;
                default:
                    System.out.println("Неверный выбор. Пожалуйста, попробуйте снова.");
            }
        }
    }
}
