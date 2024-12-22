package users.controller;

import users.models.GraduateStudent;
import users.models.Student;
import users.view.GraduateStudentView;
import users.view.StudentView;

public class GraduateStudentController <Model extends GraduateStudent , View extends GraduateStudentView> extends StudentController<GraduateStudent, GraduateStudentView> {
    protected GraduateStudent model;
    protected GraduateStudentView view;

    public GraduateStudentController(Model currentModel, View currentView) {
		super(currentModel, currentView);
    }

    public void updateGraduationYear(int year) {
        model.setGraduationYear(year);
    }

    public void showProfile() {
        view.displayStudentProfile(model);
    }
}

