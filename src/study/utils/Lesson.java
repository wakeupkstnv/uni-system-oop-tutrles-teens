package study.utils;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;


public class Lesson {
	private final String courseName;
	private LocalTime startTime; 
    private LocalTime endTime;
    private int dayIndex;
    private int capacity=15;

    public Lesson(String courseName) {
        this.courseName = courseName;
        Random rand = new Random();
        
        int randomHour = rand.nextInt(10) + 8;
        this.startTime = LocalTime.of(randomHour, 0);
        
        this.endTime = this.startTime.plusHours(2);

        this.dayIndex = rand.nextInt(5);
    }
    
    public int getdayIndex() {
    	return dayIndex;
    }

    public String getLesson() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return courseName+"\n"+startTime.format(formatter) + " - " + endTime.format(formatter);
    }
    
    public boolean addStudent() {
    	if (capacity > 0) {
    		capacity--;
            return true;
    	}
        return false;
    }
}
