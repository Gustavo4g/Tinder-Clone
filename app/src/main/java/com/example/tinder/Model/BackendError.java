package com.example.tinder.Model;

import com.google.gson.annotations.SerializedName;

public class BackendError {
    @SerializedName("entityName")
    private String entityName;
    @SerializedName("errorKey")
    private String errorKey;
    @SerializedName("type")
    private String type;
    @SerializedName("title")
    private String title;
    @SerializedName("status")
    private Integer status;
    @SerializedName("message")
    private String message;
    @SerializedName("params")
    private String params;
    @SerializedName("detail")
    private String detail;

    public String getDetail() {
        return detail;
    }

    public String getEntityName() {
        return entityName;
    }

    public String getErrorKey() {
        return errorKey;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getParams() {
        return params;
    }
}