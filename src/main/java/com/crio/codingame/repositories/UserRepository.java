package com.crio.codingame.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.crio.codingame.entities.User;

public class UserRepository implements IUserRepository{

    private final Map<String,User> userMap;
    private Integer autoIncrement = 0;

    public UserRepository(){
        userMap = new HashMap<String,User>();
    }

    public UserRepository(Map<String, User> userMap) {
        this.userMap = userMap;
        this.autoIncrement = userMap.size();
    }

    @Override
    public User save(User entity) {
        if( entity.getId() == null ){
            autoIncrement++;
            User u = new User(Integer.toString(autoIncrement),entity.getName(),entity.getScore());
            userMap.put(u.getId(),u);
            return u;
        }
        userMap.put(entity.getId(),entity);
        return entity;
    }


    @Override
    public List<User> findAll() {
        List<User> users = userMap.entrySet().stream().map(e -> e.getValue()).collect(Collectors.toList());
        /*for(User user: users){
            System.out.println(user.getName());
        }*/
        return users;
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.ofNullable(userMap.get(id));
    }

    @Override
    public boolean existsById(String id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void delete(User entity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteById(String id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }


    @Override
    public Optional<User> findByName(String name) {
        List<User> users = userMap.entrySet().stream().map(e -> e.getValue())
                .filter(n -> n.getName().equalsIgnoreCase(name)).collect(Collectors.toList());
        /*for (User user : users) {
            System.out.println(user.getName());
        }*/
        Optional<User> listOptional = users.stream().findAny();
        return listOptional;
    }
    
}
