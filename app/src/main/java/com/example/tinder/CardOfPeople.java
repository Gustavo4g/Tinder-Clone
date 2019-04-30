package com.example.tinder;

public class CardOfPeople {
    private String title;
    private String img;
    private String age;
    private String description;


    public CardOfPeople(String title, String img, String age, String description) {
        this.title = title;
        this.img = img;
        this.age = age;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
