package users.controller;

import users.models.Dean;
import users.models.Manager;
import users.view.DeanView;
import users.view.ManagerView;

public class DeanController<Model extends Dean, View extends DeanView> extends ManagerController<Dean, DeanView>   {
	
	public DeanController(){
        super();
    }

    public DeanController(Model currentModel, DeanView dv) {
        this.currentModel = currentModel;
        this.currentView = dv;
    }
    
    
    
	
	
}
