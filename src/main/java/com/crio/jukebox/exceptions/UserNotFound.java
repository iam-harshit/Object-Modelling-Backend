package com.crio.jukebox.exceptions;

public class UserNotFound extends RuntimeException{

    public UserNotFound()
    {
     super();
    }

    public UserNotFound(String msg)
    {
     super(msg);
    }
    
}
