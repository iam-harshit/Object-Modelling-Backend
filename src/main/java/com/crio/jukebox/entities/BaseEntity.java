package com.crio.jukebox.entities;

public abstract class BaseEntity {
    protected String id;
    protected String createdDate;
    protected String modifiedDate;
    protected String version;

    public String getId() {
        return id;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public String getVersion() {
        return version;
    }
    
}
