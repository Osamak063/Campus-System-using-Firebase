package com.example.osamakhalid.campusrecruitmentsystem;

/**
 * Created by Osama Khalid on 7/14/2017.
 */

public class User {
    String name="";
    String location="";
    String phonenum="";
    String grade="";
    String degree="";
    String skills="";
    String type="";
    String key="";
    public User(){}
    public User(String name,String location,String phonenum,String grade,String degree,String skills,String type,String key){
        this.name=name;
        this.location=location;
        this.phonenum=phonenum;
        this.grade=grade;
        this.degree=degree;
        this.skills=skills;
        this.type=type;
        this.key=key;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getDegree() {
        return degree;
    }

    public String getGrade() {
        return grade;
    }

    public String getLocation() {
        return location;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public String getSkills() {
        return skills;
    }

    public String getKey() {
        return key; }
}
