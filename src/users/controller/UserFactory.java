package users.controller;

import database.Database;
import users.models.*;
import users.UserType;
import users.Faculty;
import users.TeacherType;
import papers.ResearchPaper;

import java.io.BufferedReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Vector;


/**
 * Factory class for creating different types of users.
 */
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
     * @return Created User object or null if creation fails
     */
    public static User createUser(String id, String firstName, String lastName, String email, String login, Date birthDate, UserType userType, BufferedReader reader) {
        String generatedPassword = generatePassword();
        String hashedPassword = hashPassword(generatedPassword);
        // TODO: Implement your own hash function if needed

        System.out.println("Generated password for user " + login + ": " + generatedPassword);
        switch (userType) {
            case EMPLOYEE:
                Employee employee = new Employee(id, firstName, lastName, email, login, birthDate, hashedPassword);
                Database.getInstance().addUserPassword(employee, hashedPassword);
                return employee;

            case MANAGER:
                Manager manager = createManager(id, firstName, lastName, email, login, birthDate, hashedPassword);
                Database.getInstance().addUserPassword(manager, hashedPassword);
                return manager;

            case STUDENT:
                Student student = createStudent(id, firstName, lastName, email, login, birthDate, hashedPassword, reader);
                Database.getInstance().addUserPassword(student, hashedPassword);
                return student;

            case DEAN:
                Dean dean = createDean(id, firstName, lastName, email, login, birthDate, hashedPassword, reader);
                Database.getInstance().addUserPassword(dean, hashedPassword);
                return dean;

            case TEACHER:
                Teacher teacher = createTeacher(id, firstName, lastName, email, login, birthDate, hashedPassword, reader);
                Database.getInstance().addUserPassword(teacher, hashedPassword);
                if (teacher != null) {
                    Database.getInstance().addTeacher(teacher);
                }
                return teacher;

            case ADMIN:
                Admin admin = new Admin(id, firstName, lastName, email, login, birthDate, hashedPassword);
                // TODO: Save admin in DB if required
                Database.getInstance().addUserPassword(admin, hashedPassword);
                return admin;

            case GRADUATED_STUDENT:
                GraduateStudent graduateStudent = createGraduatedStudent(id, firstName, lastName, email, login, birthDate, hashedPassword, reader);
                Database.getInstance().addUserPassword(graduateStudent, hashedPassword);
                if (graduateStudent != null) {
                    Database.getInstance().addStudent(graduateStudent);
                }
                return graduateStudent;

            case PHD_STUDENT:
                PhdStudent phdStudent = createPhDStudent(id, firstName, lastName, email, login, birthDate, hashedPassword, reader);
                Database.getInstance().addUserPassword(phdStudent, hashedPassword);
                if (phdStudent != null) {
                    Database.getInstance().addStudent(phdStudent);
                }
                return phdStudent;

            case MASTER_STUDENT:
                MasterStudent masterStudent = createMasterStudent(id, firstName, lastName, email, login, birthDate, hashedPassword, reader);
                Database.getInstance().addUserPassword(masterStudent, hashedPassword);
                if (masterStudent != null) {
                    Database.getInstance().addStudent(masterStudent);
                }
                return masterStudent;

            default:
                throw new IllegalArgumentException("Invalid user type");
        }
    }

    /**
     * Generates a random password of specified length.
     *
     * @return Generated password
     */
    private static String generatePassword() {
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
    private static String hashPassword(String password) {
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
    private static Student createStudent(String id, String firstName, String lastName, String email, String login, Date birthDate, String hashedPassword, BufferedReader reader) {
        try {
            int year = readInt(reader, "Enter year of study: ");
            Faculty faculty = readFaculty(reader);
            Major major = selectMajorForFaculty(faculty, reader);
            return new Student(id, firstName, lastName, email, login, birthDate, hashedPassword, faculty, major, year);
        } catch (IOException e) {
            System.out.println("Error reading input. User creation canceled.");
            return null;
        }
    }

    /**
     * Creates a Dean object.
     */
    private static Dean createDean(String id, String firstName, String lastName, String email, String login, Date birthDate, String hashedPassword, BufferedReader reader) {
        try {
            Faculty deanFaculty = readFaculty(reader);
            return new Dean(id, firstName, lastName, email, login, birthDate, hashedPassword, deanFaculty);
        } catch (IOException e) {
            System.out.println("Error reading input. User creation canceled.");
            return null;
        }
    }

    /**
     * Creates a Teacher object.
     */
    private static Teacher createTeacher(String id, String firstName, String lastName, String email, String login, Date birthDate, String hashedPassword, BufferedReader reader) {
        try {
            Faculty teacherFaculty = readFaculty(reader);
            TeacherType teacherType = readTeacherType(reader);
            return new Teacher(id, firstName, lastName, email, login, birthDate, hashedPassword, teacherFaculty, teacherType);
        } catch (IOException e) {
            System.out.println("Error reading input. User creation canceled.");
            return null;
        }
    }

    /**
     * Creates a GraduateStudent object.
     */
    private static GraduateStudent createGraduatedStudent(String id, String firstName, String lastName, String email, String login, Date birthDate, String hashedPassword, BufferedReader reader) {
        try {
            int graduationYear = readInt(reader, "Enter graduation year: ");
            return new GraduateStudent(id, firstName, lastName, email, login, birthDate, hashedPassword, graduationYear);
        } catch (IOException e) {
            System.out.println("Error reading input. User creation canceled.");
            return null;
        }
    }

    /**
     * Creates a PhdStudent object.
     */
    private static PhdStudent createPhDStudent(String id, String firstName, String lastName, String email, String login,
                                               Date birthDate, String hashedPassword, BufferedReader reader) {
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

            return new PhdStudent(id, firstName, lastName, email, login, birthDate, hashedPassword,
                    masterCourse, masterEnrollmentYear, dissertationTitle, supervisor);
        } catch (IOException e) {
            System.out.println("Error reading input. User creation canceled.");
            return null;
        }
    }

    /**
     * Creates a MasterStudent object.
     */
    private static MasterStudent createMasterStudent(String id, String firstName, String lastName, String email, String login,
                                                     Date birthDate, String hashedPassword,
                                                     BufferedReader reader) {
        try {
            int masterCourse = readInt(reader, "Enter master course: ");
            int masterEnrollmentYear = readInt(reader, "Enter master enrollment year: ");
            return new MasterStudent(id, firstName, lastName, email, login, birthDate, hashedPassword,
                    masterCourse, masterEnrollmentYear);
        } catch (IOException e) {
            System.out.println("Error reading input. User creation canceled.");
            return null;
        }
    }

    /**
     * Creates a Manager object.
     */
    private static Manager createManager(String id, String firstName, String lastName, String email, String login, Date birthDate, String hashedPassword) {
        return new Manager(id, firstName, lastName, email, login, birthDate, hashedPassword);
    }

    /**
     * Reads an integer from BufferedReader with a prompt and error handling.
     *
     * @param reader BufferedReader for input
     * @param prompt Message to display to the user
     * @return Entered integer
     * @throws IOException If an input error occurs
     */
    private static int readInt(BufferedReader reader, String prompt) throws IOException {
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
    private static Faculty readFaculty(BufferedReader reader) throws IOException {
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
    private static TeacherType readTeacherType(BufferedReader reader) throws IOException {
        TeacherType teacherType = null;
        boolean valid = false;
        while (!valid) {
            System.out.print("Enter teacher type (TUTOR, SENIOR_LECTURER, LECTURER, ASSISTANT): ");
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
    private static Major selectMajorForFaculty(Faculty faculty, BufferedReader reader) throws IOException {
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
