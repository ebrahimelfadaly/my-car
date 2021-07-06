package com.example.recycleviewexample;

public class Cars {
    private int id;
    private String model;
    private String color;
    private double distancelitrer;
    private String descreption;
    private String image;

    public Cars(int id, String model, String color, double distancelitrer, String descreption, String image) {
        this.id = id;
        this.model = model;
        this.color = color;
        this.distancelitrer = distancelitrer;
        this.descreption = descreption;
        this.image = image;
    }

    public Cars(String model, String color, double distancelitrer, String descreption, String image) {
        this.model = model;
        this.color = color;
        this.distancelitrer = distancelitrer;
        this.descreption = descreption;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getDistancelitrer() {
        return distancelitrer;
    }

    public void setDistancelitrer(double distancelitrer) {
        this.distancelitrer = distancelitrer;
    }

    public String getDescreption() {
        return descreption;
    }

    public void setDescreption(String descreption) {
        this.descreption = descreption;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
