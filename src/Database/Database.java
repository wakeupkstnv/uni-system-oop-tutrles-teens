package database;

import papers.Journal;
import papers.ResearchPaper;
import papers.ResearchProject;
import post.News;
import post.Post;
import study.Period;
import study.utils.Course;
import users.models.Researcher;
import users.models.Student;
import users.models.Teacher;
import users.models.User;
import post.Request;

import java.io.Serializable;
import java.util.Objects;
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
    private Vector<Course> courses;

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

    private boolean registationState = false;

    private int year;

    private static Database instance;

    private Database() {
        this.courses = loadVector("./project-x/src/database/data/courses.txt");
        this.logs = loadVector("./project-x/src/database/data/logs.txt");
        this.teachers = loadVector("./project-x/src/database/data/teachers.txt");
        this.students = loadVector("./project-x/src/database/data/students.txt");
        this.users = loadVector("./project-x/src/database/data/users.txt");
        this.researchers = loadVector("./project-x/src/database/data/researchers.txt");
        this.researchPapers = loadVector("./project-x/src/database/data/researchPapers.txt");
        this.researchProjects = loadVector("./project-x/src/database/data/researchProjects.txt");
        this.news = loadVector("./project-x/src/database/data/news.txt");
        this.posts = loadVector("./project-x/src/database/data/posts.txt");
        this.journals = loadVector("./project-x/src/database/data/journals.txt");
        this.requests = loadVector("./project-x/src/database/data/requests.txt");
    }

    public static synchronized Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public Vector<Course> getCourses() {
        this.courses = loadVector("./project-x/src/database/data/courses.txt");
        return courses;
    }

    public void setCourses(Vector<Course> courses) {
        this.courses = courses;
    }

    public Vector<String> getLogs() {
        this.logs = loadVector("./project-x/src/database/data/logs.txt");
        return logs;
    }

    public Vector<Teacher> getTeachers() {
        this.teachers = loadVector("./project-x/src/database/data/teachers.txt");
        return teachers;
    }

    public Vector<User> getUsers() {
        this.users = loadVector("./project-x/src/database/data/users.txt");
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
        this.students = loadVector("./project-x/src/database/data/students.txt");
        return students;
    }

    public Vector<Researcher> getResearchers() {
        this.researchers = loadVector("./project-x/src/database/data/researchers.txt");
        return researchers;
    }

    public Vector<ResearchPaper> getResearchPapers() {
        this.researchPapers = loadVector("./project-x/src/database/data/researchPapers.txt");
        return researchPapers;
    }

    public Vector<ResearchProject> getResearchProjects() {
        this.researchProjects = loadVector("./project-x/src/database/data/researchProjects.txt");
        return researchProjects;
    }

    public Vector<News> getNews() {
        this.news = loadVector("./project-x/src/database/data/news.txt");
        return news;
    }

    public Vector<Post> getPosts() {
        this.posts = loadVector("./project-x/src/database/data/posts.txt");
        return posts;
    }

    public void setPosts(Vector<Post> posts) {
        this.posts = posts;
    }

    public Period getPeriod() {
        return period;
    }

    public Vector<Journal> getJournals() {
        this.journals = loadVector("./project-x/src/database/data/journals.txt");
        return journals;
    }

    public Vector<Request> getRequests() {
        this.requests = loadVector("./project-x/src/database/data/requests.txt");
        return requests;
    }

    public Boolean getRegistationState() {
        return this.registationState;
    }

    // Методы для users
    public void addUser(User user) {
        this.users = loadVector("./project-x/src/database/data/users.txt");
        users.add(user);
        saveVector(users, "./project-x/src/database/data/users.txt");
    }

    public void removeUser(User user) {
        this.users = loadVector("./project-x/src/database/data/users.txt");
        users.remove(user);
        saveVector(users, "./project-x/src/database/data/users.txt");
    }

    // Методы для courses
    public void addCourse(Course course) {
        this.courses = loadVector("./project-x/src/database/data/courses.txt");
        courses.add(course);
        saveVector(courses, "./project-x/src/database/data/courses.txt");
    }

    public void removeCourse(Course course) {
        this.courses = loadVector("./project-x/src/database/data/courses.txt");
        courses.remove(course);
        saveVector(courses, "./project-x/src/database/data/courses.txt");
    }

    // Методы для logs
    public void addLog(String log) {
        this.logs = loadVector("./project-x/src/database/data/logs.txt");
        logs.add(log);
        saveVector(logs, "./project-x/src/database/data/logs.txt");
    }

    public void removeLog(String log) {
        this.logs = loadVector("./project-x/src/database/data/logs.txt");
        logs.remove(log);
        saveVector(logs, "./project-x/src/database/data/logs.txt");
    }

    // Методы для teachers
    public void addTeacher(Teacher teacher) {
        this.teachers = loadVector("./project-x/src/database/data/teachers.txt");
        teachers.add(teacher);
        saveVector(teachers, "./project-x/src/database/data/teachers.txt");
    }

    public void removeTeacher(Teacher teacher) {
        this.teachers = loadVector("./project-x/src/database/data/teachers.txt");
        teachers.remove(teacher);
        saveVector(teachers, "./project-x/src/database/data/teachers.txt");
    }

    // Методы для students
    public void addStudent(Student student) {
        this.students = loadVector("./project-x/src/database/data/students.txt");
        students.add(student);
        saveVector(students, "./project-x/src/database/data/students.txt");
    }

    public void removeStudent(Student student) {
        this.students = loadVector("./project-x/src/database/data/students.txt");
        students.remove(student);
        saveVector(students, "./project-x/src/database/data/students.txt");
    }

    // Методы для researchers
    public void addResearcher(Researcher researcher) {
        this.researchers = loadVector("./project-x/src/database/data/researchers.txt");
        researchers.add(researcher);
        saveVector(researchers, "./project-x/src/database/data/researchers.txt");
    }

    public void removeResearcher(Researcher researcher) {
        this.researchers = loadVector("./project-x/src/database/data/researchers.txt");
        researchers.remove(researcher);
        saveVector(researchers, "./project-x/src/database/data/researchers.txt");
    }

    // Методы для researchPapers
    public void addResearchPaper(ResearchPaper paper) {
        this.researchPapers = loadVector("./project-x/src/database/data/researchPapers.txt");
        researchPapers.add(paper);
        saveVector(researchPapers, "./project-x/src/database/data/researchPapers.txt");
    }

    public void removeResearchPaper(ResearchPaper paper) {
        this.researchPapers = loadVector("./project-x/src/database/data/researchPapers.txt");
        researchPapers.remove(paper);
        saveVector(researchPapers, "./project-x/src/database/data/researchPapers.txt");
    }

    // Методы для researchProjects
    public void addResearchProject(ResearchProject project) {
        this.researchProjects = loadVector("./project-x/src/database/data/researchProjects.txt");
        researchProjects.add(project);
        saveVector(researchProjects, "./project-x/src/database/data/researchProjects.txt");
    }

    public void removeResearchProject(ResearchProject project) {
        this.researchProjects = loadVector("./project-x/src/database/data/researchProjects.txt");
        researchProjects.remove(project);
        saveVector(researchProjects, "./project-x/src/database/data/researchProjects.txt");
    }

    // Методы для news
    public void addNews(News newsItem) {
        this.news = loadVector("./project-x/src/database/data/news.txt");
        news.add(newsItem);
        saveVector(news, "./project-x/src/database/data/news.txt");
    }

    public void removeNews(News newsItem) {
        this.news = loadVector("./project-x/src/database/data/news.txt");
        news.remove(newsItem);
        saveVector(news, "./project-x/src/database/data/news.txt");
    }

    // Методы для posts
    public void addPost(Post post) {
        this.posts = loadVector("./project-x/src/database/data/posts.txt");
        posts.add(post);
        saveVector(posts, "./project-x/src/database/data/posts.txt");
    }

    public void removePost(Post post) {
        this.posts = loadVector("./project-x/src/database/data/posts.txt");
        posts.remove(post);
        saveVector(posts, "./project-x/src/database/data/posts.txt");
    }

    // Методы для requests
    public void addRequest(Request request) {
        this.requests = loadVector("./project-x/src/database/data/requests.txt");
        requests.add(request);
        saveVector(requests, "./project-x/src/database/data/requests.txt");
    }

    public void removeRequest(Request request) {
        this.requests = loadVector("./project-x/src/database/data/requests.txt");
        requests.remove(request);
        saveVector(requests, "./project-x/src/database/data/requests.txt");
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

    public void setRegistationState(boolean registationState) {
        this.registationState = registationState;
    }
}
