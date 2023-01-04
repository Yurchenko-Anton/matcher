package com.example.matcher.service;

import com.example.matcher.dto.CreateOrderDTO;
import com.example.matcher.dto.LocationsDTO;
import com.example.matcher.repository.DistanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DistanceService {

    private final CalculateService calculateService;
    private final DistanceRepository distanceRepository;

    public Double getDistance(CreateOrderDTO createOrderDTO){
        LocationsDTO startLocations = distanceRepository.getLocations(createOrderDTO.getStartPosition());
        LocationsDTO finishLocations = distanceRepository.getLocations(createOrderDTO.getFinishPosition());

        return calculateService.calculateDistance(startLocations, finishLocations);
    }
}