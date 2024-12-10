package Post;


import Users.Models.User;

import java.util.Date;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

abstract public class Post
{
	private User author;
	private String text;
	private Date date;
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

}

