package Users.Controller;

import Papers.Journal;
import Users.Models.User;
import Users.View.UserView;
import database.Database;

public class UserController<Model extends User, View extends UserView> {
    private Model currentModel;
    private final Database database = Database.getInstanceOfDatabse();
    private View currentView;

    public UserController(Model currentModel, View currentView){
        this.currentModel = currentModel;
        this.currentView = currentView;
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
        return false;
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
