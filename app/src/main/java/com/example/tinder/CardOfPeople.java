package com.example.tinder;

public class CardOfPeople {
    private String name;
    private String img;
    private String age;
    private String description;


    public CardOfPeople(String name, String img, String age, String description) {
        this.name = name;
        this.img = img;
        this.age = age;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
