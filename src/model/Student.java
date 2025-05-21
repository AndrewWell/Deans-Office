package model;

import entity.Human;

public class Student extends Human {
    private String phoneNumber, address, groupNumber;
    private int gradeBookNumber;

    public Student(String fullName, String levelOfEducation, String address, String phoneNumber, String groupNumber, int gradeBookNumber) {
        super(fullName, levelOfEducation);
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.groupNumber = groupNumber;
        this.gradeBookNumber = gradeBookNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }

    public int getGradeBookNumber() {
        return gradeBookNumber;
    }

    public void setGradeBookNumber(int gradeBookNumber) {
        this.gradeBookNumber = gradeBookNumber;
    }

    @Override
    public String toString() {
        return getFullName() + " | " + gradeBookNumber + " | " + groupNumber + " | " + getLevelOfEducation() + " | " + phoneNumber + " | " + address;
    }
}
