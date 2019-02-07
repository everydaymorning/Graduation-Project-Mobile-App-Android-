package com.example.smartproject3;

import android.widget.TextView;

public class Comm_item {
    private String comm_title;
    private String comm_place;
    private String comm_type;
    private String comm_content;
    private String comm_id;
    private String comm_time;

    public Comm_item() {
    }

    public Comm_item(String comm_title, String comm_place, String comm_type, String comm_content, String comm_id, String comm_time) {
        this.comm_title = comm_title;
        this.comm_place = comm_place;
        this.comm_type = comm_type;
        this.comm_content = comm_content;
        this.comm_id = comm_id;
        this.comm_time = comm_time;
    }

    public String getComm_title() {
        return comm_title;
    }

    public void setComm_title(String comm_title) {
        this.comm_title = comm_title;
    }

    public String getComm_place() {
        return comm_place;
    }

    public void setComm_place(String comm_place) {
        this.comm_place = comm_place;
    }

    public String getComm_type() {
        return comm_type;
    }

    public void setComm_type(String comm_type) {
        this.comm_type = comm_type;
    }

    public String getComm_content() {
        return comm_content;
    }

    public void setComm_content(String comm_content) {
        this.comm_content = comm_content;
    }

    public String getComm_id() {
        return comm_id;
    }

    public void setComm_id(String comm_id) {
        this.comm_id = comm_id;
    }

    public String getComm_time() {
        return comm_time;
    }

    public void setComm_time(String comm_time) {
        this.comm_time = comm_time;
    }
}
