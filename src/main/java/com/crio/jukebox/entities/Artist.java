package com.crio.jukebox.entities;

public class Artist extends BaseEntity {
    private String artistName;

    public Artist(String id,String artistName){
        this.artistName = artistName;
        this.id = id;
    }

    public String getArtistName(){
        return this.artistName;
    }
}
