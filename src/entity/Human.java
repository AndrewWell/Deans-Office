package entity;

public abstract class Human {
    private String fullName, levelOfEducation;

    public Human(String fullName, String levelOfEducation) {
        this.fullName = fullName;
        this.levelOfEducation = levelOfEducation;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLevelOfEducation() {
        return levelOfEducation;
    }

    public void setLevelOfEducation(String levelOfEducation) {
        this.levelOfEducation = levelOfEducation;
    }
}
