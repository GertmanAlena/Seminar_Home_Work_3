package org.example.HomeWork_3_task2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileStudents {

    public static final String FILE_JSON = "students.json";
    public static final String FILE_BIN = "students.bin";
    public static final String FILE_XML = "students.xml";

    private static final ObjectMapper objectMapper = new ObjectMapper();   //работает с json документом
    private static final XmlMapper xmlMapper = new XmlMapper(); //работает с xml документом

    public static void addNewStudent(Scanner scanner, List<Student> students) {
        System.out.println("Введите имя студента:");
        String name = scanner.nextLine();
        System.out.println("Введите возраст студента:");
        int age = scanner.nextInt();
        System.out.println("Введите GPA студента:");
        double GPA = scanner.nextDouble();

        students.add(new Student(name, age, GPA));
        saveStudentToFile(FILE_JSON, students);
        saveStudentToFile(FILE_BIN, students);
        saveStudentToFile(FILE_XML, students);
        System.out.println("Новый студент добавлен.");
    }

    public static List<Student> loadStudentFromFile(String fileName) {
        List<Student> students = new ArrayList<>();

        File file = new File(fileName);
        if (file.exists()) {
            try {
                if (fileName.endsWith(".json")) {
                    students = objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, Student.class));
                } else if (fileName.endsWith(".bin")) {
                    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                        students = (List<Student>) ois.readObject();
                    }
                } else if (fileName.endsWith(".xml")) {
                    students = xmlMapper.readValue(file, xmlMapper.getTypeFactory().constructCollectionType(List.class, Student.class));
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return students;
    }

    public static void saveStudentToFile(String fileName, List<Student> tasks) {
        try {
            if (fileName.endsWith(".json")) {
                objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
                objectMapper.writeValue(new File(fileName), tasks);
            } else if (fileName.endsWith(".bin")) {
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
                    oos.writeObject(tasks);
                }
            } else if (fileName.endsWith(".xml")) {
                //String s = xmlMapper.writeValueAsString(tasks);
                xmlMapper.writeValue(new File(fileName), tasks);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void displayStudents(List<Student> stud) {
        System.out.println("Список студентов:");
        for (int i = 0; i < stud.size(); i++) {
            Student student = stud.get(i);

            System.out.println((i + 1) + ". " + student.getName() + " " + student.getAge() + " " + student.getGPA());
        }
    }

    public static List<Student> DellStudent(Scanner scanner, List<Student> s) {
        displayStudents(s);
        System.out.println("Укажите номер студента, которого нужно удалить:");
        int x = scanner.nextInt();
        for (int i = 0; i < s.size(); i++) {
            if(i+1 == x){
                Student student = s.get(i);
//                System.out.println((i + 1) + ". " + student.getName() + " " + student.getAge() + " " + student.getGPA());
                s.remove(i);
            }

        }
        return s;
    }
}
