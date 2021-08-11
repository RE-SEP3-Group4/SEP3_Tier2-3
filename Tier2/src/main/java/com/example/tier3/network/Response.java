package com.example.tier3.network;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Response implements Serializable {

    private static final long serialVersionUID = 1l;

    public enum ResponseStatus {
        OK, NOT_FOUND, ERROR
    }

    private ResponseStatus status;
    private String payload;
}
