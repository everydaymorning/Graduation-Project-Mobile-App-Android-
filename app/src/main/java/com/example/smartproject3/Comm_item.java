package com.example.smartproject3;

import android.widget.TextView;

public class Comm_item {
    private String boardUser;
    private String boardType;
    private String boardReg;
    private String boardContent;
    private String boardDate;

    public Comm_item() {
    }

    public Comm_item(String boardUser, String boardType, String boardReg, String boardContent, String boardDate) {
        this.boardUser = boardUser;
        this.boardType = boardType;
        this.boardReg = boardReg;
        this.boardContent = boardContent;
        this.boardDate = boardDate;
    }

    public String getBoardUser() {
        return boardUser;
    }

    public void setBoardUser(String boardUser) {
        this.boardUser = boardUser;
    }

    public String getBoardType() {
        return boardType;
    }

    public void setBoardType(String boardType) {
        this.boardType = boardType;
    }

    public String getBoardReg() {
        return boardReg;
    }

    public void setBoardReg(String boardReg) {
        this.boardReg = boardReg;
    }

    public String getBoardContent() {
        return boardContent;
    }

    public void setBoardContent(String boardContent) {
        this.boardContent = boardContent;
    }

    public String getBoardDate() {
        return boardDate;
    }

    public void setBoardDate(String boardDate) {
        this.boardDate = boardDate;
    }
}
