package users.models;

import java.util.Comparator;
import java.util.Date;
import java.util.Objects;
import java.util.Vector;

import papers.ResearchPaper;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Researcher implements Comparable<Researcher>
{
	private String uuid;
	private double hindex;
	private Vector<ResearchPaper> articles;
	private User userInstance;
	public Researcher(String uuid, User user){
		this.uuid = uuid;
		this.userInstance = user;
		this.hindex = 0;
		this.articles = new Vector<ResearchPaper>();
	}

	public Researcher(String uuid, User user, double hindex){
		this(uuid, user);
		this.hindex = hindex;
	}

	public Researcher(String uuid, User user, double hindex, Vector<ResearchPaper> articles) {
		this(uuid, user, hindex);
		this.articles = articles;
	}

	public double getHindex() {
	    return hindex;
	}

	public void setHindex(double hindex) {
	    this.hindex = hindex;
	}


	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUuid() {
		return uuid;
	}

	public Vector<ResearchPaper> getArticles() {
	    return articles;
	}

	public void setArticles(Vector<ResearchPaper> articles) {
	    this.articles = articles;
	}

	public User getUserInstance() {
		return userInstance;
	}

	public void setUserInstance(User userInstance) {
		this.userInstance = userInstance;
	}

	@Override
	public int compareTo(Researcher o) {
		return Double.compare(this.getHindex(), o.getHindex());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Researcher that = (Researcher) o;
		return Double.compare(that.hindex, hindex) == 0 && Objects.equals(uuid, that.uuid) && Objects.equals(articles, that.articles) && Objects.equals(userInstance, that.userInstance);
	}

	@Override
	public int hashCode() {
		return Objects.hash(uuid, hindex, articles, userInstance);
	}

	@Override
	public String toString() {
		return "Researcher{" +
				"uuid='" + uuid + '\'' +
				", hindex=" + hindex +
				", articles=" + articles +
				", userInstance=" + userInstance +
				'}';
	}
}
