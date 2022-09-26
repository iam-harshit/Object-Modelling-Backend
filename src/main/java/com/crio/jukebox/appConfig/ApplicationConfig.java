package com.crio.jukebox.appConfig;

import com.crio.jukebox.services.PlayListService;
import com.crio.jukebox.services.PlaySongService;
import com.crio.jukebox.services.ILoadSongService;
import com.crio.jukebox.services.IPlayListService;
import com.crio.jukebox.services.IPlaySongService;
import com.crio.jukebox.services.IUserService;
import com.crio.jukebox.services.LoadSongService;

import com.crio.jukebox.services.UserService;

import com.crio.jukebox.commands.CommandInvoker;
import com.crio.jukebox.commands.CreatePlayListCommand;
import com.crio.jukebox.commands.CreateUserCommand;
import com.crio.jukebox.commands.DeletePlayListCommand;
import com.crio.jukebox.commands.LoadSongCommand;
import com.crio.jukebox.commands.ModifyPlayListCommand;
import com.crio.jukebox.commands.PlayPlayListCommand;
import com.crio.jukebox.commands.PlaySongCommand;
import com.crio.jukebox.repositories.PlayListRepository;
import com.crio.jukebox.repositories.SongRepository;
import com.crio.jukebox.repositories.UserRepository;


public class ApplicationConfig {

        //repositories
        private final UserRepository userRepository = new UserRepository();
        private final SongRepository songRepository = new SongRepository();
        private final PlayListRepository playListRepository = new PlayListRepository();

        //services
        private final ILoadSongService loadSongService = new LoadSongService(songRepository);
        private final IUserService userService = new UserService(userRepository);
        private final IPlayListService createPlayListService = new PlayListService(
                        userRepository, songRepository, playListRepository);
        private final IPlayListService deletePlayListService =
                        new PlayListService(userRepository, songRepository, playListRepository);
        private final IPlayListService modifyPlayListService =
                        new PlayListService(userRepository, songRepository, playListRepository);
        private final IPlayListService playPlayListSongService =
                        new PlayListService(userRepository, songRepository, playListRepository);
        private final IPlaySongService playSongService =
                        new PlaySongService(userRepository, songRepository, playListRepository);

        //commands
        private final LoadSongCommand loadSongCommand = new LoadSongCommand(loadSongService);
        private final CreateUserCommand createUserComamnd = new CreateUserCommand(userService);
        private final CreatePlayListCommand createPlayListCommand =
                        new CreatePlayListCommand(createPlayListService);
        private final DeletePlayListCommand deletePlayListCommand =
                        new DeletePlayListCommand(deletePlayListService);
        private final PlayPlayListCommand playPlayListCommand =
                        new PlayPlayListCommand(playPlayListSongService);

        private final ModifyPlayListCommand modifyPlayListCommand =
                        new ModifyPlayListCommand(modifyPlayListService);
        private final PlaySongCommand playSongCommand = new PlaySongCommand(playSongService);
       


private final CommandInvoker commandInvoker = new CommandInvoker();

    public CommandInvoker getCommandInvoker() {
            commandInvoker.register("LOAD-DATA", loadSongCommand);
            commandInvoker.register("CREATE-USER", createUserComamnd);
            commandInvoker.register("CREATE-PLAYLIST", createPlayListCommand);
            commandInvoker.register("DELETE-PLAYLIST", deletePlayListCommand);
            commandInvoker.register("PLAY-PLAYLIST", playPlayListCommand);
            commandInvoker.register("MODIFY-PLAYLIST", modifyPlayListCommand);
            commandInvoker.register("PLAY-SONG", playSongCommand);
            return commandInvoker;
    }

}
