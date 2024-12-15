package Users.Models;


import Post.Message;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import java.util.Vector;

/**
 * <!-- begin-user-doc -->
 	Standart Employee class,
 	every Employee can be Researcher
 	and every Employee can view and send messages
 * <!--  end-user-doc  -->
 * @generated
 */

abstract public class Employee extends User
{
	/**
	 * <!-- begin-user-doc -->
	 *    Messages is hashmap collection

	 *    User: Vector with messages

	 *
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	public HashMap<Employee, Vector<Message>> allMessages;


	/**
	 * <!-- begin-user-doc -->
	 *     research profile 
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	private Researcher reseacherProfile;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public Employee(String uuid, String firstName, String lastName, String email, String login, Date birthDate, String middleName, int year){
		super(uuid, firstName, lastName, email, login, birthDate, middleName, year);
		allMessages = new HashMap<Employee, Vector<Message>>();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

//	public void sendMessgae(Employee employee, String text) {
//		// TODO implement me
//	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	public Vector<Message> viewAllMessages() {
		return allMessages;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		Employee employee = (Employee) o;
		return Objects.equals(allMessages, employee.allMessages) && Objects.equals(reseacherProfile, employee.reseacherProfile);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), allMessages, reseacherProfile);
	}

	@Override
	public String toString() {
		return super.toString() + "[Employee{" +
				"allMessages=" + allMessages +
				", reseacherProfile=" + reseacherProfile +
				"}]";
	}
}

