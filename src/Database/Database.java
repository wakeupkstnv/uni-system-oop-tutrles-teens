package Database;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Database implements Serializable {
    private static final long serialVersionUID = 1L;
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	private Vector<Course> courses;
	
	private Vector<String> logs;
	
	private Vector<Teacher> teachers;
	
	private Vector<User> users;
	
	private Vector<CanBecomeResearcher> researchers;
	
	private Vector<ResearchPaper> researchPapers;
	
	private Vector<ResearchProject> researchProjects;
	
	private Vector<News> news;
	
	private Vector<Post> posts;
	
	private Period period;
	
	private Vector<Journal> journals;
	
	private Vector<Request> requests;
	
	private Researcher topCitedResearcher;
	
	private boolean isOpenToReg = true;
	
	
	
	private static Database instance;
	
	
	// Singletone pattern
	
	private Database() {
        courses = new Vector<>();
        logs = new Vector<>();
        teachers = new Vector<>();
        users = new Vector<>();
        researchers = new Vector<>();
        researchPapers = new Vector<>();
        researchProjects = new Vector<>();
        news = new Vector<>();
        posts = new Vector<>();
        journals = new Vector<>();
        requests = new Vector<>();
    }

	
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

    public void setLogs(Vector<String> logs) {
        this.logs = logs;
    }

    public Vector<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Vector<Teacher> teachers) {
        this.teachers = teachers;
    }

    public Vector<User> getUsers() {
        return users;
    }

    public void setUsers(Vector<User> users) {
        this.users = users;
    }

    public Vector<CanBecomeResearcher> getResearchers() {
        return researchers;
    }

    public void setResearchers(Vector<CanBecomeResearcher> researchers) {
        this.researchers = researchers;
    }

    public Vector<ResearchPaper> getResearchPapers() {
        return researchPapers;
    }

    public void setResearchPapers(Vector<ResearchPaper> researchPapers) {
        this.researchPapers = researchPapers;
    }

    public Vector<ResearchProject> getResearchProjects() {
        return researchProjects;
    }

    public void setResearchProjects(Vector<ResearchProject> researchProjects) {
        this.researchProjects = researchProjects;
    }

    public Vector<News> getNews() {
        return news;
    }

    public void setNews(Vector<News> news) {
        this.news = news;
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

    public void setPeriod(Period period) {
        this.period = period;
    }

    public Vector<Journal> getJournals() {
        return journals;
    }

    public void setJournals(Vector<Journal> journals) {
        this.journals = journals;
    }

    public Vector<Request> getRequests() {
        return requests;
    }

    public void setRequests(Vector<Request> requests) {
        this.requests = requests;
    }

    public Researcher getTopCitedResearcher() {
        return topCitedResearcher;
    }

    public void setTopCitedResearcher(Researcher topCitedResearcher) {
        this.topCitedResearcher = topCitedResearcher;
    }

    public boolean isOpenToReg() {
        return isOpenToReg;
    }

    public void setOpenToReg(boolean isOpenToReg) {
        this.isOpenToReg = isOpenToReg;
    }
	
    // Additional Methods

    // Logs management
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

    public Course findCourseByName(String name) {
        for (Course course : courses) {
            if (course.getName().equals(name)) {
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

    public User findUserById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    // Research papers management
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
	            if (request.isSigned()) {
	                pendingRequests.add(request);
	            }
	        }
	        return pendingRequests;
	    }
	
	

}

