package ru.ovechnikov.emplist.api.request;

public class UpdateRequest {

    private int id;
    private String firstName;
    private String lastName;
    private String secName;
    private int age;
    private String address;
    private String region;
    private String district;
    private String start;
    private String finish;


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

    public String getSecName() {
        return secName;
    }

    public void setSecName(String secName) {
        this.secName = secName;
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

    public void setStart(String start) {
        this.start = start;
    }

    public String getFinish() {
        return finish.toString();
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }
}
