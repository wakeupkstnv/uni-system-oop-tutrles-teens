package users.controller;

import core.CoreSystem;
import database.Database;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;
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

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     * @generated
     * @ordered
     */

    // Assuming that the controller should handle user input via BufferedReader.
    // If not, adjust accordingly by removing BufferedReader-based methods.

    public void addNews(BufferedReader reader) throws IOException {
        System.out.print("Uuid автора: ");
        String uuid = reader.readLine();
        System.out.print("Содержание новости: ");
        String content = reader.readLine();

        News newNews = new News(
                database.getInstance().getUsers().stream()
                        .filter(n -> Objects.equals(n.getUuid(), uuid))
                        .findFirst()
                        .orElse(currentModel),
                content,
                new Date()
        );
        database.getInstance().getNews().add(newNews);

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

    public void editNews(BufferedReader reader) throws IOException {
        System.out.print("Тема новости: ");
        String topic = reader.readLine();
        System.out.print("Новая тема: ");
        String newTopic = reader.readLine();
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

    public void deleteNews(BufferedReader reader) throws IOException {
        System.out.print("Uuid новости: ");
        String uuid = reader.readLine();
        boolean removed = database.getInstance().getNews().removeIf(n -> Objects.equals(n.getUuid(), uuid));

        if (removed) {
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
        } else {
            switch (CoreSystem.getLanguageMode()) {
                case ENG:
                    getCurrentView().showError("News with the provided UUID not found.");
                    break;
                case RUS:
                    getCurrentView().showError("Новость с указанным UUID не найдена.");
                    break;
                case KZ:
                    getCurrentView().showError("Белгіленген UUID-мен жаңалық табылмады.");
                    break;
            }
        }
    }

    // Additional deleteNews method based on topic, if needed
    /*
    public void deleteNewsByTopic(String topic) {
        News newsToDelete = database.getInstance().getNews().stream()
                .filter(n -> Objects.equals(n.getTopic(), topic))
                .findFirst()
                .orElse(null);

        if (newsToDelete != null) {
            database.getInstance().getNews().remove(newsToDelete);
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
    */

    public void openRegistration() {
        if (database.getInstance().getRegistationState()) {
            switch (CoreSystem.getLanguageMode()) {
                case ENG:
                    getCurrentView().showSomeText("Registration is already open.", getCurrentModel());
                    break;
                case RUS:
                    getCurrentView().showSomeText("Регистрация уже открыта.", getCurrentModel());
                    break;
                case KZ:
                    getCurrentView().showSomeText("Тіркеу уже ашық.", getCurrentModel());
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

    public void closeRegistration() {
        if (!database.getInstance().getRegistationState()) {
            switch (CoreSystem.getLanguageMode()) {
                case ENG:
                    getCurrentView().showSomeText("Registration is already closed.", getCurrentModel());
                    break;
                case RUS:
                    getCurrentView().showSomeText("Регистрация уже закрыта.", getCurrentModel());
                    break;
                case KZ:
                    getCurrentView().showSomeText("Тіркеу жабылды.", getCurrentModel());
                    break;
            }
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

    public void addCourseForRegistration(BufferedReader reader) throws IOException {
        System.out.println("Данные курса: ");
        System.out.print("Uuid: ");
        String uuid = reader.readLine();
        System.out.print("Название: ");
        String name = reader.readLine();
        System.out.print("Год: ");
        int year = Integer.parseInt(reader.readLine());
        System.out.print("Вместимость: ");
        int cap = Integer.parseInt(reader.readLine());
        Course newCourse = new Course(uuid, name, null, year, Database.getInstance().getPeriod(), cap);
        database.getInstance().getCourses().add(newCourse);

        switch (CoreSystem.getLanguageMode()) {
            case ENG:
                getCurrentView().showSomeText("Course added successfully.", getCurrentModel());
                break;
            case RUS:
                getCurrentView().showSomeText("Курс успешно добавлен.", getCurrentModel());
                break;
            case KZ:
                getCurrentView().showSomeText("Курс сәтті қосылды.", getCurrentModel());
                break;
        }
    }

    public void removeCourseForRegistration(BufferedReader reader) throws IOException {
        System.out.print("Uuid: ");
        String uuid = reader.readLine();
        boolean removed = database.getInstance().getCourses().removeIf(n -> Objects.equals(n.getUuid(), uuid));

        if (removed) {
            switch (CoreSystem.getLanguageMode()) {
                case ENG:
                    getCurrentView().showSomeText("Course removed successfully.", getCurrentModel());
                    break;
                case RUS:
                    getCurrentView().showSomeText("Курс успешно удален.", getCurrentModel());
                    break;
                case KZ:
                    getCurrentView().showSomeText("Курс сәтті жойылды.", getCurrentModel());
                    break;
            }
        } else {
            switch (CoreSystem.getLanguageMode()) {
                case ENG:
                    getCurrentView().showError("Course with the provided UUID not found.");
                    break;
                case RUS:
                    getCurrentView().showError("Курс с указанным UUID не найден.");
                    break;
                case KZ:
                    getCurrentView().showError("Белгіленген UUID-мен курс табылмады.");
                    break;
            }
        }
    }

    public void addTeacherToCourse(BufferedReader reader) throws IOException {
        System.out.print("Uuid учителя: ");
        String uuidOfTeacher = reader.readLine();
        System.out.print("Uuid курса: ");
        String uuidOfCourse = reader.readLine();
        Teacher teacher = database.getInstance().getTeachers()
                .stream()
                .filter(n -> Objects.equals(n.getUuid(), uuidOfTeacher))
                .findFirst().orElse(null);

        if (teacher == null) {
            switch (CoreSystem.getLanguageMode()) {
                case ENG:
                    getCurrentView().showError("Teacher with the provided UUID not found.");
                    break;
                case RUS:
                    getCurrentView().showError("Учитель с указанным UUID не найден.");
                    break;
                case KZ:
                    getCurrentView().showError("Белгіленген UUID-мен мұғалім табылмады.");
                    break;
            }
            return;
        }

        Course course = database.getInstance().getCourses()
                .stream()
                .filter(n -> Objects.equals(n.getUuid(), uuidOfCourse))
                .findFirst().orElse(null);

        if (course == null) {
            switch (CoreSystem.getLanguageMode()) {
                case ENG:
                    getCurrentView().showError("Course with the provided UUID not found.");
                    break;
                case RUS:
                    getCurrentView().showError("Курс с указанным UUID не найден.");
                    break;
                case KZ:
                    getCurrentView().showError("Белгіленген UUID-мен курс табылмады.");
                    break;
            }
            return;
        }

        course.setTeacher(teacher);
        switch (CoreSystem.getLanguageMode()) {
            case ENG:
                getCurrentView().showSomeText("Teacher added to course successfully.", getCurrentModel());
                break;
            case RUS:
                getCurrentView().showSomeText("Учитель успешно добавлен в курс.", getCurrentModel());
                break;
            case KZ:
                getCurrentView().showSomeText("Мұғалім курсқа сәтті қосылды.", getCurrentModel());
                break;
        }
    }

    public void deleteTeacherFromCourse(BufferedReader reader) throws IOException {
        System.out.print("Uuid учителя: ");
        String uuidOfTeacher = reader.readLine();
        System.out.print("Uuid курса: ");
        String uuidOfCourse = reader.readLine();
        Teacher teacher = database.getInstance().getTeachers()
                .stream()
                .filter(n -> Objects.equals(n.getUuid(), uuidOfTeacher))
                .findFirst().orElse(null);

        if (teacher == null) {
            switch (CoreSystem.getLanguageMode()) {
                case ENG:
                    getCurrentView().showError("Teacher with the provided UUID not found.");
                    break;
                case RUS:
                    getCurrentView().showError("Учитель с указанным UUID не найден.");
                    break;
                case KZ:
                    getCurrentView().showError("Белгіленген UUID-мен мұғалім табылмады.");
                    break;
            }
            return;
        }

        Course course = database.getInstance().getCourses()
                .stream()
                .filter(n -> Objects.equals(n.getUuid(), uuidOfCourse))
                .findFirst().orElse(null);

        if (course == null) {
            switch (CoreSystem.getLanguageMode()) {
                case ENG:
                    getCurrentView().showError("Course with the provided UUID not found.");
                    break;
                case RUS:
                    getCurrentView().showError("Курс с указанным UUID не найден.");
                    break;
                case KZ:
                    getCurrentView().showError("Белгіленген UUID-мен курс табылмады.");
                    break;
            }
            return;
        }

        boolean removed = course.deleteTeacher(teacher);
        if (removed) {
            switch (CoreSystem.getLanguageMode()) {
                case ENG:
                    getCurrentView().showSomeText("Teacher removed from course successfully.", getCurrentModel());
                    break;
                case RUS:
                    getCurrentView().showSomeText("Учитель успешно удален из курса.", getCurrentModel());
                    break;
                case KZ:
                    getCurrentView().showSomeText("Мұғалім курсынан сәтті алынып тасталды.", getCurrentModel());
                    break;
            }
        } else {
            switch (CoreSystem.getLanguageMode()) {
                case ENG:
                    getCurrentView().showError("Teacher is not assigned to the specified course.");
                    break;
                case RUS:
                    getCurrentView().showError("Учитель не назначен на указанный курс.");
                    break;
                case KZ:
                    getCurrentView().showError("Мұғалім көрсетілген курсқа тағайындалмаған.");
                    break;
            }
        }
    }
}
