package study.utils;


import study.Period;
import users.models.Student;

import java.util.HashMap;
import java.util.Vector;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Course
{
	private String uuid;

	private int year;

	private Period period;

	private HashMap<Student,Mark> gradebook;

	private int capacity;
	
	public void Course(String uuid, int year, Period period, int capacity){
		gradebook = new HashMap<Student, Mark>();
		this.uuid = uuid;
		this.year = year;
		this.period = period;
		this.capacity = capacity;
	}

	public double getClassMaxAndMin() {
		for (Mark m: gradebook.values()){

		}
		return 0;
	}
	

	public String outputBarChart() {
		// TODO implement me
		return "";	
	}
	

	public double getClassAvg() {
		// TODO implement me
		return 0;
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

	public String getUuid() {
		return uuid;
	}
}

