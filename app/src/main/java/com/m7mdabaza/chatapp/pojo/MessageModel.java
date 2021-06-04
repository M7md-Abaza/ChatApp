package com.m7mdabaza.chatapp.pojo;

public class MessageModel {

    private String message, pic;
    private Integer sender;

    public MessageModel(String message, String pic, Integer sender) {
        this.message = message;
        this.pic = pic;
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Integer getSender() {
        return sender;
    }

    public void setSender(Integer sender) {
        this.sender = sender;
    }
}
