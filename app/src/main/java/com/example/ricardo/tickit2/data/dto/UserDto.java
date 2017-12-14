package com.example.ricardo.tickit2.data.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ricardo on 2017/11/28.
 */

public class UserDto {
    @SerializedName("model")
    @Expose
    private String model;
    @SerializedName("pk")
    @Expose
    private String pk;
    @SerializedName("fields")
    @Expose
    private FieldDto fields;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public FieldDto getFields() {
        return fields;
    }

    public void setFields(FieldDto fields) {
        this.fields = fields;
    }
}
