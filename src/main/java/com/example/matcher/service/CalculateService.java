package com.example.matcher.service;

import com.example.matcher.dto.LocationsDTO;
import org.springframework.stereotype.Service;

@Service
public class CalculateService {

    private static final int SQUARE = 2;
    private static final double SQUARE_ROOT = 0.5;
    private static final int ROUND = 10;

    public Double calculateDistance(LocationsDTO startLocations, LocationsDTO finishLocations){
        double result = Math.pow((Math.pow(startLocations.getXCoordinate()- finishLocations.getXCoordinate(),SQUARE)
                + Math.pow(startLocations.getYCoordinate()-finishLocations.getYCoordinate(),SQUARE)),SQUARE_ROOT);
        result= round(result);
        return result;
    }

    private double round(Double result){
        return Math.ceil(result*ROUND)/ROUND;
    }
}