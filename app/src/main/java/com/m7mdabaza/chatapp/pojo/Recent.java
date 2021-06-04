package com.m7mdabaza.chatapp.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Recent {

    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Pic")
    @Expose
    private String pic;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("Time")
    @Expose
    private String time;
    @SerializedName("New")
    @Expose
    private Integer _new;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getNew() {
        return _new;
    }

    public void setNew(Integer _new) {
        this._new = _new;
    }

}
