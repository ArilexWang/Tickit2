package com.example.ricardo.tickit2.data.entity;

/**
 * Created by igulu on 13/11/2017.
 */

public class Event {
    private Long id;
    private String name;
    private String avatarPath;
    private String descriptionPath;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public String getDescriptionPath() {
        return descriptionPath;
    }

    public void setDescriptionPath(String descriptionPath) {
        this.descriptionPath = descriptionPath;
    }

    public Event(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
