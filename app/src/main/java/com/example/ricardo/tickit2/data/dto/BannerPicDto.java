package com.example.ricardo.tickit2.data.dto;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Created by Ricardo on 2017/12/26.
 */

public class BannerPicDto {
    @SerializedName("model")
    @Expose
    private String model;
    @SerializedName("pk")
    @Expose
    private Integer pk;
    @SerializedName("fields")
    @Expose
    private BannerFiledDto fields;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    public BannerFiledDto getFields() {
        return fields;
    }

    public void setFields(BannerFiledDto fields) {
        this.fields = fields;
    }

}
