import filling.ListOfExam;
import filling.ListOfStudent;
import filling.ListOfTeacher;
import model.Exam;
import model.Student;
import model.Teacher;
import org.jetbrains.annotations.NotNull;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Control {
    private ListOfStudent listOfStudent;
    private ListOfTeacher listOfTeacher;
    private ListOfExam listOfExam;


    public Control(int numberOfStudents, int numberOfTeacher, int numberOfExam) {
        this.listOfStudent = new ListOfStudent(numberOfStudents);
        this.listOfTeacher = new ListOfTeacher(numberOfTeacher);
        this.listOfExam = new ListOfExam(numberOfExam);
        generation();
    }

    public void generation() {
        listOfStudent.generation();
        listOfTeacher.generation();
        listOfExam.generation(listOfStudent, listOfTeacher);
    }

    /**
     * Вывести список преподовательского состава в формате "ФИО, таб.номе, уровень обр., научная степень, преп. стаж"
     */
    public void printListOfTeacher() {
        for (Teacher teacher : listOfTeacher.getTeacherList()) {
            System.out.println(teacher.getFullName() + " | " + teacher.getPersonnelNumber() + " | "
                    + teacher.getLevelOfEducation() + " | " + teacher.getAcademicDegree() + " | " + teacher.getExperience());
        }
    }

    /**
     * Вывести список студентов в формате "ФИО, ном. группы, номер зач.книжки, номер телефона, адрес"
     */
    public void printListOfStudent() {
        for (Student student : listOfStudent.getStudentList()) {
            System.out.println(student.getFullName() + " | " + student.getGroupNumber() + " | " + student.getGradeBookNumber()
                    + " | " + student.getPhoneNumber() + " | " + student.getAddress());
        }
    }

    /**
     * Вывести данные о студенте в формате "ФИО, ном. группы, номер зач.книжки, номер телефона, аддресс"
     */
    public void printListOfStudent(@NotNull Student student) {
        System.out.println(student.getFullName() + " | " + student.getGroupNumber() + " | " + student.getGradeBookNumber()
                + " | " + student.getPhoneNumber() + " | " + student.getAddress());
    }

    /**
     * Вывести список экзаменов в формате "ФИО студента, номер группы, номер зачетной книжки, название дисциплины по которой проходил экзамен,
     * дата проведения экзамена, оценка, ФИО преподавателя"
     */
    public void printListOfExams() {
        for (Exam exam : listOfExam.getExamList()) {
            Student student = listOfStudent.getStudentByGradeBookNumber(exam.getGradeBookNumber());
            System.out.println(student.getFullName() + " | " + student.getGroupNumber() + " | " + exam.getGradeBookNumber() + " | "
                    + exam.getName() + " | " + exam.getExamDate() + " | " + exam.getGrade() + " | " + listOfTeacher.getTeacherByPersonnelNumber(exam.getPersonNumOfTheTeacherAdminTheExam()).getFullName());
        }
    }

    /**
     * Проверить студента на сдачу сессии
     *
     * @param gradeBookNumber Номе зачетной книжки студента
     * @return Если сессия сдана на положительную оценку, метод возвратит true иначе false
     */
    public boolean checkStudent4PassingSession(int gradeBookNumber) {
        for (Exam exam : listOfExam.getExamList()) {
            if ((exam.getGradeBookNumber() == gradeBookNumber) && !(exam.getGrade().equals("хорошо") || exam.getGrade().equals("отлично")))
                return false;
        }
        return true;
    }

    /**
     * Вывести список студентов сдавшие сессию на хорошо и отлично
     */
    public void printListOfStudentsWhoPassedTheSessionWithPositiveGrade() {
        for (Student student : getListOfStudentsWhoTookExams()) {
            if (checkStudent4PassingSession(student.getGradeBookNumber()))
                printListOfStudent(listOfStudent.getStudentByGradeBookNumber(student.getGradeBookNumber()));
        }
    }

    /**
     * Вывести список студентов не сдавшие сессию или имеют оценку удовл.
     */
    public void printListOfStudentsWhoPassedTheSessionWithNegativeGrade() {
        for (Student student : getListOfStudentsWhoTookExams()) {
            if (!checkStudent4PassingSession(student.getGradeBookNumber()))
                printListOfStudent(listOfStudent.getStudentByGradeBookNumber(student.getGradeBookNumber()));
        }
    }

    /**
     * Сохранить файл со списками студентов
     */
    public void saveFeWithStudentsList() {
        listOfStudent.write2file("students");
    }

    /**
     * Сохранить файл со списками преподователей
     */
    public void saveFeWithTeachersList() {
        listOfTeacher.write2file("teachers");
    }
    /**
     * Сохранить файл со списком экзаменов для студентов
     */
    public void saveFeWithExamList(){listOfExam.write2file("exams");}

    /**
     * Сохранить файл со списками студентов претендующих на академическую стипендию
     */
    public void save2FileStudentListApp4AcademicScholarship() {
        PrintStream originalOut = System.out;
        try {
            FileOutputStream fos = new FileOutputStream("StudentListApp4AcademicScholarship.txt");
            PrintStream ps = new PrintStream(fos);
            System.setOut(ps);

            printListOfStudentsWhoPassedTheSessionWithPositiveGrade();

            ps.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.setOut(originalOut);
            System.out.println("Файл со списками студентов предендующих на академическую стипендию создан");
        }
    }

    /**
     * Сохранить файл со списками студентов, сдавшие сессию на неудовлетворительную оценку
     */
    public void save2FileStudentListWhoPassedTheSession4UnsatisfactoryAssessment() {
        PrintStream originalOut = System.out;
        try {
            FileOutputStream fos = new FileOutputStream("StudentListWhoPassedTheSession4UnsatisfactoryAssessment.txt");
            PrintStream ps = new PrintStream(fos);
            System.setOut(ps);

            printListOfStudentsWhoPassedTheSessionWithNegativeGrade();

            ps.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.setOut(originalOut);
            System.out.println("Файл со списками студентов сдавших сессию на неудовлетворительную оценку создан");
        }
    }

    /**
     * Получить список студентов сдававшие экзамен без повторений
     */
    private @NotNull Set<Student> getListOfStudentsWhoTookExams() {
        Set<Student> students = new HashSet<>();
        for (Exam exam : listOfExam.getExamList()) {
            if (students.add(listOfStudent.getStudentByGradeBookNumber(exam.getGradeBookNumber())))
                students.add(listOfStudent.getStudentByGradeBookNumber(exam.getGradeBookNumber()));
        }
        return students;
    }

    /**
     * Получить список экзаменов сдававшие студентом соответстсующий номеру зачетной книжки
     *
     * @param gradeBookNumber Номер зачетной книжки
     */
    private @NotNull List<Exam> getListExamByGradeBookNumber(int gradeBookNumber) {
        List<Exam> exams = new ArrayList<>();
        for (Exam exam : listOfExam.getExamList()) {
            if (exam.getGradeBookNumber() == gradeBookNumber) exams.add(exam);
        }
        return exams;
    }
}
