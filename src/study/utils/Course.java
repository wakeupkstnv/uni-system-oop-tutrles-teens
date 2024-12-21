package study.utils;


import study.Period;
import users.models.Student;

import java.util.HashMap;
import java.util.Objects;
import java.util.Set;
import java.util.Vector;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Course
{
	private HashMap<Lesson,Vector<Student>> lessons;
	private String uuid;
	private String title;

	private int year;

	private Period period;

	private HashMap<Student,Mark> gradebook;

	private int capacity;
	
	public Course(String uuid, String title, int year, Period period, int capacity){
		gradebook = new HashMap<Student, Mark>();
		this.title = title;
		this.uuid = uuid;
		this.year = year;
		this.period = period;
		this.capacity = capacity;
		for (int i = 0; i < 5; i++) {
		Lesson lesson = new Lesson(this.title);
		this.lessons.put(lesson, new Vector<Student>());
		}
	}

	public double getCourseMaxAndMin() {
		double max = 0;
		for (Mark m: gradebook.values()){
			max=Math.max(max,m.calculateAttestation());
		}
		return 0;
	}
	public HashMap<Lesson,Vector<Student>> getLessons() {
		return lessons;
	}
    public Set<Student> getStudents() {
		return gradebook.keySet();
	}

	public String outputBarChart() {
		// TODO implement me
		return "";	
	}
	

	public double getClassAvg() {
		// TODO implement me
		return 0;
	}
	
	public Lesson getLesson(Student student) {
		
        for (Lesson l: lessons.keySet()){
			for (Student s: lessons.get(l)){
				if (s==student){
					return l;
				}
			}
		}
		return null;
	}

	public String displayReport() {
		// TODO implement me
		return "";	
	}
	

	public Vector<Student> getEnrolledStudents() {
		Vector<Student> res = new Vector<Student>();
		for(Student s: gradebook.keySet()){
			res.add(s);
		}
		return res;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Mark getMark(Student student) {
		return gradebook.get(student);
	}
	public String getUuid() {
		return uuid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Course course = (Course) o;
		return year == course.year && capacity == course.capacity && Objects.equals(uuid, course.uuid) && Objects.equals(title, course.title) && period == course.period && Objects.equals(gradebook, course.gradebook);
	}

	@Override
	public int hashCode() {
		return Objects.hash(uuid, title, year, period, gradebook, capacity);
	}

	@Override
	public String toString() {
		return "Course{" +
				"uuid='" + uuid + '\'' +
				", title='" + title + '\'' +
				", year=" + year +
				", period=" + period +
				", gradebook=" + gradebook +
				", capacity=" + capacity +
				'}';
	}
}

