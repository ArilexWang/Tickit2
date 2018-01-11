package com.example.ricardo.tickit2.data.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ricardo on 2017/12/30.
 */

public class OrderFieldDto {
    @SerializedName("_showID")
    @Expose
    private String showID;
    @SerializedName("_studentID")
    @Expose
    private String studentID;
    @SerializedName("_status")
    @Expose
    private Integer status;
    @SerializedName("_actualPrice")
    @Expose
    private Integer actualPrice;
    @SerializedName("_createTime")
    @Expose
    private String createTime;

    public String getShowID() {
        return showID;
    }

    public void setShowID(String showID) {
        this.showID = showID;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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