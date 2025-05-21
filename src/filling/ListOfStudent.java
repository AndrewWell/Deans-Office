package filling;

import model.Student;
import model.Teacher;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListOfStudent {
    private List<Student> studentList;
    private int numberOfStudents;
    private static final String[] STREETS = {
            "Ленина", "Пушкина", "Гагарина", "Мира", "Советская"
    };
    private static final String[] CITIES = {
            "Москва", "Владимир", "Новосибирск", "Екатеринбург", "Казань", "Ярополч-Залеский"
    };

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

    public ListOfStudent(int numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }

    public List<Student> generation() {
        return generateStudent(numberOfStudents);
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    /**
     * Получить данные о студенте по номеру зачетной книжки
     * @param recordGradeNumber номер зачетной книжки
     * @return Возвращает null если данные не найдены
     */
    public Student getStudentByGradeBookNumber(int recordGradeNumber){
        for (Student student:studentList) {
            if(student.getGradeBookNumber()==recordGradeNumber) return student;
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
            for (Student student : studentList) {
                writer.write(student.toString());
                writer.newLine();
            }
            System.out.println("Список студентов записан в файл " + filename + ".txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static final Random RANDOM = new Random();

    private @NotNull List<Student> generateStudent(int numberOfStudents) {
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < numberOfStudents; i++) {
            String fullName = NAMES[RANDOM.nextInt(NAMES.length)];
            String levelOfEducation = LEVEL_OF_EDUCATION_EMPLOYEE[RANDOM.nextInt(LEVEL_OF_EDUCATION_EMPLOYEE.length)];
            String address = generateAddress();
            String phoneNumber = generatePhoneNumber();
            String groupNumber= "Группа - "+RANDOM.nextInt(25);
            int gradeBoolNumber = RANDOM.nextInt(99999999);
            students.add(new Student(fullName, levelOfEducation, address, phoneNumber, groupNumber, gradeBoolNumber));
        }
        this.studentList = students;
        return students;
    }

    private static String generatePhoneNumber() {
        return String.format("+7 (%d) %d-%d-%d",
                900 + RANDOM.nextInt(100),
                RANDOM.nextInt(900) + 100,
                RANDOM.nextInt(90) + 10,
                RANDOM.nextInt(90) + 10
        );
    }

    private static String generateAddress() {
        return String.format(" %s, ул. %s, д. %d",
                STREETS[RANDOM.nextInt(STREETS.length)],
                CITIES[RANDOM.nextInt(CITIES.length)],
                RANDOM.nextInt(100) + 1
        );
    }
}
