package com.data;

public class PlayList {
    private int playListID;
    private String playListName;

    public PlayList() {
    }

    public PlayList(int playListID, String playListName) {
        this.playListID = playListID;
        this.playListName = playListName;
    }

    public int getPlayListID() {
        return playListID;
    }

    public void setPlayListID(int playListID) {
        this.playListID = playListID;
    }

    public String getPlayListName() {
        return playListName;
    }

    public void setPlayListName(String playListName) {
        this.playListName = playListName;
    }

    @Override
    public String toString() {
        return "PlayList{" +
                "playListID=" + playListID +
                ", playListName='" + playListName + '\'' +
                '}';
    }
}
