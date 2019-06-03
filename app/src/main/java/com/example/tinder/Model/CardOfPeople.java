package com.example.tinder.Model;

import java.io.Serializable;

public class CardOfPeople implements Serializable {
    private Location location;
    private User user;
    private Relationship relationship;
    private Gender gender;
    private Ethnicity ethnicity;
    private long id;
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
    private String sentInvitations = null;
    private String receivedInvitations = null;
    private String sentBlocks = null;
    private String receivedBlocks = null;
    private String sentMessages = null;
    private String adminChatrooms = null;


    // Getter Methods

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPictureContentType() {
        return pictureContentType;
    }

    public void setPictureContentType(String pictureContentType) {
        this.pictureContentType = pictureContentType;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getUnitSystem() {
        return unitSystem;
    }

    public void setUnitSystem(String unitSystem) {
        this.unitSystem = unitSystem;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public boolean getShowAge() {
        return showAge;
    }

    public void setShowAge(boolean showAge) {
        this.showAge = showAge;
    }

    public boolean getBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public String getFilterPreferences() {
        return filterPreferences;
    }

    // Setter Methods

    public void setFilterPreferences(String filterPreferences) {
        this.filterPreferences = filterPreferences;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User userObject) {
        this.user = userObject;
    }

    public Relationship getRelationship() {
        return relationship;
    }

    public void setRelationship(Relationship relationship) {
        this.relationship = relationship;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender genderObject) {
        this.gender = genderObject;
    }

    public Ethnicity getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(Ethnicity ethnicityObject) {
        this.ethnicity = ethnicityObject;
    }

    public String getSentInvitations() {
        return sentInvitations;
    }

    public void setSentInvitations(String sentInvitations) {
        this.sentInvitations = sentInvitations;
    }

    public String getReceivedInvitations() {
        return receivedInvitations;
    }

    public void setReceivedInvitations(String receivedInvitations) {
        this.receivedInvitations = receivedInvitations;
    }

    public String getSentBlocks() {
        return sentBlocks;
    }

    public void setSentBlocks(String sentBlocks) {
        this.sentBlocks = sentBlocks;
    }

    public String getReceivedBlocks() {
        return receivedBlocks;
    }

    public void setReceivedBlocks(String receivedBlocks) {
        this.receivedBlocks = receivedBlocks;
    }

    public String getSentMessages() {
        return sentMessages;
    }

    public void setSentMessages(String sentMessages) {
        this.sentMessages = sentMessages;
    }

    public String getAdminChatrooms() {
        return adminChatrooms;
    }

    public void setAdminChatrooms(String adminChatrooms) {
        this.adminChatrooms = adminChatrooms;
    }
}