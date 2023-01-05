package com.example.matcher.service;

import com.example.matcher.dto.DriversLocationsDTO;
import com.example.matcher.repository.DistanceDriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DriversDistanceService {

    private final DistanceDriverRepository distanceDriverRepository;

    public int setLocations(DriversLocationsDTO driversLocationsDTO) {
        return distanceDriverRepository.setLocations(driversLocationsDTO);
    }
}