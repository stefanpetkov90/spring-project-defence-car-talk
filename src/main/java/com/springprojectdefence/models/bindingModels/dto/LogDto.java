package com.springprojectdefence.models.bindingModels.dto;

import java.util.Date;

public class LogDto {
    private String message;
    private Date date;

    public LogDto(String message) {
        this.message = message;
        this.date = new Date();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
