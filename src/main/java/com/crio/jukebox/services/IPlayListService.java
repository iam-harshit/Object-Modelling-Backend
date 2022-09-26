package com.crio.jukebox.services;

import java.util.List;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;

public interface IPlayListService {
    
    public void deletePlayList(String userId, String playlistId);

    public Playlist createPlaylist(String userId, String playlistName, List<String> songIdList);
    
    public void addSongToPlayList(String userId, String playlistId, List<String> songIdList);

    public void deleteSongFromPlaylist(String userId, String playlistId, List<String> songIdList);

    public void playPlayListSong(String userId, String playListId);

    public List<Song> getPlayListSongs(String playlistId);
}
