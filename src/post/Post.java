package post;


import users.models.Employee;
import users.models.User;

import java.util.Date;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

abstract public class Post
{
	private String uuid;
	private User author;
	private String text;
	private Date date;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */

	public Post(User author, String text, Date date){
		this.author = author;
		this.text = text;
		this.date = date;
	}

	public User getAuthor() {
		return author;
	}
}

