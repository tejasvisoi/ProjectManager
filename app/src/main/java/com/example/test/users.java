package com.example.test;

public class users {
    public String name,rollno,depart,course,batch,email;
    public users(){
    }
    public users(String roll, String name, String batch, String depart, String course, String email) {
        this.name = name;
        this.rollno = roll;
        this.depart = depart;
        this.course = course;
        this.batch = batch;
        this.email= email;
    }
}
