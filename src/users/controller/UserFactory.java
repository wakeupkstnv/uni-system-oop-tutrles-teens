package users.controller;

import database.Database;
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
import users.models.Major;
import users.models.Manager;
import users.models.PhdStudent;
import users.models.MasterStudent;
import users.models.Admin;
import papers.ResearchPaper;

import java.io.BufferedReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

// TODO: Add constructors for all classes

public class UserFactory {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";
    private static final int PASSWORD_LENGTH = 7;

    public UserFactory() {
        super();
    }

    /**
     * Creates a user based on the type.
     *
     * @param id          User identifier
     * @param firstName   First name
     * @param lastName    Last name
     * @param email       Email address
     * @param login       Login name
     * @param birthDate   Date of birth
     * @param userType    Type of user
     * @param reader      BufferedReader for input
     * @return Created User object
     */
    public User createUser(String id, String firstName, String lastName, String email, String login, Date birthDate, UserType userType, BufferedReader reader) {
        String generatedPassword = generatePassword();
        String hashedPassword = hashPassword(generatedPassword);
        Vector<String> notifications = new Vector<>();

        System.out.println("Generated password for user " + login + ": " + generatedPassword);
        switch (userType) {
            case EMPLOYEE:
                return new Employee(id, firstName, lastName, email, login, birthDate, hashedPassword, notifications);
            case MANAGER:
                return createManager(id, firstName, lastName, email, login, birthDate, hashedPassword, notifications);
            case STUDENT:
                return createStudent(id, firstName, lastName, email, login, birthDate, hashedPassword, notifications, reader);
            case DEAN:
                return createDean(id, firstName, lastName, email, login, birthDate, hashedPassword, notifications, reader);
            case TEACHER:
                return createTeacher(id, firstName, lastName, email, login, birthDate, hashedPassword, notifications, reader);
            case ADMIN:
                return new Admin(id, firstName, lastName, email, login, birthDate, hashedPassword, notifications);
            case GRADUATED_STUDENT:
                return createGraduatedStudent(id, firstName, lastName, email, login, birthDate, hashedPassword, notifications, reader);
            case PHD_STUDENT:
                return createPhDStudent(id, firstName, lastName, email, login, birthDate, hashedPassword, notifications, reader);
            case MASTER_STUDENT:
                return createMasterStudent(id, firstName, lastName, email, login, birthDate, hashedPassword, notifications, reader);
            default:
                throw new IllegalArgumentException("Invalid user type");
        }
    }

    /**
     * Generates a random password of specified length.
     *
     * @return Generated password
     */
    private String generatePassword() {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(PASSWORD_LENGTH);

        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            password.append(CHARACTERS.charAt(index));
        }

        return password.toString();
    }

    /**
     * Hashes a password using the SHA-256 algorithm.
     *
     * @param password Password to hash
     * @return Hashed password in hexadecimal format
     */
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

    /**
     * Creates a Student object.
     */
    private Student createStudent(String id, String firstName, String lastName, String email, String login, Date birthDate, String hashedPassword, Vector<String> notifications, BufferedReader reader) {
        try {
            int year = readInt(reader, "Enter year of study: ");
            Faculty faculty = readFaculty(reader);
            Major major = selectMajorForFaculty(faculty, reader);
            return new Student(id, firstName, lastName, email, login, birthDate, hashedPassword, notifications, faculty, major, year);
        } catch (IOException e) {
            System.out.println("Error reading input. User creation canceled.");
            return null;
        }
    }

    /**
     * Creates a Dean object.
     */
    private Dean createDean(String id, String firstName, String lastName, String email, String login, Date birthDate, String hashedPassword, Vector<String> notifications, BufferedReader reader) {
        try {
            Faculty deanFaculty = readFaculty(reader);
            return new Dean(id, firstName, lastName, email, login, birthDate, hashedPassword, notifications, deanFaculty);
        } catch (IOException e) {
            System.out.println("Error reading input. User creation canceled.");
            return null;
        }
    }

    /**
     * Creates a Teacher object.
     */
    private Teacher createTeacher(String id, String firstName, String lastName, String email, String login, Date birthDate, String hashedPassword, Vector<String> notifications, BufferedReader reader) {
        try {
            Faculty teacherFaculty = readFaculty(reader);
            TeacherType teacherType = readTeacherType(reader);
            return new Teacher(id, firstName, lastName, email, login, birthDate, hashedPassword, notifications, teacherFaculty, teacherType);
        } catch (IOException e) {
            System.out.println("Error reading input. User creation canceled.");
            return null;
        }
    }

    /**
     * Creates a GraduateStudent object.
     */
    private GraduateStudent createGraduatedStudent(String id, String firstName, String lastName, String email, String login, Date birthDate, String hashedPassword, Vector<String> notifications, BufferedReader reader) {
        try {
            int graduationYear = readInt(reader, "Enter graduation year: ");
            return new GraduateStudent(id, firstName, lastName, email, login, birthDate, hashedPassword, notifications, graduationYear);
        } catch (IOException e) {
            System.out.println("Error reading input. User creation canceled.");
            return null;
        }
    }

    /**
     * Creates a PhdStudent object.
     */
    private PhdStudent createPhDStudent(String id, String firstName, String lastName, String email, String login,
                                        Date birthDate, String hashedPassword, Vector<String> notifications, BufferedReader reader) {
        try {
            int masterCourse = readInt(reader, "Enter master course: ");
            int masterEnrollmentYear = readInt(reader, "Enter master enrollment year: ");
            System.out.print("Enter dissertation title: ");
            String dissertationTitle = reader.readLine();

            System.out.print("Enter supervisor ID: ");
            String rid = reader.readLine();
            Researcher supervisor = Database.getInstance().findResearcher(rid);
            if (supervisor == null) {
                System.out.println("Supervisor with the given ID not found. User creation canceled.");
                return null;
            }

            return new PhdStudent(id, firstName, lastName, email, login, birthDate, hashedPassword, notifications,
                    masterCourse, masterEnrollmentYear, dissertationTitle, supervisor);
        } catch (IOException e) {
            System.out.println("Error reading input. User creation canceled.");
            return null;
        }
    }

    /**
     * Creates a MasterStudent object.
     */
    private MasterStudent createMasterStudent(String id, String firstName, String lastName, String email, String login,
                                              Date birthDate, String hashedPassword, Vector<String> notifications,
                                              BufferedReader reader) {
        try {
            int masterCourse = readInt(reader, "Enter master course: ");
            int masterEnrollmentYear = readInt(reader, "Enter master enrollment year: ");
            return new MasterStudent(id, firstName, lastName, email, login, birthDate, hashedPassword, notifications,
                    masterCourse, masterEnrollmentYear);
        } catch (IOException e) {
            System.out.println("Error reading input. User creation canceled.");
            return null;
        }
    }

    /**
     * Creates a Manager object.
     */
    private Manager createManager(String id, String firstName, String lastName, String email, String login, Date birthDate, String hashedPassword, Vector<String> notifications) {
        return new Manager(id, firstName, lastName, email, login, birthDate, hashedPassword, notifications);
    }

    /**
     * Creates an Admin object.
     */
    private Admin createAdmin(String id, String firstName, String lastName, String email, String login, Date birthDate, String hashedPassword, Vector<String> notifications) {
        return new Admin(id, firstName, lastName, email, login, birthDate, hashedPassword, notifications);
    }


    /**
     * Reads an integer from BufferedReader with a prompt and error handling.
     *
     * @param reader BufferedReader for input
     * @param prompt Message to display to the user
     * @return Entered integer
     * @throws IOException If an input error occurs
     */
    private int readInt(BufferedReader reader, String prompt) throws IOException {
        int result = -1;
        while (true) {
            System.out.print(prompt);
            String input = reader.readLine();
            try {
                result = Integer.parseInt(input.trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format. Please enter a valid integer.");
            }
        }
        return result;
    }


    /**
     * Reads a double from BufferedReader with a prompt and error handling.
     *
     * @param reader BufferedReader for input
     * @param prompt Message to display to the user
     * @return Entered double
     * @throws IOException If an input error occurs
     */
    private double readDouble(BufferedReader reader, String prompt) throws IOException {
        double result = 0.0;
        while (true) {
            System.out.print(prompt);
            String input = reader.readLine();
            try {
                result = Double.parseDouble(input.trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format. Please enter a valid decimal number.");
            }
        }
        return result;
    }

    /**
     * Reads and returns a Faculty from user input.
     *
     * @param reader BufferedReader for input
     * @return Selected Faculty
     * @throws IOException If an input error occurs
     */
    private Faculty readFaculty(BufferedReader reader) throws IOException {
        Faculty faculty = null;
        boolean valid = false;
        while (!valid) {
            System.out.print("Enter faculty (SITE, BS, KMA): ");
            String facultyStr = reader.readLine().trim().toUpperCase();
            try {
                faculty = Faculty.valueOf(facultyStr);
                valid = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid faculty. Please try again.");
            }
        }
        return faculty;
    }

    /**
     * Reads and returns a TeacherType from user input.
     *
     * @param reader BufferedReader for input
     * @return Selected TeacherType
     * @throws IOException If an input error occurs
     */
    private TeacherType readTeacherType(BufferedReader reader) throws IOException {
        TeacherType teacherType = null;
        boolean valid = false;
        while (!valid) {
            System.out.print("Enter teacher type (TUTOR, SENIOR_LECTURE, LECTURE, ASSISTANT): ");
            String typeStr = reader.readLine().trim().toUpperCase();
            try {
                teacherType = TeacherType.valueOf(typeStr);
                valid = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid teacher type. Please try again.");
            }
        }
        return teacherType;
    }

    /**
     * Selects a major based on the chosen faculty.
     *
     * @param faculty Selected Faculty
     * @param reader  BufferedReader for input
     * @return Selected Major
     * @throws IOException If an input error occurs
     */
    private Major selectMajorForFaculty(Faculty faculty, BufferedReader reader) throws IOException {
        System.out.println("Select major:");
        switch (faculty) {
            case SITE:
                System.out.println("1: COMPUTER_SCIENCE");
                System.out.println("2: INFORMATION_SYSTEMS");
                break;
            case BS:
                System.out.println("1: ECONOMICS");
                System.out.println("2: MANAGEMENT");
                break;
            case KMA:
                System.out.println("1: MECHANICAL_ENGINEERING");
                System.out.println("2: ELECTRICAL_ENGINEERING");
                break;
            default:
                throw new IllegalArgumentException("Invalid faculty.");
        }

        Major major = null;
        boolean valid = false;
        while (!valid) {
            System.out.print("Enter the number of your major: ");
            String input = reader.readLine().trim();
            try {
                int choice = Integer.parseInt(input);
                switch (faculty) {
                    case SITE:
                        if (choice == 1) {
                            major = Major.COMPUTER_SCIENCE;
                        } else if (choice == 2) {
                            major = Major.INFORMATION_SYSTEMS;
                        }
                        break;
                    case BS:
                        if (choice == 1) {
                            major = Major.ECONOMICS;
                        } else if (choice == 2) {
                            major = Major.MANAGEMENT;
                        }
                        break;
                    case KMA:
                        if (choice == 1) {
                            major = Major.MECHANICAL_ENGINEERING;
                        } else if (choice == 2) {
                            major = Major.ELECTRICAL_ENGINEERING;
                        }
                        break;
                }
                if (major != null) {
                    valid = true;
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format. Please enter a valid number.");
            }
        }
        return major;
    }
}
