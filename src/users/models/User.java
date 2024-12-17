package users.models;

import papers.ResearchPaper;

import java.util.Date;
import java.util.Objects;
import java.util.Vector;

/**
 * <!-- begin-user-doc -->

 * <!--  end-user-doc  -->
 * @generated
 */
public abstract class User {
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	public String uuid;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	public String firstName;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	public String lastName;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	public String email;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	public String login;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	public Date birthDate;

	private String middleName;
	private int year;

	private Vector<ResearchPaper> allNotifications;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public User() {
		super();
	}

	// Constructor with parameters
	public User(String uuid, String firstName, String lastName, String email, String login, Date birthDate, String middleName, int year) {
		this.uuid = uuid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.login = login;
		this.birthDate = birthDate;
		this.middleName = middleName;
		this.year = year;
	}

	// Getters and Setters

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Vector<ResearchPaper> getAllNotifications() {
		return allNotifications;
	}

	public void setAllNotifications(Vector<ResearchPaper> allNotifications) {
		this.allNotifications = allNotifications;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return year == user.year && Objects.equals(uuid, user.uuid) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(email, user.email) && Objects.equals(login, user.login) && Objects.equals(birthDate, user.birthDate) && Objects.equals(middleName, user.middleName) && Objects.equals(allNotifications, user.allNotifications);
	}

	@Override
	public int hashCode() {
		return Objects.hash(uuid, firstName, lastName, email, login, birthDate, middleName, year, allNotifications);
	}

	@Override
	public String toString() {
		return "User{" +
				"uuid='" + uuid + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", email='" + email + '\'' +
				", login='" + login + '\'' +
				", birthDate=" + birthDate +
				", middleName='" + middleName + '\'' +
				", year=" + year +
				", allNotifications=" + allNotifications +
				'}';
	}
}
