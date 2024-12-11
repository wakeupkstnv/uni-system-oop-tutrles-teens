package Users.Controller;

import Users.Models.User;
import Users.UserType;
import Users.TeacherType;
import Users.Models.Employee;
import Users.Models.Student;
import Users.Models.Dean;
import Users.Models.Teacher;
import Users.Models.Admin;
import Users.Models.Researcher;
import Users.Models.ResearchPaper;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Vector;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class UserFactory
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public UserFactory(){
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
    public User createUser(String id, String firstName, String lastName, Date birthDate, UserType userType, Scanner scanner) {
        switch (userType) {
            case EMPLOYEE:
                return new Employee(id, firstName, lastName, birthDate);
            case STUDENT:
                return createStudent(id, firstName, lastName, birthDate, scanner);
            case DEAN:
                return createDean(id, firstName, lastName, birthDate, scanner);
            case TEACHER:
                return createTeacher(id, firstName, lastName, birthDate, scanner);
            case ADMIN:
                return createAdmin(id, firstName, lastName, birthDate, scanner);
            case RESEARCHER:
                return createResearcher(id, firstName, lastName, birthDate, scanner);
            case GRADUATED_STUDENT:
                return createGraduatedStudent(id, firstName, lastName, birthDate, scanner);
            case PHD_STUDENT:
                return createPhDStudent(id, firstName, lastName, birthDate, scanner);
            case MASTER_STUDENT:
                return createMasterStudent(id, firstName, lastName, birthDate, scanner);
            default:
                throw new IllegalArgumentException("Invalid user type");
        }
    }

    private Student createStudent(String id, String firstName, String lastName, Date birthDate, Scanner scanner) {
        System.out.print("Enter Year of Study: ");
        int year = scanner.nextInt();
        scanner.nextLine();  
        System.out.print("Enter Faculty: ");
        String faculty = scanner.nextLine();
        return new Student(id, firstName, lastName, birthDate, year, faculty);
    }

    private Dean createDean(String id, String firstName, String lastName, Date birthDate, Scanner scanner) {
        System.out.print("Enter Faculty: ");
        String deanFaculty = scanner.nextLine();
        return new Dean(id, firstName, lastName, birthDate, deanFaculty);
    }

    private Teacher createTeacher(String id, String firstName, String lastName, Date birthDate, Scanner scanner) {
        System.out.print("Enter Faculty: ");
        String teacherFaculty = scanner.nextLine();

        TeacherType teacherType = null;
        boolean validType = false;
        while (!validType) {
            System.out.print("Enter Teacher Type (TUTOR, SENIOR_LECTURE, LECTURE, ASSISTENT): ");
            String typeStr = scanner.nextLine().toUpperCase();
            try {
                teacherType = TeacherType.valueOf(typeStr);
                validType = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid teacher type. Please try again.");
            }
        }
        return new Teacher(id, firstName, lastName, birthDate, teacherFaculty, teacherType);
    }

    private Admin createAdmin(String id, String firstName, String lastName, Date birthDate, Scanner scanner) {
        System.out.print("Enter Department: ");
        String department = scanner.nextLine();
        return new Admin(id, firstName, lastName, birthDate, department);
    }

    private Researcher createResearcher(String id, String firstName, String lastName, Date birthDate, Scanner scanner) {
        System.out.print("Enter h-index: ");
        double hIndex = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter Research Papers (comma-separated): ");
        String papersInput = scanner.nextLine();
        String[] papersArray = papersInput.split(",");
        Vector<ResearchPaper> researchPapers = new Vector<>();

        for (String paperTitle : papersArray) {
            System.out.print("Would you like to enter custom data for the paper \"" + paperTitle.trim() + "\"? (yes/no): ");
            String choice = scanner.nextLine().trim().toLowerCase();

            Vector<Researcher> authors = new Vector<>();
            authors.add(new Researcher(id, firstName, lastName, birthDate, hIndex, new Vector<>()));

            if (choice.equals("yes")) {
                System.out.print("Enter Journal for \"" + paperTitle.trim() + "\": ");
                String journal = scanner.nextLine();

                System.out.print("Enter DOI for \"" + paperTitle.trim() + "\": ");
                String doi = scanner.nextLine();

                System.out.print("Enter Pages for \"" + paperTitle.trim() + "\": ");
                int pages = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Enter Citations for \"" + paperTitle.trim() + "\": ");
                int citations = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Enter Publication Date for \"" + paperTitle.trim() + " (yyyy-MM-dd): ");
                String publishedStr = scanner.nextLine();
                Date published = null;
                try {
                    published = new SimpleDateFormat("yyyy-MM-dd").parse(publishedStr);
                } catch (ParseException e) {
                    System.out.println("Invalid date format for publication. Please use yyyy-MM-dd.");
                    continue;
                }

                researchPapers.add(new ResearchPaper(paperTitle.trim(), authors, journal, doi, pages, citations, published));
            } else {
                System.out.println("Using default values for the paper \"" + paperTitle.trim() + "\".");
                researchPapers.add(new ResearchPaper(
                        paperTitle.trim(),
                        authors,
                        "Default Journal",
                        "Default DOI",
                        10,
                        0,
                        "2020-01-01"
                ));
            }
        }
        return new Researcher(id, firstName, lastName, birthDate, hIndex, researchPapers);
    }

    private GraduatedStudent createGraduatedStudent(String id, String firstName, String lastName, Date birthDate, Scanner scanner) {
        Researcher researcher = createResearcherProfile(scanner);
        return new GraduatedStudent(id, firstName, lastName, birthDate, researcher);
    }

    private PhDStudent createPhDStudent(String id, String firstName, String lastName, Date birthDate, Scanner scanner) {
        Researcher researcher = createResearcherProfile(scanner);
        return new PhDStudent(id, firstName, lastName, birthDate, researcher);
    }

    private MasterStudent createMasterStudent(String id, String firstName, String lastName, Date birthDate, Scanner scanner) {
        Researcher researcher = createResearcherProfile(scanner);
        return new MasterStudent(id, firstName, lastName, birthDate, researcher);
    }

    private Researcher createResearcherProfile(Scanner scanner) {
        System.out.print("Do you want to create a Researcher profile? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();

        if (response.equals("no")) {
            return null;
        }

        System.out.print("Do you want to use default values for the Researcher profile? (yes/no): ");
        String defaultResponse = scanner.nextLine().trim().toLowerCase();

        if (defaultResponse.equals("yes")) {
            Vector<ResearchPaper> defaultPapers = new Vector<>();
            defaultPapers.add(new ResearchPaper("Default Paper", null, "Default Journal", "Default DOI", 0, 0, "2020-01-01"));
            return new Researcher("Default Name", "Default Surname", "Default Institution", new Date(), 10.0, defaultPapers);
        }

        System.out.println("Creating Researcher profile:");
        System.out.print("Enter h-index for Research Profile: ");
        double hIndex = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter Research Papers (comma-separated): ");
        String papersInput = scanner.nextLine();
        String[] papersArray = papersInput.split(",");
        Vector<ResearchPaper> researchPapers = new Vector<>();

        for (String paperTitle : papersArray) {
            System.out.print("Would you like to enter custom data for the paper \"" + paperTitle.trim() + "\"? (yes/no): ");
            String choice = scanner.nextLine().trim().toLowerCase();

            Vector<Researcher> authors = new Vector<>();
            authors.add(new Researcher("", "", "", new Date(), hIndex, new Vector<>()));

            if (choice.equals("yes")) {
                System.out.print("Enter Journal for \"" + paperTitle.trim() + "\": ");
                String journal = scanner.nextLine();

                System.out.print("Enter DOI for \"" + paperTitle.trim() + "\": ");
                String doi = scanner.nextLine();

                System.out.print("Enter Pages for \"" + paperTitle.trim() + "\": ");
                int pages = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Enter Citations for \"" + paperTitle.trim() + "\": ");
                int citations = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Enter Publication Date for \"" + paperTitle.trim() + " (yyyy-MM-dd): ");
                String publishedStr = scanner.nextLine();
                Date published = null;
                try {
                    published = new SimpleDateFormat("yyyy-MM-dd").parse(publishedStr);
                } catch (ParseException e) {
                    System.out.println("Invalid date format for publication. Please use yyyy-MM-dd.");
                    continue;
                }

                researchPapers.add(new ResearchPaper(paperTitle.trim(), authors, journal, doi, pages, citations, published));
            } else {
                System.out.println("Using default values for the paper \"" + paperTitle.trim() + "\".");
                researchPapers.add(new ResearchPaper(
                        paperTitle.trim(),
                        authors,
                        "Default Journal",
                        "Default DOI",
                        10,
                        0,
                        "2020-01-01"
                ));
            }
        }
        return new Researcher("", "", "", new Date(), hIndex, researchPapers);
    }
}
		
}

