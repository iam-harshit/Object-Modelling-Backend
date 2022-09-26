package com.crio.jukebox.entities;

import java.util.List;
import com.crio.jukebox.entities.*;

public class Song extends BaseEntity {
    
    private String songName;
    private String genre;
    private String albumName;
    private String albumArtist;
    private List<String> featuredArtist;
    private SongStatus status;


    public Song(String id, String songName, String genre, String albumName, String albumArtist,
            List<String> featuredArtist, SongStatus status) {
        this(songName, genre, albumName, albumArtist, featuredArtist, status);
        this.id = id;
    }
    
    
    public Song(String songName, String genre, String albumName, String albumArtist,
            List<String> featuredArtist, SongStatus status) {
        this.songName = songName;
        this.genre = genre;
        this.albumName = albumName;
        this.albumArtist = albumArtist;
        this.featuredArtist = featuredArtist;
        this.status = status;
    }

    public String getSongName() {
        return this.songName;
    }

    public String getGenre() {
        return this.genre;
    }

    public String getAlbum() {
        return this.albumName;
    }

    public String getOwnerArtist() {
        return this.albumArtist;
    }

    public List<String> getFeaturedArtists() {
        return this.featuredArtist;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getAlbumArtist() {
        return albumArtist;
    }

    public List<String> getFeaturedArtist() {
        return featuredArtist;
    }

    public SongStatus getStatus() {
        return status;
    }

    public void setStatus(SongStatus status) {
        this.status = status;
    }

    
    public String getFeaturedArtistsInString() {
        StringBuilder fArtists = new StringBuilder();
        fArtists.append(getOwnerArtist());

        for (String s : featuredArtist) {
            fArtists.append(",");
            fArtists.append(s);
        }

        //fArtists.deleteCharAt(fArtists.length() - 1);
        return fArtists.toString();
    }

    
}

