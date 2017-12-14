package com.example.ricardo.tickit2.data.entity;

/**
 * Created by igulu on 13/11/2017.
 */

public class TicketType {
    private Long id;
    private Long parentShowID;
    private String name;
    private float price;
    private float discount = 1.0f;
    private int TotalAmount;
    private int experience;
    private boolean is_available = true;

    public TicketType(Long id, Long parentShowID, String name, float price, int totalAmount, int experience) {
        this.id = id;
        this.parentShowID = parentShowID;
        this.name = name;
        this.price = price;
        TotalAmount = totalAmount;
        this.experience = experience;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentShowID() {
        return parentShowID;
    }

    public void setParentShowID(Long parentShowID) {
        this.parentShowID = parentShowID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public int getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        TotalAmount = totalAmount;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }
}
