package com.example.ricardo.tickit2.data.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ricardo on 2017/12/30.
 */

public class OrderFieldDto {
    @SerializedName("_ticketTypeID")
    @Expose
    private String ticketTypeID;
    @SerializedName("_studentID")
    @Expose
    private String studentID;
    @SerializedName("_isOpen")
    @Expose
    private Boolean isOpen;
    @SerializedName("_actualPrice")
    @Expose
    private Integer actualPrice;
    @SerializedName("_createTime")
    @Expose
    private String createTime;

    public String getTicketTypeID() {
        return ticketTypeID;
    }

    public void setTicketTypeID(String ticketTypeID) {
        this.ticketTypeID = ticketTypeID;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public Boolean getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Boolean isOpen) {
        this.isOpen = isOpen;
    }

    public Integer getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(Integer actualPrice) {
        this.actualPrice = actualPrice;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

}
