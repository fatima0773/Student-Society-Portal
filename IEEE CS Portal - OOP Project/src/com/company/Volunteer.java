package com.company;

import java.io.Serializable;
import java.util.Scanner;

public class Volunteer implements Serializable {
    private String name;
    private String regNo;
    private String contact;
    private String email;

    public Volunteer(){
        name = regNo = email = contact = null;
    }
    public Volunteer(String name, String regNo, String contact, String email) {
        this.name = name;
        this.regNo = regNo;
        this.contact = contact;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Volunteer{" +
                "name='" + name + '\'' +
                ", regNo='" + regNo + '\'' +
                ", contact=" + contact +
                ", email='" + email + '\'' +
                '}';
    }
    public void readData(){
        Scanner input = new Scanner(System.in);
        System.out.print("name:");
        name=input. nextLine();
        System.out.print("reg:");
        regNo = input.nextLine();
        System.out.print("email:");
        email = input.nextLine();
        System.out.print("contact:");
        contact = input.nextLine();


    }
}
