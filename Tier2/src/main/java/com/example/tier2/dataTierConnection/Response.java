package com.example.tier2.dataTierConnection;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response {
    public enum ResponseStatus {
        OK, NOT_FOUND, ERROR
    }

    private ResponseStatus status;
    private String payload;
}
