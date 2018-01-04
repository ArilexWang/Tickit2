package com.example.ricardo.tickit2.data.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ricardo on 2018/1/3.
 */

public class TicketDto {
    @SerializedName("model")
    @Expose
    private String model;
    @SerializedName("pk")
    @Expose
    private String pk;
    @SerializedName("fields")
    @Expose
    private TicketFieldDto fields;

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

    public TicketFieldDto getFields() {
        return fields;
    }

    public void setFields(TicketFieldDto fields) {
        this.fields = fields;
    }
}
