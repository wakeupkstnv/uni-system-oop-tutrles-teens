package users.models;

import users.Faculty;

public enum Major {
    COMPUTER_SCIENCE(Faculty.SITE),
    INFORMATION_SYSTEMS(Faculty.SITE),
    MECHANICAL_ENGINEERING(Faculty.KMA),
    ELECTRICAL_ENGINEERING(Faculty.KMA),
    ECONOMICS(Faculty.BS),
    MANAGEMENT(Faculty.BS);

    private Faculty faculty;

    Major(Faculty faculty) {
        this.faculty = faculty;
    }

    public Faculty getFaculty() {
        return faculty;
    }
}
