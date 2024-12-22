package database;

import papers.Journal;
import papers.ResearchPaper;
import papers.ResearchProject;
import post.News;
import post.Post;
import study.Period;
import study.utils.Course;
import users.Faculty;
import users.exceptions.UserNotFoundException;
import users.models.*;
import post.Request;

import java.io.Serializable;
import java.util.HashMap;



import java.util.Vector;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 */

public class Database implements Serializable{
    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     */
    static final String PATH = "./project-x/src/database/data/";
    private Vector<Course> courses;

    private HashMap<User, String> userPasswords;

    private HashMap<Student, Vector<Course>> studentCourses;

    private HashMap<Faculty, Dean> facultyDean;

    private Vector<String> logs;

    private Vector<Teacher> teachers;

    private Vector<Student> students;

    private Vector<User> users;

    private Vector<Researcher> researchers;

    private Vector<ResearchPaper> researchPapers;

    private Vector<ResearchProject> researchProjects;

    private Vector<News> news;

    private Vector<Post> posts;

    private Period period;

    private Vector<Journal> journals;

    private Vector<Request> requests;

    private Researcher topCitedResearcher;

    private Vector<Employee> employees;

    private boolean registationState = false;

    private int year=2024;

    private static Database instance;

    private Database() {
        this.courses = loadVector(PATH + "courses.txt");
        this.logs = loadVector(PATH + "logs.txt");
        this.teachers = loadVector(PATH + "teachers.txt");
        this.students = loadVector(PATH + "students.txt");
        this.users = loadVector(PATH + "users.txt");
        this.researchers = loadVector(PATH + "researchers.txt");
        this.researchPapers = loadVector(PATH + "researchPapers.txt");
        this.researchProjects = loadVector(PATH + "researchProjects.txt");
        this.news = loadVector(PATH + "news.txt");
        this.posts = loadVector(PATH + "posts.txt");
        this.journals = loadVector(PATH + "journals.txt");
        this.requests = loadVector(PATH + "requests.txt");
        this.employees = loadVector(PATH + "employees.txt");
        this.userPasswords = loadHashMap(PATH + "userPasswords.txt");
        this.facultyDean = loadHashMap(PATH + "facultyDean .txt");
    }

    public HashMap<Faculty, Dean> getFacultyDean() {
        this.facultyDean = loadHashMap(PATH + "facultyDean .txt");
        return facultyDean;
    }

    public void addDeanToFaculty(Dean dean, Faculty faculty){
        this.facultyDean = loadHashMap(PATH + "facultyDean .txt");
        facultyDean.put(faculty, dean);
        saveHashMap(facultyDean, PATH + "facultyDean .txt");
    }

    public HashMap<User, String> getUserPasswords() {
        this.userPasswords = loadHashMap(PATH + "userPasswords.txt");
        return userPasswords;
    }

    public void addUserPassword(User user, String password){
        this.userPasswords = loadHashMap(PATH + "userPasswords.txt");
        this.userPasswords.put(user, password);
        saveHashMap(this.userPasswords, PATH + "userPasswords.txt");
    }

    public Vector<Employee> getEmployees() {
        this.employees = loadVector(PATH + "employees.txt");
        return employees;
    }

    public void addEmployees(Employee e){
        this.employees = loadVector(PATH + "employees.txt");
        this.employees.add(e);
        saveVector(this.employees, PATH + "employees.txt");
    }

    public static synchronized Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public Vector<Course> getCourses() {
        this.courses = loadVector(PATH + "courses.txt");
        return courses;
    }

    public HashMap<Student, Vector<Course>> getStudentCourses() {
        this.studentCourses = loadHashMap(PATH + "studentCourses.txt");
        return studentCourses;
    }

    public void addCourseToStudent(Student student, Course course){
        this.studentCourses = loadHashMap(PATH + "studentCourses.txt");

        if (studentCourses.get(student) == null){
            Vector<Course> c = new Vector<Course>();
            c.add(course);
            studentCourses.put(student, c);
        }  else{
            studentCourses.put(student, courses);
        }
    }

    public void addCourseToStudent(String studentUuid, Course course){
        this.studentCourses = loadHashMap(PATH + "studentCourses.txt");

        Student student = getStudents()
                .stream()
                .filter(n->n.getUuid().equals(studentUuid))
                .findFirst()
                .orElse(null);

        if (student == null) return; //TODO: add exceptions!!!

        if (studentCourses.get(student) == null){
            Vector<Course> c = new Vector<Course>();
            c.add(course);
            studentCourses.put(student, c);
        }  else{
            studentCourses.put(student, courses);
        }

        saveHashMap(studentCourses,PATH + "studentCourses.txt");
    }

    public void addCourseToStudent(String studentUuid, String uuidCourse){
        this.studentCourses = loadHashMap(PATH + "studentCourses.txt");

        Student student = this.getStudents()
                .stream()
                .filter(n->n.getUuid().equals(studentUuid))
                .findFirst()
                .orElse(null);

        Course course = this.getCourses()
                .stream()
                .filter(n->n.getUuid().equals(uuidCourse))
                .findFirst()
                .orElse(null);

        if (student == null) return; //TODO: add exceptions!!!
        if (course == null) return; //TODO: add excrption!!!!

        if (studentCourses.get(student) == null){
            Vector<Course> c = new Vector<Course>();
            c.add(course);
            studentCourses.put(student, c);
        }  else{
            studentCourses.put(student, courses);
        }

        saveHashMap(this.studentCourses, PATH + "studentCourses.txt");

    }

    public Researcher getTopCitedResearcher() {
        return topCitedResearcher;
    }

    public void setCourses(Vector<Course> courses) {
        this.courses = courses;
    }

    public Vector<String> getLogs() {
        this.logs = loadVector(PATH + "logs.txt");
        return logs;
    }

    public Vector<Teacher> getTeachers() {
        this.teachers = loadVector(PATH + "teachers.txt");
        return teachers;
    }

    public Vector<User> getUsers() {
        this.users = loadVector(PATH + "users.txt");
        return users;
    }

    public Researcher findResearcher(String uuid){
        for(Researcher r: this.researchers){
            if(r.getUuid().equals(uuid)){
                return r;
            }
        }
        return null;
    }

    public Vector<Student> getStudents() {
        this.students = loadVector(PATH + "students.txt");
        return students;
    }

    public Vector<Researcher> getResearchers() {
        this.researchers = loadVector(PATH + "researchers.txt");
        return researchers;
    }

    public Vector<ResearchPaper> getResearchPapers() {
        this.researchPapers = loadVector(PATH + "researchPapers.txt");
        return researchPapers;
    }

    public Vector<ResearchProject> getResearchProjects() {
        this.researchProjects = loadVector(PATH + "researchProjects.txt");
        return researchProjects;
    }

    public Vector<News> getNews() {
        this.news = loadVector(PATH + "news.txt");
        return news;
    }

    public Vector<Post> getPosts() {
        this.posts = loadVector(PATH + "posts.txt");
        return posts;
    }

    public void setPosts(Vector<Post> posts) {
        this.posts = posts;
    }

    public Period getPeriod() {
        return period;
    }

    public Vector<Journal> getJournals() {
        this.journals = loadVector(PATH + "journals.txt");
        return journals;
    }

    public Vector<Request> getRequests() {
        this.requests = loadVector(PATH + "requests.txt");
        return requests;
    }

    public Boolean getRegistationState() {
        return this.registationState;
    }

    // Методы для users
    public void addUser(User user) {
        this.users = loadVector(PATH + "users.txt");
        users.add(user);
        saveVector(users, PATH + "users.txt");
    }

    public void removeUser(User user) {
        this.users = loadVector(PATH + "users.txt");
        users.remove(user);
        saveVector(users, PATH + "users.txt");
    }

    // Методы для courses
    public void addCourse(Course course) {
        this.courses = loadVector(PATH + "courses.txt");
        courses.add(course);
        saveVector(courses, PATH + "courses.txt");
    }

    public void removeCourse(Course course) {
        this.courses = loadVector(PATH + "courses.txt");
        courses.remove(course);
        saveVector(courses, PATH + "courses.txt");
    }

    public void addLog(String log) {
        this.logs = loadVector(PATH + "logs.txt");
        logs.add(log);
        saveVector(logs, PATH + "logs.txt");
    }

    public void removeLog(String log) {
        this.logs = loadVector(PATH + "logs.txt");
        logs.remove(log);
        saveVector(logs, PATH + "logs.txt");
    }

    public void addTeacher(Teacher teacher) {
        this.teachers = loadVector(PATH + "teachers.txt");
        teachers.add(teacher);
        saveVector(teachers, PATH + "teachers.txt");
    }

    public void removeTeacher(Teacher teacher) {
        this.teachers = loadVector(PATH + "teachers.txt");
        teachers.remove(teacher);
        saveVector(teachers, PATH + "teachers.txt");
    }

    public void addStudent(Student student) {
        this.students = loadVector(PATH + "students.txt");
        students.add(student);
        saveVector(students, PATH + "students.txt");
    }

    public void removeStudent(Student student) {
        this.students = loadVector(PATH + "students.txt");
        students.remove(student);
        saveVector(students, PATH + "students.txt");
    }

    public void addResearcher(Researcher researcher) {
        this.researchers = loadVector(PATH + "researchers.txt");
        researchers.add(researcher);
        saveVector(researchers, PATH + "researchers.txt");
    }

    public void removeResearcher(Researcher researcher) {
        this.researchers = loadVector(PATH + "researchers.txt");
        researchers.remove(researcher);
        saveVector(researchers, PATH + "researchers.txt");
    }

    public void addResearchPaper(ResearchPaper paper) {
        this.researchPapers = loadVector(PATH + "researchPapers.txt");
        researchPapers.add(paper);
        saveVector(researchPapers, PATH + "researchPapers.txt");
    }

    public void removeResearchPaper(ResearchPaper paper) {
        this.researchPapers = loadVector(PATH + "researchPapers.txt");
        researchPapers.remove(paper);
        saveVector(researchPapers, PATH + "researchPapers.txt");
    }

    public void addResearchProject(ResearchProject project) {
        this.researchProjects = loadVector(PATH + "researchProjects.txt");
        researchProjects.add(project);
        saveVector(researchProjects, PATH + "researchProjects.txt");
    }

    public void removeResearchProject(ResearchProject project) {
        this.researchProjects = loadVector(PATH + "researchProjects.txt");
        researchProjects.remove(project);
        saveVector(researchProjects, PATH + "researchProjects.txt");
    }

    public void addNews(News newsItem) {
        this.news = loadVector(PATH + "news.txt");
        news.add(newsItem);
        saveVector(news, PATH + "news.txt");
    }

    public void removeNews(News newsItem) {
        this.news = loadVector(PATH + "news.txt");
        news.remove(newsItem);
        saveVector(news, PATH + "news.txt");
    }

    public void addPost(Post post) {
        this.posts = loadVector(PATH + "posts.txt");
        posts.add(post);
        saveVector(posts, PATH + "posts.txt");
    }

    public void removePost(Post post) {
        this.posts = loadVector(PATH + "posts.txt");
        posts.remove(post);
        saveVector(posts, PATH + "posts.txt");
    }

    // Методы для requests
    public void addRequest(Request request) {
        this.requests = loadVector(PATH + "requests.txt");
        requests.add(request);
        saveVector(requests, PATH + "requests.txt");
    }

    public void removeRequest(Request request) {
        this.requests = loadVector(PATH + "requests.txt");
        requests.remove(request);
        saveVector(requests, PATH + "requests.txt");
    }

    @SuppressWarnings("unchecked")
    private <T> Vector<T> loadVector(String filePath) {
        Object deserializedObject = ReaderWriter.deserialize(filePath);
        if (deserializedObject instanceof Vector) {
            return (Vector<T>) deserializedObject;
        }
        return new Vector<>();
    }

    private <T> void saveVector(Vector<T> vector, String filePath) {
        if (ReaderWriter.serialize(vector, filePath)) {
            System.out.println("Данные успешно сохранены в " + filePath);
        } else {
            System.out.println("Ошибка при сохранении данных в " + filePath);
        }
    }

    private <F, S> HashMap<F, S> loadHashMap(String filePath) {
        Object deserializedObject = ReaderWriter.deserialize(filePath);
        if (deserializedObject instanceof HashMap) {
            return (HashMap<F, S>) deserializedObject;
        }
        return new HashMap<>();
    }


    private <F, S> void saveHashMap(HashMap<F, S> hashMap, String filePath) {
        if (ReaderWriter.serialize(hashMap, filePath)) {
            System.out.println("Данные успешно сохранены в " + filePath);
        } else {
            System.out.println("Ошибка при сохранении данных в " + filePath);
        }
    }
    public void setRegistationState(boolean registationState) {
        this.registationState = registationState;
    }
}
