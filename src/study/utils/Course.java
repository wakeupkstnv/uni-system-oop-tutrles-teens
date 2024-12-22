package study.utils;

import study.Period;
import users.models.Student;

import java.util.HashMap;
import java.util.Objects;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

/**
 * Represents a course with lessons, students, and a gradebook.
 */
public class Course {
	private Map<Lesson, List<Student>> lessons;
	private String uuid;
	private String title;
	private int year;
	private Period period;
	private Map<Student, Mark> gradebook;
	private int capacity;

	/**
	 * Constructs a new Course.
	 *
	 * @param uuid     Unique identifier for the course.
	 * @param title    Title of the course.
	 * @param year     Year the course is offered.
	 * @param period   Period during which the course is held.
	 * @param capacity Maximum number of students allowed.
	 */
	public Course(String uuid, String title, int year, Period period, int capacity) {
		this.uuid = uuid;
		this.title = title;
		this.year = year;
		this.period = period;
		this.capacity = capacity;
		this.gradebook = new HashMap<>();
		this.lessons = new HashMap<>();

		// Initialize lessons
		for (int i = 0; i < 5; i++) {
			Lesson lesson = new Lesson(this.title + " - Lesson " + (i + 1));
			this.lessons.put(lesson, new ArrayList<>());
		}
	}

	/**
	 * Calculates and returns the maximum and minimum attestation marks in the course.
	 *
	 * @return An array where index 0 is the maximum mark and index 1 is the minimum mark.
	 */
	public double[] getCourseMaxAndMin() {
		double max = Double.MIN_VALUE;
		double min = Double.MAX_VALUE;

		for (Mark m : gradebook.values()) {
			double attestation = m.calculateAttestation();
			if (attestation > max) {
				max = attestation;
			}
			if (attestation < min) {
				min = attestation;
			}
		}

		// Handle case when gradebook is empty
		if (gradebook.isEmpty()) {
			max = 0;
			min = 0;
		}

		return new double[]{max, min};
	}

	/**
	 * Returns the gradebook.
	 *
	 * @return A map of students to their marks.
	 */
	public Map<Student, Mark> getGradebook() {
		return gradebook;
	}

	/**
	 * Returns the lessons.
	 *
	 * @return A map of lessons to the list of enrolled students.
	 */
	public Map<Lesson, List<Student>> getLessons() {
		return lessons;
	}

	/**
	 * Returns the set of enrolled students.
	 *
	 * @return A set of students.
	 */
	public Set<Student> getStudents() {
		return gradebook.keySet();
	}

	/**
	 * Outputs a bar chart representation of the grade distribution.
	 *
	 * @return A string representing the bar chart.
	 */
//	public String outputBarChart() {
//		StringBuilder chart = new StringBuilder();
//		chart.append("Grade Distribution:\n");
//
//		// Example implementation: Count number of students per mark
//		Map<Integer, Integer> distribution = new HashMap<>();
//		for (Mark mark : gradebook.values()) {
//			int grade = mark.getGrade(); // Assuming Mark has a getGrade() method
//			distribution.put(grade, distribution.getOrDefault(grade, 0) + 1);
//		}
//
//		for (Map.Entry<Integer, Integer> entry : distribution.entrySet()) {
//			chart.append("Grade ").append(entry.getKey()).append(": ");
//			for (int i = 0; i < entry.getValue(); i++) {
//				chart.append("*");
//			}
//			chart.append(" (").append(entry.getValue()).append(")\n");
//		}
//
//		return chart.toString();
//	}

	/**
	 * Calculates and returns the class average.
	 *
	 * @return The average mark of the class.
	 */
	public double getClassAvg() {
		if (gradebook.isEmpty()) {
			return 0;
		}

		double total = 0;
		for (Mark mark : gradebook.values()) {
			total += mark.calculateAttestation();
		}

		return total / gradebook.size();
	}

	/**
	 * Retrieves the lesson a student is enrolled in.
	 *
	 * @param student The student to find.
	 * @return The lesson the student is enrolled in, or null if not found.
	 */
	public Lesson getLesson(Student student) {
		for (Map.Entry<Lesson, List<Student>> entry : lessons.entrySet()) {
			if (entry.getValue().contains(student)) {
				return entry.getKey();
			}
		}
		return null;
	}

	/**
	 * Displays a report of the course.
	 *
	 * @return A string report of the course.
	 */
	public String displayReport() {
		StringBuilder report = new StringBuilder();
		report.append("Course Report:\n");
		report.append("UUID: ").append(uuid).append("\n");
		report.append("Title: ").append(title).append("\n");
		report.append("Year: ").append(year).append("\n");
		report.append("Period: ").append(period).append("\n");
		report.append("Capacity: ").append(capacity).append("\n");
		report.append("Enrolled Students: ").append(getEnrolledStudents().size()).append("\n");
		report.append("Class Average: ").append(getClassAvg()).append("\n");

		double[] maxMin = getCourseMaxAndMin();
		report.append("Max Mark: ").append(maxMin[0]).append("\n");
		report.append("Min Mark: ").append(maxMin[1]).append("\n");

		return report.toString();
	}

	/**
	 * Returns a list of enrolled students.
	 *
	 * @return A list of students.
	 */
	public List<Student> getEnrolledStudents() {
		return new ArrayList<>(gradebook.keySet());
	}

	// Getter and Setter methods

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

	public int getYear() {
		return year;
	}

	public Period getPeriod() {
		return period;
	}

	public int getCapacity() {
		return capacity;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Course course = (Course) o;
		return year == course.year &&
				capacity == course.capacity &&
				Objects.equals(uuid, course.uuid) &&
				Objects.equals(title, course.title) &&
				period == course.period &&
				Objects.equals(gradebook, course.gradebook) &&
				Objects.equals(lessons, course.lessons);
	}

	@Override
	public int hashCode() {
		return Objects.hash(uuid, title, year, period, gradebook, capacity, lessons);
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
				", lessons=" + lessons +
				'}';
	}
}
