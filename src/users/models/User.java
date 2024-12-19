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

public abstract class User
{
    protected String id;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String login;
    protected Date birthDate;
    protected String hashedPassword;
    protected Vector<String> allNotifications;
    protected boolean isActive;



    /**
     * Конструктор с параметрами для инициализации всех полей
     */
    public User(){
        
    }
    public User(String id, String firstName, String lastName, String email, String login, Date birthDate, String hashedPassword, Vector<String> allNotifications) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.login = login;
        this.birthDate = birthDate;
        this.hashedPassword = hashedPassword;
        this.allNotifications = allNotifications != null ? allNotifications : new Vector<String>(); // Проверяем на null
        this.isActive = false;
    }


    // Геттеры и сеттеры для всех полей

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public Vector<String> getAllNotifications() {
        return allNotifications;
    }

    public void setAllNotifications(Vector<String> allNotifications) {
        this.allNotifications = allNotifications;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    // Переопределение методов equals и hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
               Objects.equals(firstName, user.firstName) &&
               Objects.equals(lastName, user.lastName) &&
               Objects.equals(email, user.email) &&
               Objects.equals(login, user.login) &&
               Objects.equals(birthDate, user.birthDate) &&
               Objects.equals(hashedPassword, user.hashedPassword) &&
               Objects.equals(allNotifications, user.allNotifications);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, login, birthDate, hashedPassword, allNotifications);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", birthDate=" + birthDate +
                ", hashedPassword='" + hashedPassword + '\'' +
                ", allNotifications=" + allNotifications.size() +
                ", isActive=" + isActive +
                '}';
    }
}
