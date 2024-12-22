package users.controller;

import post.Request;
import post.Urgency;
import users.models.Dean;
import users.models.Employee;
import users.models.Manager;
import users.view.DeanView;
import users.view.ManagerView;

public class DeanController<Model extends Dean, View extends DeanView> extends ManagerController<Dean, DeanView>   {
	


    public DeanController(Model currentModel, DeanView dv) {
        super(currentModel, dv);
    }
    
    public void rejectRequest(Request request) {
            request.setSigned(false);
            redirectRequest(request, (Employee) request.getAuthor());
    }

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     * @generated
     * @ordered
     */

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     * @generated
     * @ordered
     */

    public void signRequest(Request request) {
            request.setSigned(true);
            redirectRequest(request, (Employee) request.getAuthor());
    }



    
    
    
	
	
}
