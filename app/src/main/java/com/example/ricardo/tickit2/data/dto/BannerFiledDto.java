package com.example.ricardo.tickit2.data.dto;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Created by Ricardo on 2017/12/26.
 */

public class BannerFiledDto {
    @SerializedName("_bannerPictureID")
    @Expose
    private String bannerPictureID;
    @SerializedName("_isOnDisplay")
    @Expose
    private Boolean isOnDisplay;
    @SerializedName("_pictureURL")
    @Expose
    private String pictureURL;
    @SerializedName("_descriptionURL")
    @Expose
    private String descriptionURL;

    public String getBannerPictureID() {
        return bannerPictureID;
    }

    public void setBannerPictureID(String bannerPictureID) {
        this.bannerPictureID = bannerPictureID;
    }

    public Boolean getIsOnDisplay() {
        return isOnDisplay;
    }

    public void setIsOnDisplay(Boolean isOnDisplay) {
        this.isOnDisplay = isOnDisplay;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public String getDescriptionURL() {
        return descriptionURL;
    }

    public void setDescriptionURL(String descriptionURL) {
        this.descriptionURL = descriptionURL;
    }
}
