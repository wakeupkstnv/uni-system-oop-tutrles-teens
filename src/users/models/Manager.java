package users.models;

import java.util.Date;
import java.util.Vector;

import post.Request;

/**
 * Класс для менеджера, наследующий от Employee
 */
public class Manager extends Employee {

    protected Vector<Request> allRequests;

    /**
     * Конструктор для создания объекта Manager
     */
    public Manager(String id, String firstName, String lastName, String email, String login, Date birthDate, 
                   String hashedPassword, java.util.Vector<String> allNotifications) {
        super(id, firstName, lastName, email, login, birthDate, hashedPassword, allNotifications);
        this.allRequests = new Vector<>();
    }

    /**
     * Геттер для всех заявок
     */
    public Vector<Request> getAllRequests() {
        return allRequests;
    }

    /**
     * Сеттер для всех заявок
     */
    public void setAllRequests(Vector<Request> allRequests) {
        this.allRequests = allRequests;
    }


    
}
