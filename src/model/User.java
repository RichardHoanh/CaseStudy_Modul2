package model;

import model.Account;

import java.util.ArrayList;

public class User extends Account {

    private String name;
    private String gender;
    private int age;


    public User() {
    }

    public User(String userName, String passWord, String name, String gender, int age) {
        super(userName, passWord);
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "model.User{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                '}';
    }
}