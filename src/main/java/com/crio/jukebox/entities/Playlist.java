package com.crio.jukebox.entities;

import java.util.List;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.entities.Song;

public class Playlist extends BaseEntity {
    
    private String playlistName;
    private User user;
    private List<Song> songList;
    private boolean isActivePlayList;

   

	public Playlist(String id, String playlistName, User user, List<Song> songList, boolean isActivePlayList) {
        this(playlistName, user, songList, isActivePlayList);
        this.id = id;
    }

    public Playlist(String playlistName, User user, List<Song> songList, boolean isActivePlayList) {
        this.playlistName = playlistName;
        this.user= user;
        this.songList = songList;
        this.isActivePlayList = false;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public User getUser() {
        return user;
    }

    public List<Song> getSongList() {
        return songList;
    }

    public boolean getIsActivePlayList() {
        return isActivePlayList;
    }

    public void setActivePlayList(boolean isActivePlayList) {
        this.isActivePlayList = isActivePlayList;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        return str.append("Playlist ID - ").append(getId()).toString();
    }

    public String getSongIdsToString() {
        StringBuilder songIDs = new StringBuilder();
        List<String> sList = getSongList().stream().map(e -> e.getId()).collect(Collectors.toList());

        for (String id : sList) {
            songIDs.append(id);
            songIDs.append(" ");
        }
        songIDs.deleteCharAt(songIDs.length() - 1);
        return songIDs.toString();
    }
    
}

