package com.example.osamakhalid.campusrecruitmentsystem;

/**
 * Created by Osama Khalid on 7/15/2017.
 */

public class Post {
    String jobtitle;
    String description;
    String companyname;
    String location;
    String phonenum;
    String key;
    String postkey;
    Post(){}
    public Post(String jobtitle,String description,String companyname,String location, String phonenum,String key,String postkey){
        this.companyname=companyname;
        this.location=location;
        this.phonenum=phonenum;
        this.jobtitle=jobtitle;
        this.description=description;
        this.key=key;
        this.postkey=postkey;
    }

    public String getPostkey() {
        return postkey;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public String getLocation() {
        return location;
    }

    public String getCompanyname() {
        return companyname;
    }

    public String getDescription() {
        return description;
    }

    public String getJobtitle() {
        return jobtitle;
    }

    public String getKey() {
        return key;
    }
}
