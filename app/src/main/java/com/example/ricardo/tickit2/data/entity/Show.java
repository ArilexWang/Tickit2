package com.example.ricardo.tickit2.data.entity;

/**
 * Created by igulu on 13/11/2017.
 */

public class Show {
    private Long id;
    private Long parentEventID;
    private String name;
    private String avatarPath;
    private String descriptionPath;
    private Boolean is_OnSale = false;

    Show(Long id, Long parentEventID, String name) {
        this.id = id;
        this.parentEventID = parentEventID;
        this.name = name;
    }

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public Long getParentEventID() {return parentEventID;}

    public void setParentEventID(Long parentEventID) {this.parentEventID = parentEventID;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public Boolean getIs_OnSale() {return is_OnSale;}

    public void setIs_OnSale(Boolean is_OnSale) {this.is_OnSale = is_OnSale;}
}
