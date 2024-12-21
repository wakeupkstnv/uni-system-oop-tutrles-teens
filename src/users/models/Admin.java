package users.models;

import java.util.Date;

public class Admin extends Manager{
    public Admin(String id, String firstName, String lastName, String email, String login, Date birthDate, 
            String hashedPassword) {
   super(id, firstName, lastName, email, login, birthDate, hashedPassword);
}
}
