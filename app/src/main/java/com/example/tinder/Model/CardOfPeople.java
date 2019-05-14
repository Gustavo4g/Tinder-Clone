package com.example.tinder.Model;

import java.io.Serializable;

public class CardOfPeople implements Serializable {
    private float id;
    private String birthDate;
    private String picture;
    private String pictureContentType;
    private float height;
    private float weight;
    private String unitSystem;
    private String aboutMe;
    private String displayName;
    private boolean showAge;
    private boolean banned;
    private String filterPreferences = null;
    Location location;
    User UserObject;
    Relationship relationship;
    Gender GenderObject;
    Ethnicity EthnicityObject;
    private String sentInvitations = null;
    private String receivedInvitations = null;
    private String sentBlocks = null;
    private String receivedBlocks = null;
    private String sentMessages = null;
    private String adminChatrooms = null;


    // Getter Methods

    public float getId() {
        return id;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getPicture() {
        return picture;
    }

    public String getPictureContentType() {
        return pictureContentType;
    }

    public float getHeight() {
        return height;
    }

    public float getWeight() {
        return weight;
    }

    public String getUnitSystem() {
        return unitSystem;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean getShowAge() {
        return showAge;
    }

    public boolean getBanned() {
        return banned;
    }

    public String getFilterPreferences() {
        return filterPreferences;
    }

    public Location getLocation() {
        return location;
    }

    public User getUser() {
        return UserObject;
    }

    public Relationship getRelationship() {
        return relationship;
    }

    public Gender getGender() {
        return GenderObject;
    }

    public Ethnicity getEthnicity() {
        return EthnicityObject;
    }

    public String getSentInvitations() {
        return sentInvitations;
    }

    public String getReceivedInvitations() {
        return receivedInvitations;
    }

    public String getSentBlocks() {
        return sentBlocks;
    }

    public String getReceivedBlocks() {
        return receivedBlocks;
    }

    public String getSentMessages() {
        return sentMessages;
    }

    public String getAdminChatrooms() {
        return adminChatrooms;
    }

    // Setter Methods

    public void setId(float id) {
        this.id = id;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setPictureContentType(String pictureContentType) {
        this.pictureContentType = pictureContentType;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setUnitSystem(String unitSystem) {
        this.unitSystem = unitSystem;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setShowAge(boolean showAge) {
        this.showAge = showAge;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public void setFilterPreferences(String filterPreferences) {
        this.filterPreferences = filterPreferences;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setUser(User userObject) {
        this.UserObject = userObject;
    }

    public void setRelationship(Relationship relationship) {
        this.relationship = relationship;
    }

    public void setGender(Gender genderObject) {
        this.GenderObject = genderObject;
    }

    public void setEthnicity(Ethnicity ethnicityObject) {
        this.EthnicityObject = ethnicityObject;
    }

    public void setSentInvitations(String sentInvitations) {
        this.sentInvitations = sentInvitations;
    }

    public void setReceivedInvitations(String receivedInvitations) {
        this.receivedInvitations = receivedInvitations;
    }

    public void setSentBlocks(String sentBlocks) {
        this.sentBlocks = sentBlocks;
    }

    public void setReceivedBlocks(String receivedBlocks) {
        this.receivedBlocks = receivedBlocks;
    }

    public void setSentMessages(String sentMessages) {
        this.sentMessages = sentMessages;
    }

    public void setAdminChatrooms(String adminChatrooms) {
        this.adminChatrooms = adminChatrooms;
    }
}