package users.models;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Vector;

public abstract class User implements Serializable {
    protected String uuid;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String login;
    protected Date birthDate;
    protected String hashedPassword;
    protected Vector<String> allNotifications;
    protected boolean isBanned;

    public User() {
        // Default constructor
    }

    public User(String uuid, String firstName, String lastName, String email, String login, Date birthDate, String hashedPassword) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.login = login;
        this.birthDate = birthDate;
        this.hashedPassword = hashedPassword;
        this.allNotifications = new Vector<>();
        this.isBanned = false;
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

    public boolean isBanned() {
        return isBanned;
    }

    public void setBanned(boolean isBanned) {
        this.isBanned = isBanned;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;
        return Objects.equals(uuid, user.uuid) &&
                Objects.equals(login, user.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, login);
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
                ", isBanned=" + isBanned +
                '}';
    }
}
