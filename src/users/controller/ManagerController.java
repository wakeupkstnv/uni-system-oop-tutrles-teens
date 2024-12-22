package users.controller;

import core.CoreSystem;
import post.News;
import post.Request;
import post.Urgency;
import study.utils.Course;
import users.Faculty;
import users.models.Dean;
import users.models.Employee;
import users.models.Manager;
import users.models.Teacher;
import users.view.ManagerView;

import java.util.HashMap;
import java.util.Objects;

public class ManagerController<Model extends Manager, View extends ManagerView> extends EmployeeController<Manager, ManagerView> {

    public ManagerController(Model currentModel, ManagerView mv) {
        super(currentModel, mv);
    }


    public void rejectRequest(Request request) {
        if(request.getUrgency() != Urgency.HIGH){
            request.setSigned(false);
            redirectRequest(request, (Employee) request.getAuthor());

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

    public void signRequest(Request request, Faculty faculty) {
        if (request.getUrgency() != Urgency.HIGH){
            request.setSigned(true);
            redirectRequest(request, (Employee) request.getAuthor());
        }
        else {
        	HashMap<Faculty, Dean> dean = database.getInstance().getFacultyDean();
            Dean deanFromHashMap = dean.get(faculty);
        	redirectRequest(request, deanFromHashMap);
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     * @generated
     * @ordered
     */

 

    public void addNews(News news) {
        database.getInstance().getNews().add(news);

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

        News newsToEdit = database.getInstance().getNews().stream()
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
        database.getInstance().getNews().remove(news);

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

        News newsToEdit = database.getInstance().getNews().stream()
                .filter(n -> Objects.equals(n.getTopic(), topic))
                .findFirst()
                .orElse(null);

        if (newsToEdit != null) {
            database.getInstance().getNews().remove(newsToEdit);

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
        if (database.getInstance().getRegistationState()) {

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
            database.getInstance().setRegistationState(true);

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
        if (!database.getInstance().getRegistationState()) {
            getCurrentView().showSomeText("Registration is already closed.", getCurrentModel());
        } else {
            database.getInstance().setRegistationState(false);

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
        Teacher teacher = database.getInstance().getTeachers()
                .stream()
                .filter(n -> Objects.equals(n.getUuid(), uuidOfTeacher))
                .findFirst().orElse(null);

        Course course = database.getInstance().getCourses()
                .stream()
                .filter(n -> Objects.equals(n.getUuid(), uuidOfCourse))
                .findFirst().orElse(null);

        if (teacher == null || course == null) {
            // Handle error for teacher or course not found
        }
    }
}
