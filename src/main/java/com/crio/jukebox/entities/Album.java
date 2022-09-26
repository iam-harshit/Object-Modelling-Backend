package com.crio.jukebox.entities;

public class Album extends BaseEntity {
    private String albumName;

    public Album(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumName() {
        return this.albumName;

    }
    
}
