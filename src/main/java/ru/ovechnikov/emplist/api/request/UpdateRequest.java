package ru.ovechnikov.emplist.api.request;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UpdateRequest {

    private static final SimpleDateFormat SDF = new SimpleDateFormat("HH:mm");

    private int id;
    private String firstName;
    private String lastName;
    private String secondName;
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

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
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

    public Time getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = stringToTime(start);
    }

    public Time getFinish() {
        return finish;
    }

    public void setFinish(String finish) {
        this.finish = stringToTime(finish);
    }


    private Time stringToTime(String str) {
        long ms;
        try {
            ms = SDF.parse(str).getTime();
        } catch (ParseException e) {
            //Todo write custom exceptions
            throw new IllegalArgumentException();
        }
        return new Time(ms);
    }
}
