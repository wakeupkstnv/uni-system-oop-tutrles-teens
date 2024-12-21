package users.controller;

import Database.Database;
import papers.Journal;
import papers.ResearchPaper;
import papers.ResearchProject;
import papers.comparators.ResearchPapersCitationComparator;
import post.News;
import users.models.User;
import users.view.AdminView;
import users.view.ManagerView;
import users.view.UserView;

import java.util.Vector;
import java.util.stream.Collectors;

public class UserController<Model extends User, View extends UserView> {
    protected Model currentModel;
    private final Database database = Database.getInstanceOfDatabse();
    protected View currentView;

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

    public void viewNotifactions() {currentView.showNotifications(currentModel.getAllNotifications());}
    public Boolean subscribeToJournal(Journal journal){
        journal.getSubscribers().add(currentModel);
        return true;
    }

    public Boolean subscribeToJournal(String uuid){
        Journal j = Database.getInstance().getJournals()
                .stream()
                .filter(n->n.getUuid().equals(uuid))
                .findFirst()
                .orElse(null);

        if (j == null) return false; //TODO: EXCEPTION!!

        j.getSubscribers().add(currentModel);
        return true;
    }

    public boolean giveLike(News news){
        news.setLikeCount(news.getLikeCount() + 1);
        return true;
    }

    public boolean giveLike(String uuid){
        News ns = Database.getInstance().getNews().stream().filter(n->n.getUuid().equals(uuid)).findFirst().orElse(null);
        if (ns == null) return false; //TODO: EXCETION!!
        ns.setLikeCount(ns.getLikeCount() + 1);
        return true;
    }

    public void viewPapers(){
        currentView.showPapers(Database.getInstance().getResearchPapers());
    }

    public void viewTopPapers(){
        Vector<ResearchPaper> topPapers = Database.getInstance()
                .getResearchPapers()
                .stream()
                .sorted(new ResearchPapersCitationComparator())
                .limit(10)
                .collect(Collectors.toCollection(Vector::new));
        currentView.showPapers(topPapers);
    }

    public void viewNews(){
        System.out.println(Database.getInstance().getNews());
    }

    public boolean resetPassword(){
        // TODO: need to realize this method
        return false;
    }
}
