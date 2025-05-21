package model;

import entity.Human;

public class Teacher extends Human {
    private int experience;
    String academicDegree, personnelNumber;

    public Teacher(String fullName, String levelOfEducation, int experience, String academicDegree, String personnelNumber) {
        super(fullName, levelOfEducation);
        this.experience = experience;
        this.academicDegree = academicDegree;
        this.personnelNumber = personnelNumber;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getAcademicDegree() {
        return academicDegree;
    }

    public void setAcademicDegree(String academicDegree) {
        this.academicDegree = academicDegree;
    }

    public String getPersonnelNumber() {
        return personnelNumber;
    }

    public void setPersonnelNumber(String personnelNumber) {
        this.personnelNumber = personnelNumber;
    }

    @Override
    public String toString() {
        return getFullName() + " | " + getLevelOfEducation() + " | " + experience + " | " + academicDegree + " | " + personnelNumber;
    }
}
