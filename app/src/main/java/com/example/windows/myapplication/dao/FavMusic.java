package com.example.windows.myapplication.dao;

import android.provider.BaseColumns;

public class FavMusic {

    private int id;
    private String name;
    private String artist;
    private String album;
    private String description;

    public static final String DATABASE_NAME = "fav_music.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE = "music";

    public FavMusic() {
        id = 0;
        name = "";
        artist = "";
        album = "";
        description = "";
    }

    public static class Column
    {
        public static final String ID = BaseColumns._ID;
        public static final String MUSIC_NAME = "name";
        public static final String ARTIST = "artist";
        public static final String ALBUM = "album";
        public static final String DESCRIPTION = "description";

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMusicName() {
        return name;
    }

    public void setMusicName(String musicName) {
        this.name = musicName;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
