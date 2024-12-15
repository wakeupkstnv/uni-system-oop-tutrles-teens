package Database;
// TODO import all using classes! 
// TODO logic of using DB who can and who can't ( maybe by checking instanceof ) 

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Database implements Serializable {
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
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
	
	private boolean isOpenToReg = true;
	
	private int year;
	
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
    public void addResearcher(Researcher researcher) {
    	researchers.add(researcher);
    	updateTopCitedResearcher();
    	
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
	            if (!request.isSigned()) {
	                pendingRequests.add(request);
	            }
	        }
	        return pendingRequests;
	    }
	
	

}

