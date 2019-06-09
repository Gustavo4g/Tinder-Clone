package com.example.tinder.Model;

public class Gender implements Cloneable {
    private float id;
    private String type;


    // Getter Methods

    public float getId() {
        return id;
    }

    public void setId(float id) {
        this.id = id;
    }

    // Setter Methods

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object clone() throws CloneNotSupportedException {

        return super.clone();
    }
}