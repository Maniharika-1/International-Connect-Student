package com.example.studentinternationalconnect.data.model;

public class Student {

    String mFirstName;
    String mLastName;
    String mAddress;
    String mHighestQualification;
    String mGPA;
    String mIELTSScore;
    String mPassword;
    String mUserName;
    String mToken;
    String mEmail;
    String mANo;

    public Student() {
    }

    public Student(String firstName, String lastName, String email, String password, String userName, String address, String highestQualification, String GPA, String IELTSScore, String token, String ANo) {
        mFirstName = firstName;
        mLastName = lastName;
        mEmail = email;
        mPassword = password;
        mUserName = userName;
        mAddress = address;
        mHighestQualification = highestQualification;
        mGPA = GPA;
        mIELTSScore = IELTSScore;
        mToken = token;
        mANo = ANo;
    }

    public String getmFirstName() {
        return mFirstName;
    }

    public void setmFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    public String getmLastName() {
        return mLastName;
    }

    public void setmLastName(String mLastName) {
        this.mLastName = mLastName;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public String getmHighestQualification() {
        return mHighestQualification;
    }

    public void setmHighestQualification(String mHighestQualification) {
        this.mHighestQualification = mHighestQualification;
    }

    public String getmGPA() {
        return mGPA;
    }

    public void setmGPA(String mGPA) {
        this.mGPA = mGPA;
    }

    public String getmIELTSScore() {
        return mIELTSScore;
    }

    public void setmIELTSScore(String mIELTSScore) {
        this.mIELTSScore = mIELTSScore;
    }

    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public String getmUserName() {
        return mUserName;
    }

    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public String getmToken() {
        return mToken;
    }

    public void setmToken(String mToken) {
        this.mToken = mToken;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmANo() {
        return mANo;
    }

    public void setmANo(String mANo) {
        this.mANo = mANo;
    }

}
