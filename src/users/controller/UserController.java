package users.controller;

import papers.Journal;
import users.models.User;
import users.view.UserView;
import Database.Database;

import javax.xml.crypto.Data;

public class UserController<Model extends User, View extends UserView> {
    private Model currentModel;
    private final Database database = Database.getInstanceOfDatabse();
    private View currentView;

    public UserController(){

    }

    public UserController(Model currentModel, View currentView){
        this.currentModel = currentModel;
        this.currentView = currentView;
    }


    protected Database getDatabase() {
        return database;
    }

    public Model getCurrentModel() {
        return currentModel;
    }

    public void setCurrentModel(Model currentModel) {
        this.currentModel = currentModel;
    }

    public View getCurrentView() {
        return currentView;
    }

    public void setCurrentView(View currentView) {
        this.currentView = currentView;
    }

    public boolean login(String email, String password){
        Database.getInstance().getUsers().stream().;
    }

    public boolean logout(){
        return false;
    }

    public void viewProfile(){
        currentView.showProfile(currentModel);
    }

    public Boolean subscribeToJournal(Journal journal){
        return false;
    }




}
