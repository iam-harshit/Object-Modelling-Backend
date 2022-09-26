package com.crio.jukebox.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.Playlist;

public class PlayListRepository implements CRUDRepository<Playlist, String>{
    private final Map<String, Playlist> playlistMap;
    private Integer autoIncrement = 0;

    public PlayListRepository() {
        this.playlistMap = new HashMap<>();
    }

    public PlayListRepository(Map<String, Playlist> playlistMap) {
        this.autoIncrement = playlistMap.size();
        this.playlistMap = playlistMap;
    }

    @Override
    public Playlist save(Playlist entity) {
        if (entity.getId() == null) {
            autoIncrement++;
            Playlist playlist = new Playlist(Integer.toString(autoIncrement),
                    entity.getPlaylistName(), entity.getUser(), entity.getSongList(), entity.getIsActivePlayList());
            playlistMap.put(playlist.getId(), playlist);
            return playlist;
        } else {
            return playlistMap.get(entity.getId());

        }

    }

    @Override
    public boolean existsById(String id) {
        return (playlistMap.containsKey(id) == true ? true : false);
    }

    @Override
    public void deleteById(String id) {
        playlistMap.remove(id);
        
    }

    @Override
    public Optional<Playlist> findById(String id) {
        return Optional.ofNullable(playlistMap.get(id));
    }

    @Override
    public List<Playlist> findAll() {
        List<Playlist> playLists =
                playlistMap.entrySet().stream().map(e -> e.getValue()).collect(Collectors.toList());
        
        return playLists;
    }

    @Override
    public void delete(Playlist entity) {
        playlistMap.remove(entity.getId());
        
    }
    
    
}
