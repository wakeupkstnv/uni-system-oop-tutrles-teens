package post;


import users.models.Employee;
import users.models.User;

import java.util.Date;
import java.util.Objects;

public class Request extends Post
{

	private boolean isSigned;

	private Urgency urgency;

	private String description;

	public Request(Employee author, String text, Date date, Urgency urgency, String description) {
		super(author, text, date);
		this.urgency = urgency;
		this.description = description;
		this.isSigned = false;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Urgency getUrgency() {
		return urgency;
	}

	public void setUrgency(Urgency urgency) {
		this.urgency = urgency;
	}

	public void setSigned(boolean signed) {
		isSigned = signed;
	}

	public boolean getSigned(){
		return this.isSigned;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Request request = (Request) o;
		return isSigned == request.isSigned && urgency == request.urgency && Objects.equals(description, request.description);
	}

	@Override
	public int hashCode() {
		return Objects.hash(isSigned, urgency, description);
	}

	@Override
	public String
	toString() {
		return "Request{" +
				"isSigned=" + isSigned +
				", urgency=" + urgency +
				", description='" + description + '\'' +
				'}';
	}
}

