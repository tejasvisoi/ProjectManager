package com.example.test;

public class users {
    public String name,rollno,depart,course,batch;
    public users(){

    }
    public users(String roll, String name, String batch, String depart, String course) {
        this.name = name;
        this.rollno = roll;
        this.depart = depart;
        this.course = course;
        this.batch = batch;
    }
}
