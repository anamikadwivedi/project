package com.example.indo_asia;

public class model {

    private int id;
    private String name;
    private String email;
    private String state;
    private String country;
    private String city;
    private String location;
    private String DOB;
    private String Height;
    private String Weight;
    private String Bust;
    private String Waist;
    private String Hip;
    private byte[] image;

    public model(int id, String name, String email, String state, String country, String city, String DOB, String location, String height, String weight, String bust, String waist, String hip, byte[] image) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.country = country;
        this.city = city;
        this.email = email;
        this.location = location;
        this.DOB = DOB;
        Height = height;
        Weight = weight;
        Bust = bust;
        Waist = waist;
        Hip = hip;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getHeight() {
        return Height;
    }

    public void setHeight(String height) {
        Height = height;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }

    public String getBust() {
        return Bust;
    }

    public void setBust(String bust) {
        Bust = bust;
    }

    public String getWaist() {
        return Waist;
    }

    public void setWaist(String waist) {
        Waist = waist;
    }

    public String getHip() {
        return Hip;
    }

    public void setHip(String hip) {
        Hip = hip;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }



   }