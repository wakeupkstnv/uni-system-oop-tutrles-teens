package users.controller;

import database.Database;
import post.News;
import study.utils.Course;
import users.models.Manager;
import users.models.Teacher;
import users.models.User;
import users.view.ManagerView;
import users.view.UserView;

import java.util.Objects;

public class ManagerController<Model extends Manager, View extends ManagerView> extends UserController<User, UserView> {
    public ManagerController(User currentModel, UserView currentView) {
        super(currentModel, currentView);
    }

    void addNews(News news){
        Database.getInstance().addNews(news);
    }

    void editNews(String topic, String newTopic){

        News newsToEdit = getDatabase().getNews().stream()
                .filter(n -> Objects.equals(n.getTopic(), topic))
                .findFirst()
                .orElse(null);

        if (newsToEdit != null) {
            if (newTopic != null && !newTopic.trim().isEmpty()) {
                newsToEdit.setTopic(newTopic);
                getCurrentView().showSomeText("News updated successfully.", getCurrentModel());
            } else {
                getCurrentView().showError("Validation failed");
            }

        } else {
            getCurrentView().showError("News with the topic '" + topic + "' not found.");
        }
    }

    void deleteNews(News news){
        getDatabase().getNews().remove(news);
    }

    void deleteNews(String topic){

        News newsToEdit = getDatabase().getNews().stream()
                .filter(n -> Objects.equals(n.getTopic(), topic))
                .findFirst()
                .orElse(null);

        if (newsToEdit != null) {
            getDatabase().getNews().remove(newsToEdit);
            getCurrentView().showSomeText("News updated successfully.", getCurrentModel());
        } else {
            getCurrentView().showError("News with the topic '" + topic + "' not found.");
        }
    }

    void openRegistation(){
        if (getDatabase().getRegistationState()){
            getCurrentView().showSomeText("Registation is already open", getCurrentModel());
        } else {
            getDatabase().setRegistationState(true);
            getCurrentView().showSomeText("Registation is open!", getCurrentModel());
        }
    }

    void closeRegistation(){
        if (!getDatabase().getRegistationState()){
            getCurrentView().showSomeText("Registation is already close", getCurrentModel());
        } else {
            getDatabase().setRegistationState(true);
            getCurrentView().showSomeText("Registation is close!", getCurrentModel());
        }
    }

    void addTeacherToCourse(String uuidOfTeacher, String uuidOfCourse){
        Teacher teacher = getDatabase().getTeachers()
                .stream()
                .filter(n -> Objects.equals(n.getUuid(), uuidOfTeacher))
                .findFirst().orElse(null);

        Course course = getDatabase().getCourses()
                .stream()
                .filter(n -> Objects.equals(n.getUuid(), uuidOfCourse))
                .findFirst().orElse(null);

        if (teacher == null || course == null){

        }
    }

}
