package com.example.tier2.dataTierConnection;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Request {
    public enum RequestOperation {
        CREATE, GET, UPDATE, DELETE
    }

    private String address;
    private RequestOperation operation;
    private String payload;
}
