package com.example.tinder.Model;

import java.io.Serializable;

public class Gender implements Serializable {
    private float id;
    private String type;


    // Getter Methods

    public float getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    // Setter Methods

    public void setId(float id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }
}