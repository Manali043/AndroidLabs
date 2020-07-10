package com.example.androidlabs;

public class MessageModel {
    public String message;
    public boolean isSend;

    private   long id;

    public MessageModel(Long id , String message,  boolean isSend) {
        this.id = id;
        this.message = message;
        this.isSend = isSend;
    }

    public MessageModel(String message, boolean isSend) {
        this.message = message;
        this.isSend = isSend;
    }

    public MessageModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSend() {
        return isSend;
    }

    public void setSend(boolean send) {
        isSend = send;
    }
}