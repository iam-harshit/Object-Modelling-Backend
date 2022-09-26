package com.crio.jukebox.repositories;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.Song; 

public class SongRepository implements CRUDRepository <Song, String>{
    
    private final Map<String, Song> songMap;
    private Integer autoIncrement = 0;
  
    public SongRepository() {
        this.songMap = new LinkedHashMap<>();
    }

    public SongRepository(Map<String, Song> songMap) {
        this.songMap = songMap;
        this.autoIncrement = songMap.size();
    }

    @Override
    public Song save (Song entity) {
        
        if (entity.getId() == null) {
            autoIncrement++;
            Song song = new Song(Integer.toString(autoIncrement), entity.getSongName(),
                    entity.getGenre(), entity.getAlbum(), entity.getOwnerArtist(),
                    entity.getFeaturedArtists(), entity.getStatus());
            songMap.put(song.getId(), song);
            return song;
        } else {
            return songMap.get(entity.getId());

        }
        
    }

    @Override
    public boolean existsById(String id) {
       return (songMap.containsKey(id) == true ? true : false);
    }

    @Override
    public Optional<Song> findById(String id) {
        return Optional.ofNullable(songMap.get(id));
    }

    @Override
    public List<Song> findAll() {
        List<Song> song =
                songMap.entrySet().stream().map(e -> e.getValue()).collect(Collectors.toList());
        return song;
    }

    @Override
    public void deleteById(String id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void delete(Song entity) {
        // TODO Auto-generated method stub
        
    }
  
}
