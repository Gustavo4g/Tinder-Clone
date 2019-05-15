package com.example.tinder.Model;

public class Location {
    private float id;
    private float latitude;
    private float longitude;
    private String urlGoogleMaps;
    private String urlOpenStreetMap;
    private String address;
    private String postalCode;
    private String city;
    private String stateProvice;
    private String county;
    private String country;


    // Getter Methods

    public float getId() {
        return id;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public String getUrlGoogleMaps() {
        return urlGoogleMaps;
    }

    public String getUrlOpenStreetMap() {
        return urlOpenStreetMap;
    }

    public String getAddress() {
        return address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    public String getStateProvice() {
        return stateProvice;
    }

    public String getCounty() {
        return county;
    }

    public String getCountry() {
        return country;
    }

    // Setter Methods

    public void setId(float id) {
        this.id = id;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public void setUrlGoogleMaps(String urlGoogleMaps) {
        this.urlGoogleMaps = urlGoogleMaps;
    }

    public void setUrlOpenStreetMap(String urlOpenStreetMap) {
        this.urlOpenStreetMap = urlOpenStreetMap;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStateProvice(String stateProvice) {
        this.stateProvice = stateProvice;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}