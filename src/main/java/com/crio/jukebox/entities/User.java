package com.crio.jukebox.entities;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class User extends BaseEntity {
    
    private String userName;
    private List<Playlist> playList;
    
    public User(String id, String userName, List<Playlist> playList) {
        this(userName, playList);
        this.id = id;
    }

    public User(String userName, List<Playlist> playList) {
        this.userName = userName;
        this.playList = playList;
    }

    public String getUserName() {
        return this.userName;
    }
    
    public List<Playlist> getPlayList() {
        return playList;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        return str.append(id).append(" ").append(userName).toString();
    }

}
