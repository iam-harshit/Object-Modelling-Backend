package com.crio.jukebox.services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.SongStatus;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.exceptions.PlayListIsEmpty;
import com.crio.jukebox.exceptions.PlayListNotFound;
import com.crio.jukebox.exceptions.SongNotFound;
import com.crio.jukebox.exceptions.UserNotFound;
import com.crio.jukebox.repositories.PlayListRepository;
import com.crio.jukebox.repositories.SongRepository;
import com.crio.jukebox.repositories.UserRepository;

public class PlayListService implements IPlayListService {
    private final UserRepository userRepository;
    private final SongRepository songRepository;
    private final PlayListRepository playListRepository;

    public PlayListService(UserRepository userRepository, SongRepository songRepository, PlayListRepository playListRepository)
     {
        this.songRepository = songRepository;
        this.userRepository = userRepository;
        this.playListRepository = playListRepository;
    }

    @Override
    public void deletePlayList(String userId, String playlistId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFound("User not found"));
        Playlist playlist = playListRepository.findById(playlistId).orElseThrow(() -> new PlayListNotFound(
                "Playlist Not Found"));

        if (playlist.getUser().getId().equals(user.getId()) == false) {
            throw new PlayListNotFound("Playlist Not Found");
        }
        else {
            playListRepository.deleteById(playlistId);
        }
        
    }

    @Override
    public Playlist createPlaylist(String userId, String playlistName, List<String> songIdList) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFound("User not found"));
        List<Song> songs = new LinkedList<>();

        //creating list of songs with given song list ids
        for (String songId : songIdList) {

            Song song = songRepository.findById(songId).orElseThrow(() -> new SongNotFound(
                    "Some Requested Songs Not Available. Please try again."));
            songs.add(song);

        }
        
       
        Playlist playlist = new Playlist(playlistName, user, songs, false);
        return playListRepository.save(playlist);

        //return playlist;
    }

    @Override
    public void addSongToPlayList(String userId, String playlistId, List<String> songIdList) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFound("User not found"));

        Playlist playlist = playListRepository.findById(playlistId).orElseThrow(() -> new PlayListNotFound(
                "Playlist Not Found"));
        
        for(String songId : songIdList){
            List<String> songIds = playlist.getSongList().stream().map(i -> i.getId())
                    .collect(Collectors.toList());
            ArrayList<String> idList= new ArrayList<>(songIds);
            /*If the song is already present in the playlist, so we need to skip that */
            if(idList.contains(songId) == true){
                continue;
            }
            else {
                Song song = songRepository.findById(songId).orElseThrow(() -> new SongNotFound(
                        "Some Requested Songs Not Available. Please try again."));
                playlist.getSongList().add(song);
            }
           
            System.out.println("Playlist ID - " + playlist.getId());
            System.out.println("Playlist Name - " + playlist.getPlaylistName());
            System.out.println("Song IDs - " + playlist.getSongIdsToString());
            
           
        }
        
        
        
        
    }

    @Override
    public void deleteSongFromPlaylist(String userId, String playlistId, List<String> songIdList) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFound("User not found"));

        Playlist playlist = playListRepository.findById(playlistId)
                .orElseThrow(() -> new PlayListNotFound("Playlist Not Found"));

        for (String songId : songIdList) {
            List<String> songIds = playlist.getSongList().stream().map(i -> i.getId())
                    .collect(Collectors.toList());
            ArrayList<String> idList = new ArrayList<>(songIds);
            
            if (idList.contains(songId) == false) {
                throw new SongNotFound("Some Requested Songs for Deletion are not present in the playlist. Please try again.");
            } else {
                int i;
                for (i = 0; i < songIds.size(); i++) {
                    if (songIds.get(i).compareTo(songId) == 0) {
                        break;
                    }
                }
                playlist.getSongList().remove(i);
            }


            System.out.println("Playlist ID - " + playlist.getId());
            System.out.println("Playlist Name - " + playlist.getPlaylistName());
            System.out.println("Song IDs - " + playlist.getSongIdsToString());
            
        }
        
    }

    @Override
    public void playPlayListSong(String userId, String playListId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFound("User not found"));

        Playlist playlist = playListRepository.findById(playListId)
                .orElseThrow(() -> new PlayListNotFound("Playlist Not Found"));

        playlist.setActivePlayList(true);

        Song song = playlist.getSongList().get(0);
        
        System.out.println("Current Song Playing");
        System.out.println("Song - " + song.getSongName());
        System.out.println("Album - " + song.getAlbum());
        System.out.println("Artists - " + song.getFeaturedArtistsInString());
        
    }

    @Override
    public List<Song> getPlayListSongs(String playlistId) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
