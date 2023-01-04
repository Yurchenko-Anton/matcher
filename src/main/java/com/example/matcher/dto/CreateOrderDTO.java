package com.example.matcher.dto;

import lombok.Value;

@Value
public class CreateOrderDTO {
    String startPosition;
    String finishPosition;
    Long guestId;
}