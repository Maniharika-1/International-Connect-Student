package com.example.studentinternationalconnect.data.model;

public class Student {

    String firstName;
    String lastName;
    String address;
    String levelOfEducation;
    String GPA;
    String IELTSScore;
    String password;
    String userName;
    String token;
    String email;
    String ANo;

    public Student() {
    }

    public Student(String firstName, String lastName, String email, String password, String userName, String address, String levelOfEducation, String GPA, String IELTSScore, String token, String ANo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.address = address;
        this.levelOfEducation = levelOfEducation;
        this.GPA = GPA;
        this.IELTSScore = IELTSScore;
        this.token = token;
        this.ANo = ANo;
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

    public String getLevelOfEducation() {
        return levelOfEducation;
    }

    public void setLevelOfEducation(String levelOfEducation) {
        this.levelOfEducation = levelOfEducation;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getANo() {
        return ANo;
    }

    public void setANo(String ANo) {
        this.ANo = ANo;
    }

}
