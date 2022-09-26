package com.crio.jukebox.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.repositories.SongRepository;
import com.crio.jukebox.entities.*;;

public class LoadSongService implements ILoadSongService {
    
    private final SongRepository songRepository;
    private String remove;

    public LoadSongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public void loadData(String fileName) {
       
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            
            while (line != null) {
                String[] data = line.split(",");
                List<String> artists = Arrays.asList(data[4].split("#"));
                if (data[3].compareTo(artists.get(0)) == 0) {
                    if (artists.size() == 1) {
                        List<String> artistList = new ArrayList<>();
                        Song song = new Song(data[0], data[1], data[2], data[3], artistList,
                                SongStatus.NOT_PLAYING);
                        songRepository.save(song);
                    }
                    else {
                        List<String> artistsList = new ArrayList<>(artists);
                        artistsList.remove(0);
                        Song song = new Song(data[0], data[1], data[2], data[3], artistsList,
                                SongStatus.NOT_PLAYING);
                        songRepository.save(song);
                    }
                   
                }
                else {
                    
                    Song song = new Song(data[0], data[1], data[2], data[3], artists,
                            SongStatus.NOT_PLAYING);
                    songRepository.save(song);

                } 
                line = reader.readLine();
            }
            reader.close();
            

        } catch (Exception e) {
            e.printStackTrace();

        }
        
    }
    
}
