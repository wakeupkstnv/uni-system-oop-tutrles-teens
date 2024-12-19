package users.models;

import java.util.Date;
import java.util.Vector;

import papers.ResearchPaper;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Researcher extends User
{
	private double hindex;
	private Vector<ResearchPaper> articles;

	/**
	 * Конструктор для инициализации Researcher
	 */
	public Researcher(String id, String firstName, String lastName, String email, String login, Date birthDate, 
	                  String hashedPassword, Vector<String> notifications, double hindex, Vector<ResearchPaper> rP) {
	    super(id, firstName, lastName, email, login, birthDate, hashedPassword, notifications);
	    this.hindex = hindex;
	    this.articles = rP;
	}

	/**
	 * Геттер для hindex
	 */
	public double getHindex() {
	    return hindex;
	}

	/**
	 * Сеттер для hindex
	 */
	public void setHindex(double hindex) {
	    this.hindex = hindex;
	}

	/**
	 * Геттер для списка статей
	 */
	public Vector<ResearchPaper> getArticles() {
	    return articles;
	}

	/**
	 * Сеттер для списка статей
	 */
	public void setArticles(Vector<ResearchPaper> articles) {
	    this.articles = articles;
	}
}

