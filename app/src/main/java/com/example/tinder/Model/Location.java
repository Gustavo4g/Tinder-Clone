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

    public void setId(float id) {
        this.id = id;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getUrlGoogleMaps() {
        return urlGoogleMaps;
    }

    public void setUrlGoogleMaps(String urlGoogleMaps) {
        this.urlGoogleMaps = urlGoogleMaps;
    }

    public String getUrlOpenStreetMap() {
        return urlOpenStreetMap;
    }

    public void setUrlOpenStreetMap(String urlOpenStreetMap) {
        this.urlOpenStreetMap = urlOpenStreetMap;
    }

    public String getAddress() {
        return address;
    }

    // Setter Methods

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateProvice() {
        return stateProvice;
    }

    public void setStateProvice(String stateProvice) {
        this.stateProvice = stateProvice;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}