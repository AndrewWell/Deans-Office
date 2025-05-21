package model;

import entity.Discipline;


public class Exam implements Discipline {
    private String name, personNumOfTheTeacherAdminTheExam, examDate, grade;
    private int gradeBookNumber;

    public Exam(String name, String personNumOfTheTeacherAdminTheExam, String examDate, int gradeBookNumber, String grade) {
        this.name = name;
        this.personNumOfTheTeacherAdminTheExam = personNumOfTheTeacherAdminTheExam;
        this.examDate = examDate;
        this.gradeBookNumber = gradeBookNumber;
        this.grade=grade;
    }

    public int getGradeBookNumber() {
        return gradeBookNumber;
    }

    public void setGradeBookNumber(int gradeBookNumber) {
        this.gradeBookNumber = gradeBookNumber;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPersonNumOfTheTeacherAdminTheExam() {
        return personNumOfTheTeacherAdminTheExam;
    }

    public void setPersonNumOfTheTeacherAdminTheExam(String personNumOfTheTeacherAdminTheExam) {
        this.personNumOfTheTeacherAdminTheExam = personNumOfTheTeacherAdminTheExam;
    }

    @Override
    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
    @Override
    public String toString() {
        return name + " | " + personNumOfTheTeacherAdminTheExam + " | " + examDate + " | " + gradeBookNumber + " | " + grade;
    }
}
