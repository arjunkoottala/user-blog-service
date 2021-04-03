package com.asajayan.user.rest.error;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
@Data
public class ErrorNode implements Serializable {

    @JsonIgnore
    private ErrorCode error;
    private String message;

    private ErrorNode() {
    }

    public ErrorNode(ErrorCode error, String message) {
        this();
        this.message = message;
        this.error = error;
    }


    @Override
    public String toString(){
        return "ErrorNode{"+
                "error=" + error +
                ", errorDescription='" + message + '\'' +
                "}";
    }
}
