package study.utils;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import Core.CoreSystem;

public class Lesson {
	private final String courseName;
	private LocalTime startTime; 
    private LocalTime endTime;
    private String weekDay;
    private static Language language = CoreSystem.getLanguageMode();

    private static final String[][] DAYS_OF_WEEK = {
            {"Monday", "Понедельник", "Дүйсенбі"},
            {"Tuesday", "Вторник", "Сейсенбі"},
            {"Wednesday", "Среда", "Сәрсенбі"},
            {"Thursday", "Четверг", "Бейсенбі"},
            {"Friday", "Пятница", "Жұма"}
    };



    public Lesson(String courseName) {
        this.courseName = courseName;
        Random rand = new Random();
        
        int randomHour = rand.nextInt(10) + 8;
        this.startTime = LocalTime.of(randomHour, 0);
        
        this.endTime = this.startTime.plusHours(2);

        int dayIndex = rand.nextInt(5);
        this.dayOfWeek = getDayOfWeekByLanguage(dayIndex, language);
    }

    private String getDayOfWeekByLanguage(int index, Language language) {
        return switch (language) {
            case ENG -> DAYS_OF_WEEK[index][0]; // English
            case RUS -> DAYS_OF_WEEK[index][1]; // Russian
            case KZ -> DAYS_OF_WEEK[index][2];  // Kazakh
        };
    }

    public String getLesson() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return dayOfWeek + ": " + courseName + "\n" +
                startTime.format(formatter) + " - " + endTime.format(formatter);
    }
    
}
