package users.models;


import papers.ResearchPaper;
import post.Message;
import post.Request;

import java.util.*;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Employee extends User implements CanBecomeResearcher
{
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	protected Vector<Request> allRequests;
	
	protected  Vector<Request> myRequests;


	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */

	
	public Vector<Message> allMessages;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private Researcher reseacherProfile;
	
	
	public Employee(String id, String firstName, String lastName, String email, String login, Date birthDate, String hashedPassword) {
	    super(id, firstName, lastName, email, login, birthDate, hashedPassword);
	    this.allMessages = new Vector<>();
	    this.allRequests = new Vector<>();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	 public void sendMessage(Employee employee, String text) {
	        if (employee == null) {
	            System.out.println("Error: Cannot send message to a null employee.");
	            return;
	        }
	        if (text == null || text.trim().isEmpty()) {
	            System.out.println("Error: Message cannot be empty.");
	            return;
	        }
	        if (employee.getUuid().equals(this.getUuid())) {
                throw new IllegalArgumentException("Cannot send a message to yourself");
            }
	        
	        Message message = new Message(this, text, new Date());
	        
	        employee.allMessages.add(message);
	        System.out.println("Message sent successfully to " + employee.getFirstName() + " " + employee.getLastName());
	    }
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Vector<Message> viewAllMessages() {
	    return allMessages;
	}
	
	public Vector<Request> getAllRequests() {
        return allRequests;
    }
	public Vector<Request> getMyRequests() {
        return myRequests;
    }
	
    public void setAllRequests(Vector<Request> allRequests) {
        this.allRequests = allRequests;
    }
    


	@Override
    public void becomeResearcher() {
        this.reseacherProfile = new Researcher(
				this.uuid,
				this
		);
    }

	public void addRequest(Request request) {
		allRequests.add(request);
		
	}
	
	public void addMyRequest(Request request) {
		myRequests.add(request);
		allRequests.add(request);
	}

	
}

