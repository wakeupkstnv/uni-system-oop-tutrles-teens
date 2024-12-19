package users.controller;

import users.models.User;
import users.UserType;
import users.Faculty;
import users.TeacherType;
import users.models.Employee;
import users.models.GraduateStudent;
import users.models.Student;
import users.models.Dean;
import users.models.Teacher;
import users.models.Researcher;
import users.models.GraduateStudent;
import users.models.Major;
import users.models.Manager;
import users.models.PhdStudent;
import users.models.PhdStudent;
import users.models.MasterStudent;
import users.models.Admin;
import papers.ResearchPaper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Vector;

import Database.Database;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

// TODO add constructors for all classes

public class UserFactory {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";
    private static final int PASSWORD_LENGTH = 7;

    public UserFactory() {
        super();
    }

    public User createUser(String id, String firstName, String lastName, String email, String login, Date birthDate, UserType userType, Scanner scanner) {
        String generatedPassword = generatePassword();
        String hashedPassword = hashPassword(generatedPassword);  // Хеширование пароля
        Vector<String> notifications = new Vector<>(); // По умолчанию пустой список уведомлений

        System.out.println("Generated Password for user " + login + ": " + generatedPassword); // Для тестирования пароля

        switch (userType) {
            case EMPLOYEE:
                return new Employee(id, firstName, lastName, email, login, birthDate, hashedPassword, notifications);
            case MANAGER:
            	return createManager(id, firstName, lastName, email, login, birthDate, hashedPassword, notifications);
            case STUDENT:
                return createStudent(id, firstName, lastName, email, login, birthDate, hashedPassword, notifications, scanner);
            case DEAN:
                return createDean(id, firstName, lastName, email, login, birthDate, hashedPassword, notifications, scanner);
            case TEACHER:
                return createTeacher(id, firstName, lastName, email, login, birthDate, hashedPassword, notifications, scanner);
            case ADMIN:
                return new Admin(id, firstName, lastName, email, login, birthDate, hashedPassword, notifications);
            case RESEARCHER:
                return createResearcher(id, firstName, lastName, email, login, birthDate, hashedPassword, notifications, scanner);
            case GRADUATED_STUDENT:
                return createGraduatedStudent(id, firstName, lastName, email, login, birthDate, hashedPassword, notifications, scanner);
            case PHD_STUDENT:
                return createPhDStudent(id, firstName, lastName, email, login, birthDate, hashedPassword, notifications, scanner);
            case MASTER_STUDENT:
                return createMasterStudent(id, firstName, lastName, email, login, birthDate, hashedPassword, notifications, scanner);
            default:
                throw new IllegalArgumentException("Invalid user type");
        }
    }

    // Метод для генерации пароля длиной 7 символов
    private String generatePassword() {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(PASSWORD_LENGTH);

        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            password.append(CHARACTERS.charAt(index));
        }

        return password.toString();
    }

    // Метод для хеширования пароля
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    private Student createStudent(String id, String firstName, String lastName, String email, String login, Date birthDate, String hashedPassword, Vector<String> notifications, Scanner scanner) {
        System.out.print("Enter Year of Study: ");
        int year = scanner.nextInt();
        scanner.nextLine();  // Очищаем буфер
        System.out.print("Enter Faculty (SITE, BS, KMA): ");
        String facultyStr = scanner.nextLine().toUpperCase();  // Ввод факультета
        Faculty faculty = Faculty.valueOf(facultyStr); // Преобразуем строку в Faculty

        // Выбираем специальность на основе факультета
        Major major = selectMajorForFaculty(faculty, scanner);

        return new Student(id, firstName, lastName, email, login, birthDate, hashedPassword, notifications, faculty, major, year);
    }

    private Major selectMajorForFaculty(Faculty faculty, Scanner scanner) {
        // Даем пользователю выбор специальности в зависимости от факультета
        switch (faculty) {
            case SITE:
                System.out.println("Choose Major: COMPUTER_SCIENCE, INFORMATION_SYSTEMS");
                break;
            case BS:
                System.out.println("Choose Major: ECONOMICS, MANAGEMENT");
                break;
            case KMA:
                System.out.println("Choose Major: MECHANICAL_ENGINEERING, ELECTRICAL_ENGINEERING");
                break;
            default:
                throw new IllegalArgumentException("Invalid faculty.");
        }
        String majorStr = scanner.nextLine().toUpperCase(); // Ввод специальности
        return Major.valueOf(majorStr); // Преобразуем строку в Major
    }


    private Dean createDean(String id, String firstName, String lastName, String email, String login, Date birthDate, String hashedPassword, Vector<String> notifications, Scanner scanner) {
        Faculty deanFaculty = null;
        boolean validType = false;
        while (!validType) {
        	System.out.print("Enter Faculty (SITE, BS, KMA): ");
        	String typeStr = scanner.nextLine().toUpperCase();
            try {
            	deanFaculty = Faculty.valueOf(typeStr);
                validType = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid teacher type. Please try again.");
            }
        }
        return new Dean(id, firstName, lastName, email, login, birthDate, hashedPassword, notifications, deanFaculty);
    }
    
    private Teacher createTeacher(String id, String firstName, String lastName, String email, String login, Date birthDate, String hashedPassword, Vector<String> notifications, Scanner scanner) {
        
        Faculty teacherFaculty = null;

        TeacherType teacherType = null;
        boolean validType = false;
        while (!validType) {
        	System.out.print("Enter Faculty (SITE, BS, KMA): ");
        	String typeStr = scanner.nextLine().toUpperCase();
            try {
            	teacherFaculty = Faculty.valueOf(typeStr);
                validType = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid teacher type. Please try again.");
            }
            System.out.print("Enter Teacher Type (TUTOR, SENIOR_LECTURE, LECTURE, ASSISTENT): ");
            typeStr = scanner.nextLine().toUpperCase();
            try {
                teacherType = TeacherType.valueOf(typeStr);
                validType = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid teacher type. Please try again.");
            }
        }
        return new Teacher(id, firstName, lastName, email, login, birthDate, hashedPassword, notifications, teacherFaculty, teacherType);
    }
    
    
    private Student createGraduatedStudent(String id, String firstName, String lastName, String email, String login, Date birthDate, String hashedPassword, Vector<String> notifications, Scanner scanner) {
        System.out.print("Enter Graduation Year: ");
        int graduationYear = scanner.nextInt();
        scanner.nextLine();  // Очистить буфер
        return new GraduateStudent(id, firstName, lastName, email, login, birthDate, hashedPassword, notifications, graduationYear);
    }
    private PhdStudent createPhDStudent(String id, String firstName, String lastName, String email, String login, 
            Date birthDate, String hashedPassword, Vector<String> notifications, Scanner scanner) {
// Запрос курса магистратуры
		System.out.print("Enter Master Course: ");
		int masterCourse = scanner.nextInt();
		scanner.nextLine(); // Очистить буфер

// Запрос года зачисления на магистратуру
		System.out.print("Enter Master Enrollment Year: ");
		int masterEnrollmentYear = scanner.nextInt();
		scanner.nextLine(); // Очистить буфер

// Запрос темы диссертации
		System.out.print("Enter Dissertation Title: ");
		String dissertationTitle = scanner.nextLine();

// Запрос научного руководителя
		System.out.print("Enter Supervisor id: ");
        String rid=scanner.nextLine();
		Researcher supervisor=Database.getInstance().findResearcher(rid) ;

// Создание и возврат нового объекта PhdStudent
		return new PhdStudent(id, firstName, lastName, email, login, birthDate, hashedPassword, notifications,
				masterCourse, masterEnrollmentYear, dissertationTitle, supervisor);
	}

    
    private MasterStudent createMasterStudent(String id, String firstName, String lastName, String email, String login,
            Date birthDate, String hashedPassword, Vector<String> notifications,
            Scanner scanner) {
// Запрос курса магистратуры
		System.out.print("Enter Master Course: ");
		int masterCourse = scanner.nextInt();
		scanner.nextLine(); // Очистить буфер после ввода числа

// Запрос года зачисления
		System.out.print("Enter Master Enrollment Year: ");
		int masterEnrollmentYear = scanner.nextInt();
		scanner.nextLine(); // Очистить буфер после ввода числа

// Создание и возврат нового объекта MasterStudent
		return new MasterStudent(id, firstName, lastName, email, login, birthDate, hashedPassword, notifications,
				masterCourse, masterEnrollmentYear);
	}

    
    private Manager createManager(String id, String firstName, String lastName, String email, String login, Date birthDate, String hashedPassword, Vector<String> notifications) {
        return new Manager(id, firstName, lastName, email, login, birthDate, hashedPassword, notifications);
    }
    
    private Admin createAdmin(String id, String firstName, String lastName, String email, String login, Date birthDate, String hashedPassword, Vector<String> notifications) {
        return new Admin(id, firstName, lastName, email, login, birthDate, hashedPassword, notifications);
    }

	private Researcher createResearcher(String id, String firstName, String lastName, String email, String login,
			Date birthDate, String hashedPassword, Vector<String> notifications, Scanner scanner) {
		double hIndex = 0.0;

		// Ввод h-index с обработкой ошибок
		while (true) {
			System.out.print("Enter h-index: ");
			try {
				hIndex = Double.parseDouble(scanner.nextLine().trim());
				break;
			} catch (NumberFormatException e) {
				System.out.println("Invalid input for h-index. Please enter a valid number.");
			}
		}

		Vector<ResearchPaper> researchPapers = new Vector<>();

// Ввод названий статей
		System.out.print("Enter Research Papers (comma-separated titles): ");
		String papersInput = scanner.nextLine();
		String[] papersArray = papersInput.split(",");

		for (String paperTitle : papersArray) {
			paperTitle = paperTitle.trim();
			if (paperTitle.isEmpty())
				continue;

			System.out.print("Would you like to enter custom data for the paper \"" + paperTitle + "\"? (yes/no): ");
			String choice = scanner.nextLine().trim().toLowerCase();

// Создание списка авторов, включая текущего Researcher
			Vector<Researcher> authors = new Vector<>();
			authors.add(new Researcher(id, firstName, lastName, email, login, birthDate, hashedPassword, notifications,
					hIndex, new Vector<>()));

			if (choice.equals("yes")) {
				try {
// Ввод пользовательских данных для статьи
					System.out.print("Enter Journal for \"" + paperTitle + "\": ");
					String journal = scanner.nextLine();

					System.out.print("Enter DOI for \"" + paperTitle + "\": ");
					String doi = scanner.nextLine();

					int pages = 0;
					while (true) {
						System.out.print("Enter Pages for \"" + paperTitle + "\": ");
						try {
							pages = Integer.parseInt(scanner.nextLine().trim());
							break;
						} catch (NumberFormatException e) {
							System.out.println("Invalid input for pages. Please enter a valid number.");
						}
					}

					int citations = 0;
					while (true) {
						System.out.print("Enter Citations for \"" + paperTitle + "\": ");
						try {
							citations = Integer.parseInt(scanner.nextLine().trim());
							break;
						} catch (NumberFormatException e) {
							System.out.println("Invalid input for citations. Please enter a valid number.");
						}
					}

					System.out.print("Enter Publication Date (dd-MM-yyyy) for \"" + paperTitle + "\": ");
					String dateStr = scanner.nextLine();
					Date publicationDate = new SimpleDateFormat("dd-MM-yyyy").parse(dateStr);

// Добавление новой статьи в список
					researchPapers.add(
							new ResearchPaper(paperTitle, journal, doi, pages, citations, publicationDate, authors));
				} catch (NumberFormatException e) {
					System.out.println("Invalid number input. Skipping this paper.");
				} catch (ParseException e) {
					System.out.println("Invalid date format. Skipping this paper.");
				}
			} else {
// Создание статьи по умолчанию
				researchPapers.add(new ResearchPaper(paperTitle, "Unknown Journal", "N/A", 0, 0, new Date(), authors));
			}
		}

// Возврат нового объекта Researcher
		return new Researcher(id, firstName, lastName, email, login, birthDate, hashedPassword, notifications, hIndex,
				researchPapers);
	}

}
