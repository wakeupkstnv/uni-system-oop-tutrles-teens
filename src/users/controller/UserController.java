package users.controller;

import core.CoreSystem;
import database.Database;
import papers.Journal;
import papers.ResearchPaper;
import papers.comparators.ResearchPapersCitationComparator;
import post.News;
import users.models.User;
import users.view.UserView;

import java.util.Vector;
import java.util.stream.Collectors;

public class UserController<Model extends User, View extends UserView> {
    protected Model currentModel;
    protected final static Database database = Database.getInstance();
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

    public static boolean login(String email, String password){
        User u = database.getInstance()
                .getUsers()
                .stream()
                .filter(user -> user.getLogin().equals(email))
                .findFirst()
                .orElse(null);
        if (u == null){
            System.out.println("Ошибка: пользователь не найден.");
            return false;
        }

        String psw = database.getInstance().getUserPasswords().get(u);
        if (psw == null || !psw.equals(password)){
            System.out.println("Ошибка: неверный пароль.");
            return false;
        }
        return true;
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
        Journal j = database.getInstance().getJournals()
                .stream()
                .filter(n->n.getUuid().equals(uuid))
                .findFirst()
                .orElse(null);

        if (j == null) {
            String message = "";
            switch (CoreSystem.getLanguageMode()) {
                case RUS:
                    message = "Журнал с UUID " + uuid + " не существует\n";
                    break;
                case ENG:
                    message = "Journal with UUID " + uuid + " does not exist\n";
                    break;
                default:
                    message = "Журнал UUID-мен " + uuid + " жоқ";
            }
            throw new IllegalArgumentException(message);
        }

        j.getSubscribers().add(currentModel);
        return true;
    }

    public boolean giveLike(News news){
        news.setLikeCount(news.getLikeCount() + 1);
        return true;
    }

    public boolean giveLike(String uuid){
        News ns = database.getInstance().getNews().stream().filter(n->n.getUuid().equals(uuid)).findFirst().orElse(null);
        if (ns == null) {
            String message = "";
            switch (CoreSystem.getLanguageMode()) {
                case RUS:
                    message = "Новость с UUID " + uuid + " не существует\n";
                    break;
                case ENG:
                    message = "News with UUID " + uuid + " does not exist\n";
                    break;
                default:
                    message = "Новость UUID-мен " + uuid + " жоқ";
            }
            throw new IllegalArgumentException(message);
        }
        ns.setLikeCount(ns.getLikeCount() + 1);
        return true;
    }

    public void viewPapers(){
        currentView.showPapers(database.getInstance().getResearchPapers());
    }

    public void viewTopPapers(){
        Vector<ResearchPaper> topPapers = database.getInstance()
                .getResearchPapers()
                .stream()
                .sorted(new ResearchPapersCitationComparator())
                .limit(10)
                .collect(Collectors.toCollection(Vector::new));
        currentView.showPapers(topPapers);
    }

    public void viewNews(){
        System.out.println(database.getInstance().getNews());
    }

    public boolean resetPassword(){
        // TODO: need to realize this method
        return false;
    }
}
