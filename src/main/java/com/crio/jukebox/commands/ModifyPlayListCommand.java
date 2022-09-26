package com.crio.jukebox.commands;

import java.util.LinkedList;
import java.util.List;
import com.crio.jukebox.services.IPlayListService;

public class ModifyPlayListCommand implements ICommand {
    
    private final IPlayListService playListService;

    public ModifyPlayListCommand (IPlayListService playListService) {
        this.playListService = playListService;
    }

    @Override
    public void execute(List<String> tokens) {
        String action = tokens.get(1);
        String userId = tokens.get(2);
        String playlistId = tokens.get(3);
        List<String> songIdList = new LinkedList<>();

        for (int i = 4; i < tokens.size(); i++) {
            songIdList.add(tokens.get(i));
        }

        if (action.compareTo("ADD-SONG") == 0) {
            playListService.addSongToPlayList(userId, playlistId, songIdList);
        }

        if (action.compareTo("DELETE-SONG") == 0) {
            playListService.deleteSongFromPlaylist(userId, playlistId, songIdList);
        }
    }
    
}
