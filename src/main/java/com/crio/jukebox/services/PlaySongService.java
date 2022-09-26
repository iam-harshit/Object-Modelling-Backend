package com.crio.jukebox.services;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.repositories.UserRepository;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.SongStatus;
import com.crio.jukebox.exceptions.SongNotFound;
import com.crio.jukebox.exceptions.UserNotFound;
import com.crio.jukebox.repositories.PlayListRepository;
import com.crio.jukebox.repositories.SongRepository;

public class PlaySongService implements IPlaySongService {

    private final Map<Song,List<Song>>musicPlayer;

    private final UserRepository userRepository;
    private final SongRepository songRepository;
    private final PlayListRepository playListRepository;

    public PlaySongService(UserRepository userRepository, SongRepository songRepository, PlayListRepository playListRepository)
     {
        this.songRepository = songRepository;
        this.userRepository = userRepository;
        this.playListRepository = playListRepository;
        this.musicPlayer = new LinkedHashMap<>();
    }

    @Override
    public void playPreviousSong(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFound("User not found"));
        
        List<Playlist> playlists = playListRepository.findAll().stream()
                .filter(e -> e.getIsActivePlayList() == true && e.getUser().getId().equals(user.getId())).collect(Collectors.toList());
        
        createMusicPlayer(playlists.get(0));
        
        List<Song> songs = playlists.get(0).getSongList().stream()
                .filter(e -> e.getStatus().equals(SongStatus.valueOf("PLAYING")) == true)
                .collect(Collectors.toList());
        
        Song song = songs.get(0);
        Song previousSong = musicPlayer.get(song).get(0);
        song.setStatus(SongStatus.NOT_PLAYING);
        previousSong.setStatus(SongStatus.PLAYING);

       
        System.out.println("Current Song Playing");
        System.out.println("Song - " + previousSong.getSongName());
        System.out.println("Album - " + previousSong.getAlbum());
        System.out.println("Artists - " + previousSong.getFeaturedArtistsInString());
        
        
    }

    @Override
    public void playNextSong(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFound("User not found"));

        List<Playlist> playlists = playListRepository.findAll().stream().filter(
                e -> e.getIsActivePlayList() == true && e.getUser().getId().equals(user.getId()))
                .collect(Collectors.toList());

        createMusicPlayer(playlists.get(0));

        List<Song> songs = playlists.get(0).getSongList().stream()
                .filter(e -> e.getStatus().equals(SongStatus.valueOf("PLAYING")) == true)
                .collect(Collectors.toList());

        Song song = songs.get(0);
        Song nextSong = musicPlayer.get(song).get(1);
        song.setStatus(SongStatus.NOT_PLAYING);
        nextSong.setStatus(SongStatus.PLAYING);

       
        System.out.println("Current Song Playing");
        System.out.println("Song - " + nextSong.getSongName());
        System.out.println("Album - " + nextSong.getAlbum());
        System.out.println("Artists - " + nextSong.getFeaturedArtistsInString());
        
    }

    @Override
    public void playSongById(String userId, String songId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFound("User not found"));
        
        List<Playlist> playlists = playListRepository.findAll().stream().filter(
                e ->  e.getUser().getId().equals(user.getId()))
                .collect(Collectors.toList());
        Playlist playlist = playlists.get(0);

        List<Song> songs = playlist.getSongList().stream().
        filter(e -> e.getId().compareTo(songId) == 0)
                .collect(Collectors.toList());
        if (songs.size() == 0) {
            System.out.println("Given song id is not a part of the active playlist");
        }
        else {
            Song song = songs.get(0);

            song.setStatus(SongStatus.PLAYING);
            
            System.out.println("Current Song Playing");
            System.out.println("Song - " + song.getSongName());
            System.out.println("Album - " + song.getAlbum());
            System.out.println("Artists - " + song.getFeaturedArtistsInString());
           
        }
        
       



    }

    void createMusicPlayer(Playlist playlist) {

        int size = playlist.getSongList().size();
        for (int i = 0; i < playlist.getSongList().size(); i++) {
            Song song = playlist.getSongList().get(i);
            List<Song> sList = new ArrayList<>();
            if (i == 0) {
                sList.add(playlist.getSongList().get(size - 1));
                sList.add(playlist.getSongList().get(i + 1));
            }
            else if (i == size - 1) {
                sList.add(playlist.getSongList().get(i - 1));
                sList.add(playlist.getSongList().get(0));
            }
            else {
                sList.add(playlist.getSongList().get(i - 1));
                sList.add(playlist.getSongList().get(i + 1));
            }
            musicPlayer.put(song, sList);
        }
        
    }
    


    
}
