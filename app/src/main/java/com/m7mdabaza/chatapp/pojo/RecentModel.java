package com.m7mdabaza.chatapp.pojo;

public class RecentModel {

    private String name;
    private String message;
    private String time;
    private Integer _new;
    private String pic;

    public RecentModel(String name, String message, String time, Integer _new, String pic) {
        this.name = name;
        this.message = message;
        this.time = time;
        this._new = _new;
        this.pic = pic;
    }

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

    public Integer get_new() {
        return _new;
    }

    public void set_new(Integer _new) {
        this._new = _new;
    }
}
