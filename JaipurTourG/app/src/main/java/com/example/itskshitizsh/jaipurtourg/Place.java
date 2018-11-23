package com.example.itskshitizsh.jaipurtourg;

public class Place {

    private int myId;
    private String namePlace;
    private String shortDescription;
    private double rating;
    private int myImage;

    public Place(int id, String name, String shortDescription, double rating, int image) {
        this.myId = id;
        this.namePlace = name;
        this.shortDescription = shortDescription;
        this.rating = rating;
        this.myImage = image;
    }

    public int getMyId() {
        return myId;
    }

    public String getNamePlace() {
        return namePlace;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public double getRating() {
        return rating;
    }

    public int getMyImage() {
        return myImage;
    }
}
