package com.crio.jukebox.commands;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.crio.jukebox.entities.Artist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.repositories.SongRepository;
import com.crio.jukebox.services.ILoadSongService;

public class LoadSongCommand implements ICommand {
    private final ILoadSongService loadSongService;

    public LoadSongCommand(ILoadSongService loadSongService) {  
        this.loadSongService = loadSongService;
    }

    @Override
    public void execute(List<String> tokens) {
        loadSongService.loadData(tokens.get(1));
        System.out.println("Songs Loaded successfully");
        //System.out.println();
        
    }
    
}
