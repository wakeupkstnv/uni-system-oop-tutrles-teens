package users.controller;

import java.util.Vector;
import java.util.stream.Collectors;

import papers.Journal;
import papers.ResearchPaper;
import papers.comparators.ResearchPapersCitationComparator;
import post.News;
import users.models.Researcher;
import users.models.User;
import users.view.ResearcherView;

public class ResearcherController<Model extends Researcher, View extends ResearcherView>{
	protected Model currentModel;
    private final Database database = Database.getInstance();
    protected View currentView;
    protected Language language = CoreSystem.getLanguageMode();

    public ResearcherController(){

    }

    public ResearcherController(Model currentModel, View currentView){
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
        //TODO: DODELAT
        User u =  Database.getInstance()
                .getUsers()
                .stream()
                .filter(user -> user.getLogin().equals(email))
                .findFirst()
                .orElse(null);
        if (u == null){
            System.out.println("Exception: user not found"); // TODO: make the exceptions
            return false;
        }

        String psw = Database.getInstance().getUserPasswords().get(u);
        if (psw == null || psw != password){
            System.out.println("Exception: password is not correct");
            return false;
        }
        return true;
    }

    public boolean logout(){
        return false;
    }

    @SuppressWarnings("unchecked")
	public void viewProfile(){
        currentView.showProfile(currentModel);
    }

    public void viewNotifactions() {currentView.showNotifications(currentModel.getUserInstance().getAllNotifications());}
    public Boolean subscribeToJournal(Journal journal){
        journal.addSubscriber(currentModel);
        return true;
    }

    public Boolean subscribeToJournal(String uuid){
        Journal j = Database.getInstance().getJournals()
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

        j.addSubscriber(currentModel);
        return true;
    }

    public boolean giveLike(News news){
        news.setLikeCount(news.getLikeCount() + 1);
        return true;
    }

    public boolean giveLike(String uuid){
        News ns = Database.getInstance().getNews().stream().filter(n->n.getUuid().equals(uuid)).findFirst().orElse(null);
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
