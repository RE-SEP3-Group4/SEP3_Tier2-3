package com.example.tier3.network;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Request implements Serializable {

    private static final long serialVersionUID = 1l;

    public enum RequestOperation {
        CREATE, GET, UPDATE, DELETE
    }

    private String address;
    private RequestOperation operation;
    private String payload;
}
