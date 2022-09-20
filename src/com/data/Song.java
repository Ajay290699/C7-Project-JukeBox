package com.data;

public class Song {
    private int songID;
    private String songName;
    private String songDuration;
    private String artistName;
    private String genreType;
    private String songPath;

    public Song() {
    }

    public Song(int songID, String songName, String songDuration, String artistName, String genreType, String songPath) {
        this.songID = songID;
        this.songName = songName;
        this.songDuration = songDuration;
        this.artistName = artistName;
        this.genreType = genreType;
        this.songPath = songPath;
    }

    public int getSongID() {
        return songID;
    }

    public void setSongID(int songID) {
        this.songID = songID;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongDuration() {
        return songDuration;
    }

    public void setSongDuration(String songDuration) {
        this.songDuration = songDuration;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getGenreType() {
        return genreType;
    }

    public void setGenreType(String genreType) {
        this.genreType = genreType;
    }

    public String getSongPath() {
        return songPath;
    }

    public void setSongPath(String songPath) {
        this.songPath = songPath;
    }

    @Override
    public String toString() {
        return "Song{" +
                "songID=" + songID +
                ", songName='" + songName + '\'' +
                ", songDuration='" + songDuration + '\'' +
                ", artistName='" + artistName + '\'' +
                ", genreType='" + genreType + '\'' +
                ", songPath='" + songPath + '\'' +
                '}';
    }
}
