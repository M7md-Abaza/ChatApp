package com.m7mdabaza.emlenotes.pojo;

import java.util.ArrayList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class SecondScreenResponse {
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Pic")
    @Expose
    private String pic;
    @SerializedName("Messages")
    @Expose
    private ArrayList<Message> messages = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }
}
