package com.crio.jukebox.services;

import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.repositories.UserRepository;

public interface IPlaySongService {
    
    public void playPreviousSong(String userId);

    public void playNextSong(String userId);

    public void playSongById(String userId, String songId);
}
