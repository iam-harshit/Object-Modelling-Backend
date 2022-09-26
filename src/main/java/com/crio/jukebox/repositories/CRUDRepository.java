package com.crio.jukebox.repositories;

import java.util.List;
import java.util.Optional;

public interface CRUDRepository<T, ID> {
    
    public T save(T entity);

    boolean existsById(ID id);

    public  Optional<T> findById(ID id);

    public List<T> findAll();

    public void deleteById(ID id);

    public void delete(T entity);

}
    

