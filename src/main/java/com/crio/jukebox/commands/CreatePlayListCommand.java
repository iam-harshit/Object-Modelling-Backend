package com.crio.jukebox.commands;

import java.util.LinkedList;
import java.util.List;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.services.IPlayListService;

public class CreatePlayListCommand implements ICommand {
    
    private final IPlayListService playListService;

    public CreatePlayListCommand(IPlayListService playListService) {
        this.playListService = playListService;
    }

    @Override
    public void execute(List<String> tokens) {
        String userId = tokens.get(1);
        String playlistName = tokens.get(2);
        List<String> songIdList = new LinkedList<>();

        for (int i = 3; i < tokens.size(); i++) {
            songIdList.add(tokens.get(i));
        }
        
        Playlist playlist = playListService.createPlaylist(userId, playlistName, songIdList);
        //System.out.println();
        //System.out.println("Playlist ID - " + playlist.getId()+"\n");
        System.out.println(playlist.toString());
        
    }
    
    
}