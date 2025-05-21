package filling;

import model.Exam;
import model.Student;
import model.Teacher;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListOfExam {
    private List<Exam> examList;
    private int numberOfExam;

    private static final String[] DISCIPLINE = {
            "История России",
            "ООП",
            "Архитектура ЭВМ",
            "Компьютерные сети",
            "Философия",
            "Безопасноть жизнидеятельности",
            "Российские операционные системы",
            "Информационная безопасность"
    };
    private static final String[] GRADE = {
            "не допущен",
            "не явился",
            "неудовлетворительно",
            "удовлетворительно",
            "хорошо",
            "отлично"
    };

    public ListOfExam(int numberOfExam) {
        this.numberOfExam = numberOfExam;
    }

    public List<Exam> generation(ListOfStudent listOfStudent, ListOfTeacher listOfTeacher) {
        return generateExam(numberOfExam, listOfStudent, listOfTeacher);
    }

    public List<Exam> getExamList() {
        return examList;
    }

    /**
     * Получить информацию об экзамене для студента соответствующей номеру зачетной книжки
     * @param gradeBookNumber Номер зачетной книжки
     * @return Возвращает null если данные не найдены
     */
    public Exam getExamByGradeBookNumber(int gradeBookNumber) {
        for (Exam exam : examList) {
            if (exam.getGradeBookNumber() == gradeBookNumber) return exam;
        }
        return null;
    }
    /**
     * Запись в файл
     *
     * @param filename Имя файла
     */
    public void write2file(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename + ".txt"))) {
            for (Exam exam : examList) {
                writer.write(exam.toString());
                writer.newLine();
            }
            System.out.println("Список студентов записан в файл " + filename + ".txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static final Random RANDOM = new Random();
    private static final LocalDate DATE = LocalDate.now();

    private @NotNull List<Exam> generateExam(int numberOfExam, ListOfStudent listOfStudent, ListOfTeacher listOfTeacher) {
        List<Exam> exams = new ArrayList<>();
        for (int i = 0; i < numberOfExam; i++) {
            String nameExam = DISCIPLINE[RANDOM.nextInt(DISCIPLINE.length)];

            List<Teacher> teachers = listOfTeacher.getTeacherList();
            String personNumOfTheTeacherAdminTheExam = teachers.get(RANDOM.nextInt(teachers.size())).getPersonnelNumber();

            List<Student> students = listOfStudent.getStudentList();
            int gradeBookNumber = students.get(RANDOM.nextInt(students.size())).getGradeBookNumber();
            @NotNull String dateExam = generateDateExam();
            String grade = GRADE[RANDOM.nextInt(GRADE.length)];
            exams.add(new Exam(nameExam, personNumOfTheTeacherAdminTheExam, dateExam, gradeBookNumber, grade));
        }
        this.examList = exams;
        return exams;
    }

    private @NotNull String generateDateExam() {
        return DATE.minusDays(RANDOM.nextInt(15)).format(DateTimeFormatter.ofPattern("dd-MM-yyy"));
    }
}
