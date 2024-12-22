package users.models;

import post.Message;
import post.Request;

import java.util.Date;
import java.util.Vector;

public class Employee extends User implements CanBecomeResearcher {
	protected Vector<Request> allRequests;
	protected Vector<Request> myRequests;
	protected Vector<Message> allMessages;
	protected Researcher researcherProfile;

	public Employee() {
		super();
		this.allMessages = new Vector<>();
		this.allRequests = new Vector<>();
		this.myRequests = new Vector<>();
	}

	public Employee(String id, String firstName, String lastName, String email, String login, Date birthDate, String hashedPassword) {
		super(id, firstName, lastName, email, login, birthDate, hashedPassword);
		this.allMessages = new Vector<>();
		this.allRequests = new Vector<>();
		this.myRequests = new Vector<>();
	}

	public void sendMessage(Employee employee, String text){
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

	public void setMyRequests(Vector<Request> myRequests) {
		this.myRequests = myRequests;
	}

	@Override
	public void becomeResearcher() {
		this.researcherProfile = new Researcher(this.uuid, this, 0.0);
	}

	public Researcher getResearcherProfile() {
		return researcherProfile;
	}

	public void setResearcherProfile(Researcher researcherProfile) {
		this.researcherProfile = researcherProfile;
	}

	public void addRequest(Request request) {
		allRequests.add(request);
	}

	public void addMyRequest(Request request) {
		myRequests.add(request);
		allRequests.add(request);
	}
}
