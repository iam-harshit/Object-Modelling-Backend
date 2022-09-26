package com.crio.jukebox.entities;

import com.crio.jukebox.entities.Song;
import java.util.ArrayList;
import java.util.List;

public class SongPool extends BaseEntity {
    private final List<Song> songPoolList;

    public SongPool() {
        this.songPoolList = new ArrayList<>();
    }

    public SongPool(String id, List<Song> songPoolList) {
        this.songPoolList = songPoolList;
        this.id = id;
    }

    public SongPool(List<Song> songPoolList) {
        this.songPoolList = songPoolList;
    }

    public List<Song> getSongPoolList() {
        return songPoolList;
    }

    public Song songExit(String songId){
        for (int i = 0; i < songPoolList.size(); i++) {
            if (songPoolList.get(i).getId().equals(songId)) {
                return songPoolList.get(i);
            }
        }
        return null;
    }

}

