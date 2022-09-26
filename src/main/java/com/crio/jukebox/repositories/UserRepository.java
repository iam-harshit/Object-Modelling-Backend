package com.crio.jukebox.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.User;

public class UserRepository implements CRUDRepository<User, String> {

    private final Map<String, User> userMap;
    private Integer autoIncrement = 0;

    public UserRepository(){
        this.userMap = new HashMap<>();
    }

    public UserRepository(Map<String, User> userMap){
        this.userMap = userMap;
        this.autoIncrement = userMap.size();
    }

    public User save(User entity) {
        if (entity.getId() == null) {
            autoIncrement++;
            User user = new User(Integer.toString(autoIncrement), entity.getUserName(), entity.getPlayList());
            userMap.put(user.getId(), user);
            return user;
        } else {
            return userMap.get(entity.getId());

        }

    }

    @Override
    public boolean existsById(String id) {
        return (userMap.containsKey(id) ? true : false);
    }

    @Override
    public void deleteById(String id) {
        userMap.remove(id);
    }

    @Override
    public Optional<User> findById(String id) {
        // TODO Auto-generated method stub
       return Optional.ofNullable(userMap.get(id));
    }

    @Override
    public List<User> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(User entity) {
        // TODO Auto-generated method stub
        
    }

    
}
