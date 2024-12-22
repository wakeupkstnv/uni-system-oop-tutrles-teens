package users.controller;
import database.Database;
import post.Request;
import post.Urgency;
import study.utils.Course;
import study.utils.Mark;
import users.exceptions.UserNotFoundException;
import users.models.Dean;
import users.models.Student;
import users.models.Teacher;
import users.view.TeacherView;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;
import java.util.Vector;

public class TeacherController<Model extends Teacher, View extends TeacherView> extends EmployeeController<Teacher, TeacherView> {

    public TeacherController(Model currentModel, View currentView) {
        super(currentModel, currentView);
    }
    TeacherView currentView = (TeacherView) getCurrentView();


    /**
     * Просмотр списка студентов, которых преподает преподаватель.
     */
    public void viewStudents() {
        Vector<Student> students = new Vector<>();
        for (Course course : Database.getInstance().getCourses()) {  // Изменили на Database.getInstance()
            if (course.getTeacher() == this.getCurrentModel()) {
                students.addAll(course.getStudents());
            }
        }
        // for example;
        students.add(new Student("12", "tamir", "kustanayev", "t_kustanayev@kbtu.kz", "t_kustanayev", new Date(), "123"));
        currentView.showStudents(students);
    }

    /**
     * Назначение оценки студенту.
     *
     * @param reader BufferedReader для чтения ввода пользователя.
     * @throws IOException
     */
    public void assignGrades(BufferedReader reader) throws IOException {
        System.out.print("Введите UUID студента: ");
        String studentUuid = reader.readLine();
        System.out.print("Введите название курса: ");
        String courseName = reader.readLine();
        System.out.print("Введите оценку: ");
        String gradeStr = reader.readLine();
        System.out.print("Введите куда:\n1) первая аттестация\n2)Вторая аттестация 3)Экзамен");
        String choiceOfPeriod = reader.readLine();

        try {
            int grade = Integer.parseInt(gradeStr);
            Student student = database.getInstance().getStudents().stream()
                    .filter(s -> s.getUuid().equals(studentUuid))
                    .findFirst()
                    .orElseThrow(() -> new UserNotFoundException("Студент не найден."));

            Optional<Course> courseOpt = database.getInstance().getCourses().stream()
                    .filter(c -> c.getTitle().equalsIgnoreCase(courseName))
                    .findFirst();

            if (!courseOpt.isPresent()) {
                System.out.println("Курс не найден.");
                return;
            }

            Course course = courseOpt.get();
            if (!course.getTeacher().equals(this.currentModel) ) {
                System.out.println("Вы не являетесь преподавателем этого курса.");
                return;
            }

            Mark mark = course.getGradebook().get(student);
            if (choiceOfPeriod.equals("1")){
                mark.addPointsForFirstAttestation((float) Double.parseDouble(gradeStr));
            } else if(choiceOfPeriod.equals("2")){
                mark.addPointsForFirstAttestation((float) Double.parseDouble(gradeStr));
            } else {
                mark.putPointToFinal((float) Double.parseDouble(gradeStr));
            }

            database.getInstance().addLog("Преподаватель " + currentModel.getFirstName() + " назначил оценку " + grade + " студенту " + student.getFirstName() + " по курсу " + course.getTitle());

            System.out.println("Оценка успешно назначена.");
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат оценки.");
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Отправка жалобы декану.
     *
     * @param reader BufferedReader для чтения ввода пользователя.
     * @throws IOException
     */
    public void sendComplaint(BufferedReader reader) throws IOException {
        System.out.print("Введите UUID студента для жалобы: ");
        String studentUuid = reader.readLine();
        System.out.print("Введите текст жалобы: ");
        String complaintText = reader.readLine();
        System.out.print("Выберите уровень срочности (1: LOW, 2: MEDIUM, 3: HIGH): ");
        String urgencyStr = reader.readLine();

        Urgency urgency;
        switch (urgencyStr) {
            case "1":
                urgency = Urgency.LOW;
                break;
            case "2":
                urgency = Urgency.MEDIUM;
                break;
            case "3":
                urgency = Urgency.HIGH;
                break;
            default:
                System.out.println("Неверный выбор уровня срочности.");
                return;
        }

        Student student = database.getInstance().getStudents().stream()
                .filter(s -> s.getUuid().equals(studentUuid))
                .findFirst()
                .orElse(null);

        if (student == null) {
            System.out.println("Студент не найден.");
            return;
        }

        // Найти декана факультета студента
        Dean dean = database.getInstance().getFacultyDean().get(student.getFaculty());
        if (dean == null) {
            System.out.println("Декан факультета не назначен.");
            return;
        }

        // Создать запрос (жалобу)
        Request complaint = new Request(currentModel, complaintText, new java.util.Date(), urgency, "Complaint about student: " + student.getFirstName());

        // Отправить жалобу декану
        dean.addRequest(complaint);
        database.getInstance().addRequest(complaint);

        database.getInstance().addLog("Преподаватель " + currentModel.getFirstName() + " отправил жалобу декану по студенту " + student.getFirstName());

        System.out.println("Жалоба успешно отправлена декану.");
    }
}
