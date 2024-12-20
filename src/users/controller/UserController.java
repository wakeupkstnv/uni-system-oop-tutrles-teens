package users.controller;

import database.Database;
import papers.Journal;
import post.News;
import users.models.User;
import users.view.UserView;

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
        //TODO: DODELAT
        User u =  Database.getInstance()
                .getUsers()
                .stream()
                .filter(user -> user.getLogin().equals(email))
                .findFirst()
                .orElse(null);
        return false;
    }

    public boolean logout(){
        return false;
    }

    public void viewProfile(){
        currentView.showProfile(currentModel);
    }

    public Boolean subscribeToJournal(Journal journal){
        Journal j = Database.getInstance().getJournals().stream().filter(n->n.equals(journal)).findFirst().orElse(null);

        if (j == null) return false; // TODO: exception!!

        j.getSubscribers().add(currentModel);
        return true;
    }

    public Boolean subscribeToJournal(String uuid){
        Journal j = Database.getInstance().getJournals().stream().filter(n->n.getUuid().equals(uuid)).findFirst().orElse(null);
        if (j == null) return false; //TODO: EXCEPTION!!

        j.getSubscribers().add(currentModel);
        return true;
    }

    public boolean giveLike(News news){
        News n = Database.getInstance().getNews().stream().filter(n->n.equals(news)).findFirst().orElse(null);
        if (n == null) return false; //TODO: EXCETION!!
        n.setLikeCount(n.getLikeCount() + 1);
        return true;
    }

    public boolean giveLike(String uuid){
        //TODO: do with uuid its very easy
        return false;
    }

}
