package com.crio.jukebox.exceptions;

public class SongNotFound extends RuntimeException {

    public SongNotFound()
    {
     super();
    }

    public SongNotFound(String msg)
    {
     super(msg);
    }

}
