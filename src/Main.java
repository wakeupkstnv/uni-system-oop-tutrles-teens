import core.CoreSystem;
import core.Language;
import database.Database;
import users.controller.*;
import users.models.*;
import users.view.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws IOException {
        Database database = Database.getInstance();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            Admin admin = new Admin("12", "tamir", "kustanayev", "t_kustanayev@kbtu.kz", "t_kustanayev", new Date(), "123");
            database.addUserPassword(admin, "123");

            while (true) {
                System.out.println("=== WSP System ===");
                System.out.println("1. Вход");
                System.out.println("2. Выход");
                System.out.print("Выберите опцию: ");
                String choice = reader.readLine();

                switch (choice) {
                    case "1":
                        handleLogin(reader, database);  // Pass the database instead of userController
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
    }

    private static void handleLogin(BufferedReader reader, Database database) throws IOException {
        System.out.print("Введите логин: ");
        String login = reader.readLine();
        System.out.print("Введите пароль: ");
        String password = reader.readLine();

        User u = database.getInstance().getUsers()
                .stream()
                .filter(n -> n.getLogin().equals(login))
                .findFirst()
                .orElse(null);

        System.out.println(database.getInstance().getUserPasswords());
        // Проверяем, существует ли пользователь с данным логином
        if (u == null || !database.getInstance().getUserPasswords().containsKey(u.getLogin())) {
            System.out.println("Неверный логин или пароль. Пожалуйста, попробуйте снова.");
            return;
        }

        // Проверяем, совпадает ли пароль
        String storedPassword = database.getInstance().getUserPasswords().get(u.getLogin());
        if (storedPassword.equals(password)) {
            System.out.println("Успешный вход! Добро пожаловать, " + u.getFirstName() + "!");
            navigateUserRole(u, reader, database);  // Pass database to the navigateUserRole method
        } else {
            System.out.println("Неверный логин или пароль. Пожалуйста, попробуйте снова.");
        }
    }

    private static void navigateUserRole(User user, BufferedReader reader, Database database) throws IOException {
        if (user instanceof Admin) {
            AdminView adminView = new AdminView();
            AdminController<Admin, AdminView> adminController = new AdminController<>((Admin) user, adminView);
            adminMenu(adminController, reader);
        } else if (user instanceof Manager) {
            ManagerView managerView = new ManagerView();
            ManagerController<Manager, ManagerView> managerController = new ManagerController<>((Manager) user, managerView);
            managerMenu(managerController, reader);
        }
        else if(user instanceof Teacher) {
            TeacherView teacherView = new TeacherView();
            TeacherController teacherController = new TeacherController<>((Teacher) user, teacherView);
            TeacherMenu(teacherController, reader);
        }
        else if (user instanceof Student) {
            StudentView studentView = new StudentView();
            StudentController studentController = new StudentController<>((Student) user, studentView);
            StudentMenu(studentController, reader);
        }
        else {
            UserView userView = new UserView();
            UserController<User, UserView> userController = new UserController<>(user, userView);
            userMenu(userController, reader);
        }

    }

    private static void adminMenu(AdminController<Admin, AdminView> adminController, BufferedReader reader) throws IOException {
        while (true){
            System.out.println("\n=== Меню Администратора ===");
            System.out.println("1. Просмотреть профиль");
            System.out.println("2. Просмотреть уведомления");
            System.out.println("3. Просмотреть статьи");
            System.out.println("4. Управление пользователями");
            System.out.println("5. Управление блокировками");
            System.out.println("6. Выход");
            System.out.print("Выберите опцию: ");
            String choice = reader.readLine();
            AdminView adminView = new AdminView();
            switch (choice){
                case "1":
                    adminController.viewProfile();
                    break;
                case "2":
                    adminController.viewNotifactions();
                    break;
                case "3":
                    adminController.viewPapers();
                    break;
                case "4":
                    AdminView.showRegisterUser(adminController, reader);
                    break;
                case "5":
                    adminView.banMenu(adminController, reader);
                    break;
                case "6":
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
    // for example
    // login: a_zhumabay
    // pass: 31dcfd6dfd75eaab11f14cda4bdd4ad334571842e3cedf38cd22628b351e0f55 (while witohut hash)
    private static void TeacherMenu(TeacherController<Teacher, TeacherView> teacherController, BufferedReader reader) throws IOException {
        while (true) {
            System.out.println("\n=== Меню Преподавателя ===");
            System.out.println("1. Просмотреть профиль");
            System.out.println("2. Просмотреть уведомления");
            System.out.println("3. Просмотреть статьи");
            System.out.println("4. Просмотреть новости");
            System.out.println("5. Просмотреть список студентов");
            System.out.println("6. Назначить оценки");
            System.out.println("7. Отправить жалобу декану");
            System.out.println("8. Отправить сообщение сотруднику");
            System.out.println("9. Просмотреть сообщения");
            System.out.println("10. Создать запрос");
            System.out.println("11. Выход");
            System.out.print("Выберите опцию: ");
            String choice = reader.readLine();

            switch (choice) {
                case "1":
                    teacherController.viewProfile();
                    break;
                case "2":
                    teacherController.viewNotifactions();
                    break;
                case "3":
                    teacherController.viewPapers();
                    break;
                case "4":
                    teacherController.viewNews();
                    break;
                case "5":
                    teacherController.viewStudents();
                    break;
                case "6":
                    teacherController.assignGrades(reader);
                    break;
                case "7":
                    teacherController.sendComplaint(reader);
                    break;
                case "8":
                    teacherController.sendMessage(reader);
                    break;
                case "9":
                    teacherController.viewMessages();
                    break;
                case "10":
                    teacherController.createRequest(reader);  // Existing method
                    break;
                case "11":
                    System.out.println("Выход из учетной записи преподавателя.");
                    return;
                default:
                    System.out.println("Неверный выбор. Пожалуйста, попробуйте снова.");
            }
        }
    }
    private static void StudentMenu(StudentController studentController, BufferedReader reader) throws IOException {
        while (true) {
            System.out.println("\n=== Меню Студента ===");
            System.out.println("1. Просмотреть профиль");
            System.out.println("2. Просмотреть уведомления");
            System.out.println("3. Просмотреть статьи");
            System.out.println("4. Просмотреть новости");
            System.out.println("5. Просмотреть список курсов");
            System.out.println("6. Просмотреть расписание");
            System.out.println("7. Зарегистрироваться на курс");
            System.out.println("8. Просмотреть мои оценки");
            System.out.println("9. Выйти");
            System.out.print("Выберите опцию: ");
            String choice = reader.readLine();

            switch (choice) {
                case "1":
                    studentController.viewProfile();
                    break;
                case "2":
                    studentController.viewNotifactions();
                    break;
                case "3":
                    studentController.viewPapers();
                    break;
                case "4":
                    studentController.viewNews();
                    break;
                case "5":
                    studentController.checkStudentCourses();
                    break;
                case "6":
                    studentController.viewSchedule();
                    break;
                case "7":
                    studentController.registerForCourse(reader);
                    break;
                case "8":
                    studentController.viewMarks();
                    break;
                case "9":
                    System.out.println("Выход из учетной записи студента.");
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
