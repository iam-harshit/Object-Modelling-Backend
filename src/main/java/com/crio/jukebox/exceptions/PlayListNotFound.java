package com.crio.jukebox.exceptions;

public class PlayListNotFound extends RuntimeException{

    public PlayListNotFound()
    {
     super();
    }

    public PlayListNotFound(String msg)
    {
     super(msg);
    }
    
}