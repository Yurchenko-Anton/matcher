package com.example.matcher.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class LocationsDTO {
    int id;
    String streetName;
    int xCoordinate;
    int yCoordinate;
}