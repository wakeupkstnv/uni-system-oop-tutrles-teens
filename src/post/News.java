package post;


import users.models.User;

import javax.xml.stream.events.Comment;
import java.util.Date;
import java.util.Objects;
import java.util.Vector;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class News extends Post
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private Vector<Comment> comments;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private int likeCount = 0;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public String topic;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 *
	 * @param author
	 * @param text
	 * @param date
	 * @generated
	 */
	public News(User author, String text, Date date) {
		super(author, text, date);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public Vector<Comment> getComments() {
		return comments;
	}

	public void setComments(Vector<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		News news = (News) o;
		return likeCount == news.likeCount && Objects.equals(comments, news.comments) && Objects.equals(topic, news.topic);
	}

	@Override
	public int hashCode() {
		return Objects.hash(comments, likeCount, topic);
	}

	@Override
	public String toString() {
		return "News{" +
				"comments=" + comments +
				", likeCount=" + likeCount +
				", topic='" + topic + '\'' +
				'}';
	}
}

