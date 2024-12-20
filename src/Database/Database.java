
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

import java.util.Objects;
import java.util.Vector;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 */

public class Database implements Serializable {
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

	private Database() {}

	public static synchronized Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    // Getters and Setters
    public Vector<Course> getCourses() {
        return courses;
    }

    public void setCourses(Vector<Course> courses) {
        this.courses = courses;
    }

    public Vector<String> getLogs() {
        return logs;
    }

    public Vector<Teacher> getTeachers() {
        return teachers;
    }


    public Vector<User> getUsers() {
        return users;
    }

    public Vector<Researcher> getResearchers() {
        return researchers;
    }

    public Vector<ResearchPaper> getResearchPapers() {
        return researchPapers;
    }

    public Vector<ResearchProject> getResearchProjects() {
        return researchProjects;
    }

    public Vector<News> getNews() {
        return news;
    }

    public Vector<Post> getPosts() {
        return posts;
    }

    public void setPosts(Vector<Post> posts) {
        this.posts = posts;
    }

    public Period getPeriod() {
        return period;
    }


    public Vector<Journal> getJournals() {
        return journals;
    }


    public Vector<Request> getRequests() {
        return requests;
    }

    public Boolean getRegistationState() {return this.registationState;}

    public void setRegistationState(Boolean registationState) {this.registationState = registationState;}
    public Researcher getTopCitedResearcher() {
        return topCitedResearcher;
    }

    public void setTopCitedResearcher(Researcher topCitedResearcher) {
        this.topCitedResearcher = topCitedResearcher;
    }


    public void addLog(String log) {
        logs.add(log);
    }

    public Vector<String> getAllLogs() {
        return logs;
    }

    // Courses management
    public void addCourse(Course course) {
        courses.add(course);
    }

    public void removeCourse(Course course) {
        courses.remove(course);
    }


    public Course findCourseByTitle(String title) {
        for (Course course : courses) {
            if (course.getTitle().equals(title)) {
                return course;
            }
        }
        return null;
    }

    // Users management
    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public User findUserById(String uuid) {
        for (User user : users) {
            if (Objects.equals(user.getUuid(), uuid)) {
                return user;
            }
        }
        return null;
    }
    
 // Research papers management
    public void addResearcher(Researcher researcher) {
    	researchers.add(researcher);
    	updateTopCitedResearcher();
    	
    }
    public Researcher findResearcher(String uuid){
            for(Researcher r: this.researchers){
                if(r.getUuid().equals(uuid)){
                    return r;
                }
            }
            return null;
     }
    public void removeResearcher(Researcher researcher) {
    	researchers.remove(researcher);
    	updateTopCitedResearcher();
    	
    }

    public void addStudent(Student student) {
        if (!students.contains(student)) {
            students.add(student);
        }
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public void addTeacher(Teacher teacher) {
        if (!teachers.contains(teacher)) {
            teachers.add(teacher);
        }
    }

    public void removeTeacher(Teacher teacher) {
        teachers.remove(teacher);
    }

    public void addResearchPaper(ResearchPaper paper) {
        researchPapers.add(paper);
    }

    public void removeResearchPaper(ResearchPaper paper) {
        researchPapers.remove(paper);
    }

    public Vector<ResearchPaper> getResearchPapersByAuthor(Researcher researcher) {
        Vector<ResearchPaper> papersByAuthor = new Vector<>();
        
        for (ResearchPaper paper : researchPapers) {
            if (paper.getAuthors().contains(researcher)) {
                papersByAuthor.add(paper);
            }
        }
        return papersByAuthor;
    }

    public void addNews(News news){
        getNews().add(news);
    }

    public void editNews(String topic){

    }
    private void updateTopCitedResearcher() {
        Researcher topCited = null;
        int maxCitations = 0;

        for (ResearchPaper paper : researchPapers) {
            for (Researcher author : paper.getAuthors()) {
                int citations = paper.getCitations(); 
                if (citations > maxCitations) {
                    maxCitations = citations;
                    topCited = author;
                }
            }
        }

        this.topCitedResearcher = topCited;
    }

    // Requests management
    public void addRequest(Request request) {
        requests.add(request);
    }

    public void removeRequest(Request request) {
        requests.remove(request);
    }

	public Vector<Request> getPendingRequests() {
        Vector<Request> pendingRequests = new Vector<>();
        for (Request request : requests) {
	            if (!request.getSigned()) {
	                pendingRequests.add(request);
	            }
	        }
	        return pendingRequests;
    }



	private static Database databaseInstance;

	public static Database getInstanceOfDatabse(){
		if (Database.databaseInstance == null)
			return new Database();
		return new Database();
	}
}

