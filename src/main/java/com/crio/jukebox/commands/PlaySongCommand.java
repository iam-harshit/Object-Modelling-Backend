package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.services.IPlaySongService;

public class PlaySongCommand implements ICommand {
    
    private final IPlaySongService playSongService;

    public PlaySongCommand(IPlaySongService playSongService) {
        this.playSongService = playSongService;
    }

    @Override
    public void execute(List<String> tokens) {
        String userId = tokens.get(1);
        String action = tokens.get(2);

        if (action.compareTo("BACK") == 0) {
            playSongService.playPreviousSong(userId);
        }
        else if (action.compareTo("NEXT") == 0) {
            playSongService.playNextSong(userId);
        }
        else {
            playSongService.playSongById(userId, action);
        }

    }
    
}
