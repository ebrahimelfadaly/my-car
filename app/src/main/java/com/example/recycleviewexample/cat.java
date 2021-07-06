package com.example.recycleviewexample;

public class cat {
    private int image;
    private String name1;
    private String name2;

    public cat(int image, String name1,String name2) {
        this.image = image;
        this.name1 = name1;
        this.name2=name2;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name1;
    }

    public void setName(String name) {
        this.name1 = name;
    }
}
