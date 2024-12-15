package Users.Controller;

import Papers.ResearchPaper;
import Users.Models.User;
import Users.UserType;
import Users.TeacherType;
import Users.Models.Employee;
import Users.Models.Student;
import Users.Models.Dean;
import Users.Models.Teacher;
import Users.Models.Admin;
import Users.Models.Researcher;
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
        default:
            throw new IllegalArgumentException("Invalid user type");
            
            // используем фактори чтобы создавать юзеров, сделал отдельные ПРИВАТНЫЕ методоы чтобы было удобно!
            // пока нет GraduatedStudent, PhDStudent, и MasterStudent, не могу понять их логику.
       }
        
}
    		//ПРИВАТНЫЕ МЕТОДЫ
	private Student createStudent(String id, String firstName, String lastName, Date birthDate, Scanner scanner) {
	    System.out.print("Enter Year of Study: ");
	    int year = scanner.nextInt();
	    scanner.nextLine();  // consume the newline
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
	    System.out.print("Enter hindex: ");
	    double hindex = scanner.nextDouble();
	    scanner.nextLine(); 
	
	    System.out.print("Enter Research Papers (separate by commas): ");
	    String papersInput = scanner.nextLine();
	    String[] papersArray = papersInput.split(",");
	    Vector<ResearchPaper> researchPapers = new Vector<>();
	    
	    Researcher researcher = new Researcher(id, firstName, lastName, birthDate, hindex, new Vector<>());
	
	    for (String paperTitle : papersArray) {
	        System.out.print("Would you like to enter custom data for the paper \"" + paperTitle.trim() + "\"? (yes/no): ");
	        String choice = scanner.nextLine().trim().toLowerCase();
	        
	        Vector<Researcher> authors = new Vector<>();
	        authors.add(researcher); 
	
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
	    return new Researcher(id, firstName, lastName, birthDate, hindex, researchPapers);
	}
		
}

