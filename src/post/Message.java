package post;

import users.models.User;

import java.util.Date;

public class Message extends Post {
    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     *
     * @param author
     * @param text
     * @param date
     * @generated
     */
    public Message(User author, String text, Date date) {
        super(author, text, date);
    }
}
