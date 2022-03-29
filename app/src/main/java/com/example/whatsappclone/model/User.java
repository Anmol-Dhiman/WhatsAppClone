package com.example.whatsappclone.model;

public class User {

    String profliePic, phoneNumber, about, userId;
    String lastMessage;

    public User(String phoneNumber, String about ) {

        this.phoneNumber = phoneNumber;
        this.about = about;
    }

    public String getLastMessage() {
        return lastMessage;
    }


    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public User() {
    }

    public String getProfliePic() {
        return profliePic;
    }

    public void setProfliePic(String profliePic) {
        this.profliePic = profliePic;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
