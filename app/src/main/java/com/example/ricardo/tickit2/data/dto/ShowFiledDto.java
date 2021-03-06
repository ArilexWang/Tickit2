package com.example.ricardo.tickit2.data.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ricardo on 2017/12/26.
 */

public class ShowFiledDto {
    @SerializedName("_parentEventID")
    @Expose
    private String parentEventID;
    @SerializedName("_name")
    @Expose
    private String name;
    @SerializedName("_description")
    @Expose
    private String description;
    @SerializedName("_showTime")
    @Expose
    private String showTime;
    @SerializedName("_expiredTime")
    @Expose
    private String expiredTime;
    @SerializedName("_expiredFetchTime")
    @Expose
    private String expiredFetchTime;
    @SerializedName("_price")
    @Expose
    private Integer price;
    @SerializedName("_avatar")
    @Expose
    private String avatar;
    @SerializedName("_favoritesTimes")
    @Expose
    private Integer favoritesTimes;
    @SerializedName("_category")
    @Expose
    private Integer category;
    @SerializedName("_isOnSale")
    @Expose
    private Boolean isOnSale;
    @SerializedName("_isRestricted")
    @Expose
    private Boolean isRestricted;
    @SerializedName("_restrictionNum")
    @Expose
    private Integer restrictionNum;
    @SerializedName("_remainedAmount")
    @Expose
    private Integer remainedAmount;

    public String getParentEventID() {
        return parentEventID;
    }

    public void setParentEventID(String parentEventID) {
        this.parentEventID = parentEventID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public String getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(String expiredTime) {
        this.expiredTime = expiredTime;
    }

    public String getExpiredFetchTime() {
        return expiredFetchTime;
    }

    public void setExpiredFetchTime(String expiredFetchTime) {
        this.expiredFetchTime = expiredFetchTime;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getFavoritesTimes() {
        return favoritesTimes;
    }

    public void setFavoritesTimes(Integer favoritesTimes) {
        this.favoritesTimes = favoritesTimes;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Boolean getIsOnSale() {
        return isOnSale;
    }

    public void setIsOnSale(Boolean isOnSale) {
        this.isOnSale = isOnSale;
    }

    public Boolean getIsRestricted() {
        return isRestricted;
    }

    public void setIsRestricted(Boolean isRestricted) {
        this.isRestricted = isRestricted;
    }

    public Integer getRestrictionNum() {
        return restrictionNum;
    }

    public void setRestrictionNum(Integer restrictionNum) {
        this.restrictionNum = restrictionNum;
    }

    public Integer getRemainedAmount() {
        return remainedAmount;
    }

    public void setRemainedAmount(Integer remainedAmount) {
        this.remainedAmount = remainedAmount;
    }

}
