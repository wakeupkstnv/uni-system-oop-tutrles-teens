package users.models;

import post.Request;

import java.util.Date;
import java.util.Vector;

/**
 * Класс для менеджера, наследующий от Employee
 */


public class Manager extends Employee {

    protected Vector<Request> allRequests;

    public Manager(){
        super();
    }

    /**
     * Конструктор для создания объекта Manager
     */
    public Manager(String id, String firstName, String lastName, String email, String login, Date birthDate, 
                   String hashedPassword) {
        super(id, firstName, lastName, email, login, birthDate, hashedPassword);
        
    }

    /**
     * Геттер для всех заявок
     */
    

    /**
     * Сеттер для всех заявок
     */


    
}
