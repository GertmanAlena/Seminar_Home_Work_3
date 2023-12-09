package org.example.HomeWork_3_task2;

import java.io.*;

public class Student implements Externalizable {

    //region Externalizable implements
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(name);
        out.writeInt(age);
        out.writeDouble(GPA);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = (String) in.readObject();
        age = in.readInt();
        GPA = in.readDouble();
    }
    //endregion

    //region Поля
    private String name;
    private int age;
    private transient double GPA;

    //endregion

    //region Конструктор

    public Student(String name, int age, double GPA) {
        this.name = name;
        this.age = age;
        this.GPA = GPA;
    }
    public Student() {

    }

    //endregion

    //region Get & Set

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getGPA() {
        return GPA;
    }

    public void setGPA(double GPA) {
        this.GPA = GPA;
    }



    //endregion
}
