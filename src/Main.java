import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Control control = new Control(20, 5, 10);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n1 - Вывести список студентов");
            System.out.println("2 - Вывести список преподавателей");
            System.out.println("3 - Вывести список пройденных экзаменов");
            System.out.println("4 - Вывести список студентов имеющие возможность подать документы на академическую стипендию");
            System.out.println("5 - Вывести список студентов сдавшие сессию на неуд. оценку");
            System.out.println("6 - Сохранить данные о студентах в файл");
            System.out.println("7 - Сохранить данные о преподователях в файл");
            System.out.println("8 - Сохранить данные о экзаменах в файл");
            System.out.println("9 - Сохранить данные о студентах сдавших сессию на неуд. оценку");
            System.out.println("10 - Сохранить данные о студентах претендующих на академическую стипендию");
            System.out.println("q - Выход из программы");
            String choice = scanner.next();
            switch (choice) {
                case "1":
                    System.out.println("ФИО | ном. группы | номер зач.книжки | номер телефона | адрес");
                    control.printListOfStudent();
                    break;
                case "2":
                    System.out.println("ФИО | таб.номе | уровень обр. | научная степень | преп. стаж");
                    control.printListOfTeacher();
                    break;
                case "3":
                    System.out.println("ФИО студента | номер группы | номер зачетной книжки | название дисциплины по которой проходил экзамен | дата проведения экзамена | оценка | ФИО преподавателя");
                    control.printListOfExams();
                    break;
                case "4":
                    control.printListOfStudentsWhoPassedTheSessionWithPositiveGrade();
                    break;
                case "5":
                    control.printListOfStudentsWhoPassedTheSessionWithNegativeGrade();
                case "6":
                    control.saveFeWithStudentsList();
                    break;
                case "7":
                    control.saveFeWithTeachersList();
                    break;
                case "8":
                    control.saveFeWithExamList();
                    break;
                case "9":
                    control.save2FileStudentListWhoPassedTheSession4UnsatisfactoryAssessment();
                    break;
                case "10":
                    control.save2FileStudentListApp4AcademicScholarship();
                    break;
                case ("q"):
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте еще раз.");
                    break;
            }
        }
        System.out.println("Вы завершили работу программы, до скорых встреч!");
        scanner.close();
    }
}