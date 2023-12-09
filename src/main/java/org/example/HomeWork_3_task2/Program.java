package org.example.HomeWork_3_task2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.example.HomeWork_3_task2.FileStudents.*;
import static org.example.HomeWork_3_task2.FileStudents.FILE_BIN;
import static org.example.HomeWork_3_task2.FileStudents.FILE_JSON;
import static org.example.HomeWork_3_task2.FileStudents.FILE_XML;

public class Program {

    public static void main(String[] args) {

        List<Student> students;
        File f = new File(FILE_JSON);
        //проверка создан ли файл в рамках JSON документа
        if (f.exists() && !f.isDirectory())
            students = loadStudentFromFile(FILE_JSON);
        else
            students = prepareStudents();
        FileStudents.saveStudentToFile(FILE_JSON, students); //смотрим другие форматы
        FileStudents.saveStudentToFile(FILE_BIN, students);
        FileStudents.saveStudentToFile(FILE_XML, students);

        displayStudents(students);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1. Добавить нового студента");
//            System.out.println("2. ");
            System.out.println("3. Выйти");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":

                    FileStudents.addNewStudent(scanner, students);
                    break;
//                case "2":
//                    ToDoListApp.markTaskAsDone(scanner, tasks);
//                    break;
                case "3":
                    FileStudents.saveStudentToFile(FILE_JSON, students);
                    FileStudents.saveStudentToFile(FILE_BIN, students);
                    FileStudents.saveStudentToFile(FILE_XML, students);
                    System.out.println("Список студентов сохранен.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Некорректный выбор. Попробуйте снова.");
                    break;
            }

            displayStudents(students);
        }

    }

    /**
     * Добавили студентов
     * @return
     */
    static List<Student> prepareStudents()
    {
        ArrayList<Student> list = new ArrayList<>();
        list.add(new Student("Alena", 38, 300));
        list.add(new Student("Sasha", 38, 300));
        list.add(new Student("Liza", 14, 300));
        list.add(new Student("Daniil", 9, 300));
        return list;
    }
}
