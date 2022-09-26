package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.services.IPlayListService;

public class PlayPlayListCommand implements ICommand {
    private final IPlayListService playListService;

    public PlayPlayListCommand (IPlayListService playListService) {
        this.playListService = playListService;
    }

    @Override
    public void execute(List<String> tokens) {
        String userId = tokens.get(1);
        String playListId = tokens.get(2);
        playListService.playPlayListSong(userId, playListId);
       
        
    }
}
