package org.example.HomeWork_3;

import java.io.*;

public class Program {

    public static void main(String[] args) {

        Student student = new Student("Alena", 38, 300);

        try (FileOutputStream fos = new FileOutputStream("student.bin");
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(student);
            System.out.println("Объект UserData сериализован");
        }catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (FileInputStream fis = new FileInputStream("student.bin");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            student = (Student)ois.readObject();
            System.out.println("Объект UserData ДЕсериализован");
        }catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Имя: " + student.getName());
        System.out.println("Возраст: " + student.getAge());
        System.out.println("GPA (должен быть null, так как transient): " + student.getGPA());
    }
}
