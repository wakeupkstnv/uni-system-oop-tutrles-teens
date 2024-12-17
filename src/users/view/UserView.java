package users.view;


import post.Message;
import post.News;
import users.models.User;

import java.util.Objects;
import java.util.Vector;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class UserView <Model extends User>
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void showLoginResult(boolean success) {
	}

	public void showProfile(Model user){
		System.out.println(user);
		// TODO:
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void showLogoutMessage() {
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void showMessage(Vector<Message> messages) {

	}

	public void showSomeText(String s, Object o){
		System.out.println(o.getClass() + ": " + s);
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void showSubscriptionResult(boolean subscribed) {
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void showNotifications(Vector<News> notifications) {
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void showError(String msg) {
		System.out.println("ERROR MESSAGE: " + msg);
	}
	
}

