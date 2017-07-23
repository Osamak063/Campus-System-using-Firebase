package com.example.osamakhalid.campusrecruitmentsystem;

/**
 * Created by Osama Khalid on 7/21/2017.
 */

public class Apply {
    String name="";
    String location="";
    String phonenum="";
    String grade="";
    String degree="";
    String skills="";
    String type="";
    String key="";
    String postkey="";
    public Apply(){}
    public Apply(String name,String location,String phonenum,String grade,String degree,String skills,String type,String key,String postkey){
        this.name=name;
        this.location=location;
        this.phonenum=phonenum;
        this.grade=grade;
        this.degree=degree;
        this.skills=skills;
        this.type=type;
        this.key=key;
        this.postkey=postkey;
    }

    public String getPostkey() {
        return postkey;
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
