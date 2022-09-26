package com.crio.jukebox.exceptions;

public class PlayListIsEmpty  extends RuntimeException{
    public PlayListIsEmpty()
    {
     super();
    }

    public PlayListIsEmpty(String msg)
    {
     super(msg);
    }

}
