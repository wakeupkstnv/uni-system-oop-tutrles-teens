package study.utils;

import users.models.Student;

import java.util.HashMap;

public class GPA {
	private Course course;
	private Student student;
	private double numericGrade;
	private String letterGrade;

	public GPA(Course course, Student student) {
		this.course = course;
		this.student = student;
		this.letterGrade = "";
		this.numericGrade = 0;
	}

	public double getNumGrade() {
		HashMap<Student, Mark> gradebook = (HashMap<Student, Mark>) course.getGradebook();
		Mark mark = gradebook.get(student);

		if (mark != null) {
			double attestationScore = mark.calculateAttestation();

			if (attestationScore >= 95) {
				numericGrade = 4.0;
			} else if (attestationScore >= 90) {
				numericGrade = 3.67;
			} else if (attestationScore >= 85) {
				numericGrade = 3.33;
			} else if (attestationScore >= 80) {
				numericGrade = 3.0;
			} else if (attestationScore >= 75) {
				numericGrade = 2.67;
			} else if (attestationScore >= 70) {
				numericGrade = 2.33;
			} else if (attestationScore >= 65) {
				numericGrade = 2.0;
			} else if (attestationScore >= 60) {
				numericGrade = 1.67;
			} else if (attestationScore >= 55) {
				numericGrade = 1.33;
			} else if (attestationScore >= 50) {
				numericGrade = 1.0;
			} else {
				numericGrade = 0.0;
			}
		}
		return numericGrade;
	}

	public String getLetGrade() {
		if (numericGrade == 4.0) {
			letterGrade = "A";
		} else if (numericGrade == 3.67) {
			letterGrade = "A-";
		} else if (numericGrade == 3.33) {
			letterGrade = "B+";
		} else if (numericGrade == 3.0) {
			letterGrade = "B";
		} else if (numericGrade == 2.67) {
			letterGrade = "B-";
		} else if (numericGrade == 2.33) {
			letterGrade = "C+";
		} else if (numericGrade == 2.0) {
			letterGrade = "C";
		} else if (numericGrade == 1.67) {
			letterGrade = "C-";
		} else if (numericGrade == 1.33) {
			letterGrade = "D+";
		} else if (numericGrade == 1.0) {
			letterGrade = "D";
		} else {
			letterGrade = "F";
		}

		return letterGrade;
	}

}
