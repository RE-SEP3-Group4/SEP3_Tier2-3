package com.example.tier2.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreatePaymentDTO {
    private int userID;
    private String startDate, endDate;
}
