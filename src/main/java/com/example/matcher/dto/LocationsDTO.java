package com.example.matcher.dto;

import lombok.Value;

@Value
public class LocationsDTO {

    int id;

    String streetName;

    int xCoordinate;

    int yCoordinate;
}