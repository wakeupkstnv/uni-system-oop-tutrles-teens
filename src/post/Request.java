package post;


import users.models.User;

import java.util.Date;

public class Request extends Post
{

	private boolean isSigned;

	private Urgency urgency;

	private String description;

	public Request(User author, String text, Date date, Urgency urgency, String description) {
		super(author, text, date);
		this.urgency = urgency;
		this.description = description;
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

}

