package com.example.tinder.Model;

import java.io.Serializable;

public class Ethnicity {
    private float id;
    private String ethnicity;


    // Getter Methods

    public float getId() {
        return id;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    // Setter Methods

    public void setId(float id) {
        this.id = id;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }
}