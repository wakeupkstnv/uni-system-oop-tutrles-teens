package users.controller;

import users.models.GraduateStudent;
import users.models.MasterStudent;
import users.view.GraduateStudentView;
import users.view.MasterStudentView;

public class MasterStudentController <Model extends MasterStudent , View extends MasterStudentView> extends GraduateStudentController<MasterStudent, MasterStudentView> {

    public MasterStudentController(Model currentModel, View currentView) {
		super(currentModel, currentView);
    }

    public void updateMasterCourse(int course) {
        ((MasterStudent) model).setMasterCourse(course);
    }

    public void showProfile() {
        view.displayStudentProfile(model);
    }
}
