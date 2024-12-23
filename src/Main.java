import core.CoreSystem;
import core.Language;
import database.Database;
import users.controller.*;
import users.models.*;
import users.view.*;

import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;

public class Main {

    public static final String RESET = "\u001B[0m";
    public static final String BLUE = "\u001B[34m";
    public static final String GREEN = "\u001B[32m";
    public static final String CYAN = "\u001B[36m";
    public static final String YELLOW = "\u001B[33m";
    public static final String RED = "\u001B[31m";
    public static final String PURPLE = "\u001B[35m";

    public static void main(String[] args) throws IOException {
        Database database = Database.getInstance();


        System.out.println(Database.getInstance().getLogs());
        System.out.println(database.getUserPasswords());

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            Admin admin = new Admin("12", "Tamir", "Kustanayev", "t_kustanayev@kbtu.kz",
                    "t_kustanayev", new Date(), "123");
            database.addUserPassword(admin, "123");

            while (true) {
                printMainMenu();
                String choice = reader.readLine();

                switch (choice) {
                    case "1":
                        handleLogin(reader, database);
                        break;
                    case "2":
                        printExitMessage();
                        System.exit(0);
                        break;
                    case "3":
                        changeLanguage(reader);
                        break;
                    default:
                        printInvalidChoiceMessage();
                }
            }
        }
    }

    private static void printMainMenu() {
        Language lang = CoreSystem.getLanguageMode();
        String header = getColoredText("=== WSP Система ===", BLUE);
        String option1 = "1. " + getLocalizedString("Login", "Вход", "Кіру");
        String option2 = "2. " + getLocalizedString("Exit", "Выход", "Шығу");
        String option3 = "3. " + getLocalizedString("Change Language", "Изменить язык", "Тілді өзгерту");
        String prompt = getColoredText(getLocalizedString("Choose an option: ", "Выберите опцию: ", "Опцияны таңдаңыз: "), CYAN);

        System.out.println("\n" + header);
        System.out.println(option1);
        System.out.println(option2);
        System.out.println(option3);
        System.out.print(prompt);
    }

    private static void handleLogin(BufferedReader reader, Database database) throws IOException {
        Language lang = CoreSystem.getLanguageMode();
        String promptLogin = "";
        String promptPassword = "";

        if (lang == Language.RUS) {
            promptLogin = "Введите логин: ";
            promptPassword = "Введите пароль: ";
        } else if (lang == Language.ENG) {
            promptLogin = "Enter login: ";
            promptPassword = "Enter password: ";
        } else if (lang == Language.KZ) {
            promptLogin = "Логин енгізіңіз: ";
            promptPassword = "Құпия сөз енгізіңіз: ";
        }

        System.out.print(getColoredText(promptLogin, YELLOW));
        String login = reader.readLine();
        System.out.print(getColoredText(promptPassword, YELLOW));
        String password = reader.readLine();

        User u = database.getUsers()
                .stream()
                .filter(n -> n.getLogin().equals(login))
                .findFirst()
                .orElse(null);

        if (u == null || !database.getUserPasswords().containsKey(u.getLogin())) {
            printLoginFailureMessage();
            return;
        }

        String storedPassword = database.getUserPasswords().get(u.getLogin());
        if (storedPassword.equals(password)) {
            String welcomeMessage = "";
            if (lang == Language.RUS) {
                welcomeMessage = "Успешный вход! Добро пожаловать, " + u.getFirstName() + "!";
            } else if (lang == Language.ENG) {
                welcomeMessage = "Login successful! Welcome, " + u.getFirstName() + "!";
            } else if (lang == Language.KZ) {
                welcomeMessage = "Сәтті кіру! Қош келдіңіз, " + u.getFirstName() + "!";
            }
            System.out.println(getColoredText(welcomeMessage, GREEN));
            navigateUserRole(u, reader, database);
        } else {
            printLoginFailureMessage();
        }
    }

    private static void printLoginFailureMessage() {
        Language lang = CoreSystem.getLanguageMode();
        String message = "";
        if (lang == Language.RUS) {
            message = "Неверный логин или пароль. Пожалуйста, попробуйте снова.";
        } else if (lang == Language.ENG) {
            message = "Invalid login or password. Please try again.";
        } else if (lang == Language.KZ) {
            message = "Қате логин немесе пароль. Қайтадан көріңіз.";
        }
        System.out.println(getColoredText(message, RED));
    }

    private static void printExitMessage() {
        Language lang = CoreSystem.getLanguageMode();
        String message = "";
        if (lang == Language.RUS) {
            message = "Выход из системы. До свидания!";
        } else if (lang == Language.ENG) {
            message = "Exiting the system. Goodbye!";
        } else if (lang == Language.KZ) {
            message = "Жүйеден шығу. Сау болыңыз!";
        }
        System.out.println(getColoredText(message, PURPLE));
    }

    private static void printInvalidChoiceMessage() {
        Language lang = CoreSystem.getLanguageMode();
        String message = "";
        if (lang == Language.RUS) {
            message = "Неверный выбор. Пожалуйста, попробуйте снова.";
        } else if (lang == Language.ENG) {
            message = "Invalid choice. Please try again.";
        } else if (lang == Language.KZ) {
            message = "Қате таңдау. Қайтадан көріңіз.";
        }
        System.out.println(getColoredText(message, RED));
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
        } else if (user instanceof Teacher) {
            TeacherView teacherView = new TeacherView();
            TeacherController<Teacher, TeacherView> teacherController = new TeacherController<>((Teacher) user, teacherView);
            teacherMenu(teacherController, reader);
        } else if (user instanceof Student) {
            StudentView studentView = new StudentView();
            StudentController<Student, StudentView> studentController = new StudentController<>((Student) user, studentView);
            studentMenu(studentController, reader);
        } else {
            UserView userView = new UserView();
            UserController<User, UserView> userController = new UserController<>(user, userView);
            userMenu(userController, reader);
        }
    }

    private static void adminMenu(AdminController<Admin, AdminView> adminController, BufferedReader reader) throws IOException {
        while (true) {
            printAdminMenu();
            String choice = reader.readLine();
            AdminView adminView = new AdminView();
            switch (choice) {
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
                    changeLanguage(reader);
                    break;
                case "7":
                    printAdminLogoutMessage();
                    return;
                default:
                    printInvalidChoiceMessage();
            }
        }
    }

    private static void printAdminMenu() {
        Language lag = CoreSystem.getLanguageMode();
        String header = getColoredText("\n=== Меню Администратора ===", BLUE);
        String option1 = "1. " + getLocalizedString("View Profile", "Просмотреть профиль", "Профильді қарау");
        String option2 = "2. " + getLocalizedString("View Notifications", "Просмотреть уведомления", "Хабарландыруларды қарау");
        String option3 = "3. " + getLocalizedString("View Papers", "Просмотреть статьи", "Мақалаларды қарау");
        String option4 = "4. " + getLocalizedString("Manage Users", "Управление пользователями", "Пайдаланушыларды басқару");
        String option5 = "5. " + getLocalizedString("Manage Bans", "Управление блокировками", "Блокировкаларды басқару");
        String option6 = "6. " + getLocalizedString("Change Language", "Изменить язык", "Тілді өзгерту");
        String option7 = "7. " + getLocalizedString("Logout", "Выход", "Шығу");
        String prompt = getColoredText(getLocalizedString("Choose an option: ", "Выберите опцию: ", "Опцияны таңдаңыз: "), CYAN);

        System.out.println(header);
        System.out.println(option1);
        System.out.println(option2);
        System.out.println(option3);
        System.out.println(option4);
        System.out.println(option5);
        System.out.println(option6);
        System.out.println(option7);
        System.out.print(prompt);
    }

    private static void printAdminLogoutMessage() {
        Language lang = CoreSystem.getLanguageMode();
        String message = "";
        if (lang == Language.RUS) {
            message = "Выход из учетной записи администратора.";
        } else if (lang == Language.ENG) {
            message = "Admin account logged out.";
        } else if (lang == Language.KZ) {
            message = "Әкімші тіркелгісінен шығу.";
        }
        System.out.println(getColoredText(message, PURPLE));
    }

    private static void managerMenu(ManagerController<Manager, ManagerView> managerController, BufferedReader reader) throws IOException {
        while (true){
            printManagerMenu();
            String choice = reader.readLine();
            ManagerView managerView = new ManagerView();
            switch (choice){
                case "1":
                    managerController.viewProfile();
                    break;
                case "2":
                    managerController.viewNotifactions();
                    break;
                case "3":
                    managerController.viewPapers();
                    break;
                case "4":
                    managerView.registrationMenu(managerController, reader);
                    break;
                case "5":
                    managerView.newsMenu(managerController, reader);
                    break;
                case "6":
                    managerView.teacherMenu(managerController, reader);
                    break;
                case "7":
                    managerView.showCourseList();
                    break;
                case "8":
                    managerView.showTeacherList();
                    break;
                case "9":
                    managerView.viewAllRequest();
                    break;
                case "10":
                    printManagerLogoutMessage();
                    return;
                default:
                    printInvalidChoiceMessage();
            }
        }
    }

    private static void printManagerMenu() {
        Language lang = CoreSystem.getLanguageMode();
        String header = getColoredText("\n=== Меню Менеджера ===", BLUE);
        String option1 = "1. " + getLocalizedString("View Profile", "Просмотреть профиль", "Профильді қарау");
        String option2 = "2. " + getLocalizedString("View Notifications", "Просмотреть уведомления", "Хабарландыруларды қарау");
        String option3 = "3. " + getLocalizedString("View Papers", "Просмотреть статьи", "Мақалаларды қарау");
        String option4 = "4. " + getLocalizedString("Manage Registrations", "Управление регистрацией", "Тіркеуді басқару");
        String option5 = "5. " + getLocalizedString("Manage News", "Управление новостями", "Жаңалықтарды басқару");
        String option6 = "6. " + getLocalizedString("Manage Teachers", "Управление учительским составом", "Мұғалімдерді басқару");
        String option7 = "7. " + getLocalizedString("View Courses", "Просмотреть курсы", "Курстарды қарау");
        String option8 = "8. " + getLocalizedString("View Teachers", "Просмотреть учителей", "Мұғалімдерді қарау");
        String option9 = "9. " + getLocalizedString("View Requests", "Просмотреть запросы", "Сұраныстарды қарау");
        String option10 = "10. " + getLocalizedString("Logout", "Выход", "Шығу");
        String prompt = getColoredText(getLocalizedString("Choose an option: ", "Выберите опцию: ", "Опцияны таңдаңыз: "), CYAN);

        System.out.println(header);
        System.out.println(option1);
        System.out.println(option2);
        System.out.println(option3);
        System.out.println(option4);
        System.out.println(option5);
        System.out.println(option6);
        System.out.println(option7);
        System.out.println(option8);
        System.out.println(option9);
        System.out.println(option10);
        System.out.print(prompt);
    }

    private static void printManagerLogoutMessage() {
        Language lang = CoreSystem.getLanguageMode();
        String message = "";
        if (lang == Language.RUS) {
            message = "Выход из учетной записи менеджера.";
        } else if (lang == Language.ENG) {
            message = "Manager account logged out.";
        } else if (lang == Language.KZ) {
            message = "Менеджер тіркелгісінен шығу.";
        }
        System.out.println(getColoredText(message, PURPLE));
    }

    private static void teacherMenu(TeacherController<Teacher, TeacherView> teacherController, BufferedReader reader) throws IOException {
        while (true) {
            printTeacherMenu();
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
                    teacherController.createRequest(reader);
                    break;
                case "11":
                    changeLanguage(reader);
                    break;
                case "12":
                    printTeacherLogoutMessage();
                    return;
                default:
                    printInvalidChoiceMessage();
            }
        }
    }

    private static void printTeacherMenu() {
        Language lang = CoreSystem.getLanguageMode();
        String header = getColoredText("\n=== Меню Преподавателя ===", BLUE);
        String option1 = "1. " + getLocalizedString("View Profile", "Просмотреть профиль", "Профильді қарау");
        String option2 = "2. " + getLocalizedString("View Notifications", "Просмотреть уведомления", "Хабарландыруларды қарау");
        String option3 = "3. " + getLocalizedString("View Papers", "Просмотреть статьи", "Мақалаларды қарау");
        String option4 = "4. " + getLocalizedString("View News", "Просмотреть новости", "Жаңалықтарды қарау");
        String option5 = "5. " + getLocalizedString("View Student List", "Просмотреть список студентов", "Студенттер тізімін қарау");
        String option6 = "6. " + getLocalizedString("Assign Grades", "Назначить оценки", "Бағаларды тағайындау");
        String option7 = "7. " + getLocalizedString("Send Complaint to Dean", "Отправить жалобу декану", "Деканға шағым жіберу");
        String option8 = "8. " + getLocalizedString("Send Message to Staff", "Отправить сообщение сотруднику", "Қызметкерге хабарлама жіберу");
        String option9 = "9. " + getLocalizedString("View Messages", "Просмотреть сообщения", "Хабарламаларды қарау");
        String option10 = "10. " + getLocalizedString("Create Request", "Создать запрос", "Сұраныс жасау");
        String option11 = "11. " + getLocalizedString("Change Language", "Изменить язык", "Тілді өзгерту");
        String option12 = "12. " + getLocalizedString("Logout", "Выход", "Шығу");
        String prompt = getColoredText(getLocalizedString("Choose an option: ", "Выберите опцию: ", "Опцияны таңдаңыз: "), CYAN);

        System.out.println(header);
        System.out.println(option1);
        System.out.println(option2);
        System.out.println(option3);
        System.out.println(option4);
        System.out.println(option5);
        System.out.println(option6);
        System.out.println(option7);
        System.out.println(option8);
        System.out.println(option9);
        System.out.println(option10);
        System.out.println(option11);
        System.out.println(option12);
        System.out.print(prompt);
    }

    private static void printTeacherLogoutMessage() {
        Language lang = CoreSystem.getLanguageMode();
        String message = "";
        if (lang == Language.RUS) {
            message = "Выход из учетной записи преподавателя.";
        } else if (lang == Language.ENG) {
            message = "Teacher account logged out.";
        } else if (lang == Language.KZ) {
            message = "Мұғалім тіркелгісінен шығу.";
        }
        System.out.println(getColoredText(message, PURPLE));
    }

    private static void studentMenu(StudentController<Student, StudentView> studentController, BufferedReader reader) throws IOException {
        while (true) {
            printStudentMenu();
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
                    changeLanguage(reader);
                    break;
                case "10":
                    printStudentLogoutMessage();
                    return;
                default:
                    printInvalidChoiceMessage();
            }
        }
    }

    private static void printStudentMenu() {
        Language lang = CoreSystem.getLanguageMode();
        String header = getColoredText("\n=== Меню Студента ===", BLUE);
        String option1 = "1. " + getLocalizedString("View Profile", "Просмотреть профиль", "Профильді қарау");
        String option2 = "2. " + getLocalizedString("View Notifications", "Просмотреть уведомления", "Хабарландыруларды қарау");
        String option3 = "3. " + getLocalizedString("View Papers", "Просмотреть статьи", "Мақалаларды қарау");
        String option4 = "4. " + getLocalizedString("View News", "Просмотреть новости", "Жаңалықтарды қарау");
        String option5 = "5. " + getLocalizedString("View Course List", "Просмотреть список курсов", "Курстар тізімін қарау");
        String option6 = "6. " + getLocalizedString("View Schedule", "Просмотреть расписание", "Кестені қарау");
        String option7 = "7. " + getLocalizedString("Register for Course", "Зарегистрироваться на курс", "Курсты тіркелу");
        String option8 = "8. " + getLocalizedString("View My Grades", "Просмотреть мои оценки", "Менің бағаларымды қарау");
        String option9 = "9. " + getLocalizedString("Change Language", "Изменить язык", "Тілді өзгерту");
        String option10 = "10. " + getLocalizedString("Logout", "Выход", "Шығу");
        String prompt = getColoredText(getLocalizedString("Choose an option: ", "Выберите опцию: ", "Опцияны таңдаңыз: "), CYAN);

        System.out.println(header);
        System.out.println(option1);
        System.out.println(option2);
        System.out.println(option3);
        System.out.println(option4);
        System.out.println(option5);
        System.out.println(option6);
        System.out.println(option7);
        System.out.println(option8);
        System.out.println(option9);
        System.out.println(option10);
        System.out.print(prompt);
    }

    private static void printStudentLogoutMessage() {
        Language lang = CoreSystem.getLanguageMode();
        String message = "";
        if (lang == Language.RUS) {
            message = "Выход из учетной записи студента.";
        } else if (lang == Language.ENG) {
            message = "Student account logged out.";
        } else if (lang == Language.KZ) {
            message = "Студент тіркелгісінен шығу.";
        }
        System.out.println(getColoredText(message, PURPLE));
    }

    // Метод для отображения меню пользователя
    private static void userMenu(UserController<User, UserView> userController, BufferedReader reader) throws IOException {
        while (true) {
            printUserMenu();
            String choice = reader.readLine();

            switch (choice) {
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
                    changeLanguage(reader);
                    break;
                case "6":
                    printUserLogoutMessage();
                    return;
                default:
                    printInvalidChoiceMessage();
            }
        }
    }

    // Метод для печати меню пользователя
    private static void printUserMenu() {
        Language lang = CoreSystem.getLanguageMode();
        String header = getColoredText("\n=== Меню Пользователя ===", BLUE);
        String option1 = "1. " + getLocalizedString("View Profile", "Просмотреть профиль", "Профильді қарау");
        String option2 = "2. " + getLocalizedString("View Notifications", "Просмотреть уведомления", "Хабарландыруларды қарау");
        String option3 = "3. " + getLocalizedString("View Papers", "Просмотреть статьи", "Мақалаларды қарау");
        String option4 = "4. " + getLocalizedString("View News", "Просмотреть новости", "Жаңалықтарды қарау");
        String option5 = "5. " + getLocalizedString("Change Language", "Изменить язык", "Тілді өзгерту");
        String option6 = "6. " + getLocalizedString("Logout", "Выход", "Шығу");
        String prompt = getColoredText(getLocalizedString("Choose an option: ", "Выберите опцию: ", "Опцияны таңдаңыз: "), CYAN);

        System.out.println(header);
        System.out.println(option1);
        System.out.println(option2);
        System.out.println(option3);
        System.out.println(option4);
        System.out.println(option5);
        System.out.println(option6);
        System.out.print(prompt);
    }

    private static void printUserLogoutMessage() {
        Language lang = CoreSystem.getLanguageMode();
        String message = "";
        if (lang == Language.RUS) {
            message = "Выход из учетной записи пользователя.";
        } else if (lang == Language.ENG) {
            message = "User account logged out.";
        } else if (lang == Language.KZ) {
            message = "Пайдаланушы тіркелгісінен шығу.";
        }
        System.out.println(getColoredText(message, PURPLE));
    }

    private static void changeLanguage(BufferedReader reader) throws IOException {
        Language currentLang = CoreSystem.getLanguageMode();
        String header = getColoredText(getLocalizedString("=== Change Language ===", "=== Изменить язык ===", "=== Тілді өзгерту ==="), BLUE);
        String option1 = "1. Русский";
        String option2 = "2. English";
        String option3 = "3. Қазақша";
        String prompt = getColoredText(getLocalizedString("Choose an option: ", "Выберите опцию: ", "Опцияны таңдаңыз: "), YELLOW);

        System.out.println("\n" + header);
        System.out.println(option1);
        System.out.println(option2);
        System.out.println(option3);
        System.out.print(prompt);

        String choice = reader.readLine();
        switch (choice) {
            case "1":
                CoreSystem.setLanguageMode(Language.RUS);
                if (currentLang == Language.RUS) {
                    System.out.println(getColoredText("Язык уже установлен на Русский.", GREEN));
                } else if (currentLang == Language.ENG) {
                    System.out.println(getColoredText("Language changed to Русский.", GREEN));
                } else if (currentLang == Language.KZ) {
                    System.out.println(getColoredText("Тіл өзгертілді Русский.", GREEN));
                }
                break;
            case "2":
                CoreSystem.setLanguageMode(Language.ENG);
                if (currentLang == Language.RUS) {
                    System.out.println(getColoredText("Язык изменен на English.", GREEN));
                } else if (currentLang == Language.ENG) {
                    System.out.println(getColoredText("Language is already set to English.", GREEN));
                } else if (currentLang == Language.KZ) {
                    System.out.println(getColoredText("Тіл өзгертілді English.", GREEN));
                }
                break;
            case "3":
                CoreSystem.setLanguageMode(Language.KZ);
                if (currentLang == Language.RUS) {
                    System.out.println(getColoredText("Язык изменен на Қазақша.", GREEN));
                } else if (currentLang == Language.ENG) {
                    System.out.println(getColoredText("Language changed to Қазақша.", GREEN));
                } else if (currentLang == Language.KZ) {
                    System.out.println(getColoredText("Тіл уже установлен на Қазақша.", GREEN));
                }
                break;
            default:
                String errorMsg = "";
                if (currentLang == Language.RUS) {
                    errorMsg = "Неверный выбор. Пожалуйста, попробуйте снова.";
                } else if (currentLang == Language.ENG) {
                    errorMsg = "Invalid choice. Please try again.";
                } else if (currentLang == Language.KZ) {
                    errorMsg = "Қате таңдау. Қайтадан көріңіз.";
                }
                System.out.println(getColoredText(errorMsg, RED));
        }
    }

    private static String getLocalizedString(String eng, String rus, String kaz) {
        Language lang = CoreSystem.getLanguageMode();
        switch (lang) {
            case ENG:
                return eng;
            case RUS:
                return rus;
            case KZ:
                return kaz;
            default:
                return eng;
        }
    }

    private static String getColoredText(String text, String color) {
        return color + text + RESET;
    }
}
