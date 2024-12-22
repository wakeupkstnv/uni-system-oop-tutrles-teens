package users.controller;

import users.models.MasterStudent;
import users.models.PhdStudent;
import users.view.MasterStudentView;
import users.view.PhdStudentView;

public class PhdStudentController  <Model extends PhdStudent , View extends PhdStudentView> extends MasterStudentController<PhdStudent, PhdStudentView>{

	public PhdStudentController(Model currentModel, View currentView) {
		super(currentModel, currentView);
	}
	public void updateDissertationTitle(String title) {
        ((PhdStudent) model).setDissertationTitle(title);
    }

    public void showProfile() {
        view.displayStudentProfile(model);
    }
}


