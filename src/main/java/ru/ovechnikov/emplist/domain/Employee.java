package ru.ovechnikov.emplist.domain;

import java.sql.Time;

public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private String surName;
    private String name;
    private int age;
    private String address;
    private String region;
    private String district;
    private Time start;
    private Time finish;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStart() {
        return start.toString();
    }

    public void setStart(Time start) {
        this.start = start;
    }

    public String getFinish() {
        return finish.toString();
    }

    public void setFinish(Time finish) {
        this.finish = finish;
    }
}