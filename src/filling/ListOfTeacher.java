package filling;

import model.Teacher;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListOfTeacher {
    private List<Teacher> teacherList;
    private int numberOfTeachers;
    private static final String[] NAMES = {
            "Иванов Иван Иванови",
            "Петрова Анна Сергеевна",
            "Сидоров Алексей Дмитриевич",
            "Кузнецова Мария Викторовна",
            "Смирнов Николай Павлович",
            "Васильева Екатерина Алексеевна",
            "Попов Дмитрий Андреевич",
            "Михайлова Ольга Николаевна",
            "Федоров Сергей Валерьевич",
            "Лебедева Наталья Игоревна"
    };
    private static final String[] LEVEL_OF_EDUCATION_EMPLOYEE = {
            "Высшее образование (бакалавр)",
            "Высшее образование (магистр)",
            "Специалитет",
            "Исследователь",
            "Преподаватель-исследователь"
    };
    private static final String[] ACADEMIC_DEGREE_EMPLOYEE = {
            "Н/Д",
            "кандидат наук",
            "доктор наук"
    };

    public ListOfTeacher(int numberOfTeacher) {
        this.numberOfTeachers = numberOfTeacher;
    }

    public List<Teacher> generation() {
        return generateTeacher(numberOfTeachers);
    }

    /**
     * Запись в файл
     *
     * @param filename Имя файла
     */
    public void write2file(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename + ".txt"))) {
            for (Teacher teacher : teacherList) {
                writer.write(teacher.toString());
                writer.newLine();
            }
            System.out.println("Список преподователей записан в файл " + filename + ".txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Teacher> getTeacherList() {
        return teacherList;
    }

    /**
     * Получить данные о преподаватели по табельному номеру
     *
     * @param personnelNumber табельный номер
     * @return Возвращает null если данные не найдены
     */
    public Teacher getTeacherByPersonnelNumber(String personnelNumber) {
        for (Teacher teacher : teacherList) {
            if (teacher.getPersonnelNumber().equals(personnelNumber)) return teacher;
        }
        return null;
    }

    private static final Random RANDOM = new Random();

    private @NotNull List<Teacher> generateTeacher(int numberOfTeacher) {
        List<Teacher> teachers = new ArrayList<>();
        for (int i = 0; i < numberOfTeacher; i++) {
            String fullName = NAMES[RANDOM.nextInt(NAMES.length)];

            int elementNumInEducationLevelArray = RANDOM.nextInt(LEVEL_OF_EDUCATION_EMPLOYEE.length);
            String levelOfEducation = LEVEL_OF_EDUCATION_EMPLOYEE[elementNumInEducationLevelArray];

            int elementNumInAcademicDegree;
            String academicDegree;
            if (elementNumInEducationLevelArray < 3)//Если сотрудник не закончил аспирантуру - степень науки не присуждается
                elementNumInAcademicDegree = 0;
            else
                elementNumInAcademicDegree = RANDOM.nextInt(ACADEMIC_DEGREE_EMPLOYEE.length);
            academicDegree = ACADEMIC_DEGREE_EMPLOYEE[elementNumInAcademicDegree];

            int experience = RANDOM.nextInt(30);

            String personnelNumber = "V33 - " + RANDOM.nextInt(99999);
            teachers.add(new Teacher(fullName, levelOfEducation, experience, academicDegree, personnelNumber));
        }
        this.teacherList = teachers;
        return teachers;
    }
}
