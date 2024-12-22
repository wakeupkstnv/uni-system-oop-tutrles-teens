package users.controller;

import core.CoreSystem;
import database.Database;
import post.News;
import post.Request;
import post.Urgency;
import study.utils.Course;
import users.models.Employee;
import users.models.Manager;
import users.models.Teacher;
import users.models.User;
import users.view.ManagerView;
import users.view.UserView;

import java.util.Objects;

public class ManagerController<Model extends Manager, View extends ManagerView> extends EmployeeController<Manager, ManagerView> {

    public ManagerController() {
        super();
    }

    public ManagerController(Model currentModel, ManagerView mv) {
        this.currentModel = currentModel;
        this.currentView = mv;
    }


    public void rejectRequest(Request request) {
        if(request.getUrgency() != Urgency.HIGH){
            request.setSigned(false);
            redirectRequest(request, request.getAuthor());
            // TODO добавление в лог бд
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     * @generated
     * @ordered
     */

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     * @generated
     * @ordered
     */

    public void signRequest(Request request) {
        if (request.getUrgency() != Urgency.HIGH){
            request.setSigned(true);
            redirectRequest(request, request.getAuthor());
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     * @generated
     * @ordered
     */

    public void redirectRequest(Request request, User user) {

    }

    public void addNews(News news) {
        Database.getInstance().getNews().add(news);

        switch (CoreSystem.getLanguageMode()) {
            case ENG:
                getCurrentView().showSomeText("News added successfully.", getCurrentModel());
                break;
            case RUS:
                getCurrentView().showSomeText("Новость успешно добавлена.", getCurrentModel());
                break;
            case KZ:
                getCurrentView().showSomeText("Жаңалық сәтті қосылды.", getCurrentModel());
                break;
            }
    }

    public void editNews(String topic, String newTopic) {

        News newsToEdit = Database.getInstance().getNews().stream()
                .filter(n -> Objects.equals(n.getTopic(), topic))
                .findFirst()
                .orElse(null);

        if (newsToEdit != null) {
            if (newTopic != null && !newTopic.trim().isEmpty()) {
                newsToEdit.setTopic(newTopic);

                switch (CoreSystem.getLanguageMode()) {
                    case ENG:
                        getCurrentView().showSomeText("News updated successfully.", getCurrentModel());
                        break;
                    case RUS:
                        getCurrentView().showSomeText("Новость обновлена.", getCurrentModel());
                        break;
                    case KZ:
                        getCurrentView().showSomeText("Жаңалық сәтті жаңартылды.", getCurrentModel());
                        break;
                }
            } else {
                switch (CoreSystem.getLanguageMode()) {
                    case ENG:
                        getCurrentView().showError("Validation failed.");
                        break;
                    case RUS:
                        getCurrentView().showError("Проверка не пройдена.");
                        break;
                    case KZ:
                        getCurrentView().showError("Тексеру сәтсіз аяқталды.");
                        break;
                    }
            }

        } else {
            switch (CoreSystem.getLanguageMode()) {
                case ENG:
                    getCurrentView().showError("News with the topic '" + topic + "' not found.");
                    break;
                case RUS:
                    getCurrentView().showError("Новость с темой '" + topic + "' не найдена.");
                    break;
                case KZ:
                    getCurrentView().showError("Тақырыбы '" + topic + "' бар жаңалық табылмады.");
                    break;
                }
        }
    }

    void deleteNews(News news) {
        Database.getInstance().getNews().remove(news);

        switch (CoreSystem.getLanguageMode()) {
            case ENG:
                getCurrentView().showSomeText("News deleted successfully.", getCurrentModel());
                break;
            case RUS:
                getCurrentView().showSomeText("Новость успешно удалена.", getCurrentModel());
                break;
            case KZ:
                getCurrentView().showSomeText("Жаңалық сәтті өшірілді.", getCurrentModel());
                break;
            }
    }

    public void deleteNews(String topic) {

        News newsToEdit = Database.getInstance().getNews().stream()
                .filter(n -> Objects.equals(n.getTopic(), topic))
                .findFirst()
                .orElse(null);

        if (newsToEdit != null) {
            Database.getInstance().getNews().remove(newsToEdit);

            switch (CoreSystem.getLanguageMode()) {
                case ENG:
                    getCurrentView().showSomeText("News deleted successfully.", getCurrentModel());
                    break;
                case RUS:
                    getCurrentView().showSomeText("Новость удалена.", getCurrentModel());
                    break;
                case KZ:
                    getCurrentView().showSomeText("Жаңалық сәтті өшірілді.", getCurrentModel());
                    break;
                }
        } else {
            switch (CoreSystem.getLanguageMode()) {
                case ENG:
                    getCurrentView().showError("News with the topic '" + topic + "' not found.");
                    break;
                case RUS:
                    getCurrentView().showError("Новость с темой '" + topic + "' не найдена.");
                    break;
                case KZ:
                    getCurrentView().showError("Тақырыбы '" + topic + "' бар жаңалық табылмады.");
                    break;
                }
        }
    }

    public void openRegistation() {
        if (Database.getInstance().getRegistationState()) {

            switch (CoreSystem.getLanguageMode()) {
                case ENG:
                    getCurrentView().showSomeText("Registration is already open.", getCurrentModel());
                    break;
                case RUS:
                    getCurrentView().showSomeText("Регистрация уже открыта.", getCurrentModel());
                    break;
                case KZ:
                    getCurrentView().showSomeText("Тіркеу already ашық.", getCurrentModel());
                    break;
                }
        } else {
            Database.getInstance().setRegistationState(true);

            switch (CoreSystem.getLanguageMode()) {
                case ENG:
                    getCurrentView().showSomeText("Registration is open!", getCurrentModel());
                    break;
                case RUS:
                    getCurrentView().showSomeText("Регистрация открыта!", getCurrentModel());
                    break;
                case KZ:
                    getCurrentView().showSomeText("Тіркеу ашылды!", getCurrentModel());
                    break;
                }
        }
    }

    void closeRegistation() {
        if (!Database.getInstance().getRegistationState()) {
            getCurrentView().showSomeText("Registration is already closed.", getCurrentModel());
        } else {
            Database.getInstance().setRegistationState(false);

            switch (CoreSystem.getLanguageMode()) {
                case ENG:
                    getCurrentView().showSomeText("Registration is closed.", getCurrentModel());
                    break;
                case RUS:
                    getCurrentView().showSomeText("Регистрация закрыта.", getCurrentModel());
                    break;
                case KZ:
                    getCurrentView().showSomeText("Тіркеу жабылды.", getCurrentModel());
                    break;
                }
        }
    }

    void addTeacherToCourse(String uuidOfTeacher, String uuidOfCourse) {
        Teacher teacher = Database.getInstance().getTeachers()
                .stream()
                .filter(n -> Objects.equals(n.getUuid(), uuidOfTeacher))
                .findFirst().orElse(null);

        Course course = Database.getInstance().getCourses()
                .stream()
                .filter(n -> Objects.equals(n.getUuid(), uuidOfCourse))
                .findFirst().orElse(null);

        if (teacher == null || course == null) {
            // Handle error for teacher or course not found
        }
    }
}
