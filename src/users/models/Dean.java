package users.models;

import post.Request;
import users.Faculty;

import java.util.Date;
import java.util.Vector;

public class Dean extends Manager {
	private Faculty faculty;

	private Vector<Request> allRequests;

	public Dean() {
		super();
		this.allRequests = new Vector<>();
	}

	public Dean(String id, String firstName, String lastName, String email, String login, Date birthDate,
				String hashedPassword, Faculty faculty) {
		super(id, firstName, lastName, email, login, birthDate, hashedPassword);
		this.faculty = faculty;
		this.allRequests = new Vector<>();
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	public Vector<Request> getAllRequests() {
		return allRequests;
	}

	public void setAllRequests(Vector<Request> allRequests) {
		this.allRequests = allRequests;
	}

	public void addRequest(Request request) {
		allRequests.add(request);
	}
}
