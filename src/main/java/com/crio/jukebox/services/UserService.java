package com.crio.jukebox.services;

import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.repositories.*;

public class UserService implements IUserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(String name) {
        List<Playlist> playlists = new ArrayList<>();
        User user = new User(name, playlists);
        return userRepository.save(user);
    }


    
}
