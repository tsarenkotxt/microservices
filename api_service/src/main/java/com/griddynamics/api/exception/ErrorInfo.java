package com.griddynamics.api.exception;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ErrorInfo {

    private long timestamp;
    private int status;
    private String error;
    private String message;

    public ErrorInfo(int status, String error, String message) {
        this.timestamp = new Timestamp(System.currentTimeMillis()).getTime();
        this.status = status;
        this.error = error;
        this.message = message;
    }

}
