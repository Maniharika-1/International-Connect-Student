package com.example.studentinternationalconnect.data.model;

public class Student {

    String firstName;
    String lastName;
    String address;
    String highestQualification;
    String GPA;
    String IELTSScore;
    String password;
    String userName;
    String token;

    public Student(String firstName, String lastName, String address, String highestQualification, String GPA, String IELTSScore, String password, String userName, String token) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.highestQualification = highestQualification;
        this.GPA = GPA;
        this.IELTSScore = IELTSScore;
        this.password = password;
        this.userName = userName;
        this.token = token;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHighestQualification() {
        return highestQualification;
    }

    public void setHighestQualification(String highestQualification) {
        this.highestQualification = highestQualification;
    }

    public String getGPA() {
        return GPA;
    }

    public void setGPA(String GPA) {
        this.GPA = GPA;
    }

    public String getIELTSScore() {
        return IELTSScore;
    }

    public void setIELTSScore(String IELTSScore) {
        this.IELTSScore = IELTSScore;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
