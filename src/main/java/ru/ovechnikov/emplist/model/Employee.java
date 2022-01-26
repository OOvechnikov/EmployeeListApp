package ru.ovechnikov.emplist.model;

import java.sql.Time;

public class Employee {

    private Integer id;
    private String firstName;
    private String lastName;
    private String secondName;
    private Integer age;
    private Address address;
    private WorkTime workTime;


    public static Employee buildEmpty() {
        Employee employee = new Employee();
        Address address = new Address();
        WorkTime workTime = new WorkTime();
        workTime.setStart(new Time(0));
        workTime.setFinish(new Time(0));
        employee.setAddress(address);
        employee.setWorkTime(workTime);
        return employee;
    }

    public String getFullName() {
        return lastName + " " + firstName + " " + secondName;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public WorkTime getWorkTime() {
        return workTime;
    }

    public void setWorkTime(WorkTime workTime) {
        this.workTime = workTime;
    }
}
