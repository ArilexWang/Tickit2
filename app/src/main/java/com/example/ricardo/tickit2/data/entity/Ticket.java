package com.example.ricardo.tickit2.data.entity;

/**
 * Created by igulu on 13/11/2017.
 */

public class Ticket {
    private Long id;
    private Long parentTicketTypeID;
    private float actualPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentTicketTypeID() {
        return parentTicketTypeID;
    }

    public void setParentTicketTypeID(Long parentTicketTypeID) {
        this.parentTicketTypeID = parentTicketTypeID;
    }

    public float getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(float actualPrice) {
        this.actualPrice = actualPrice;
    }
}
