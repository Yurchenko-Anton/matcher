package com.example.matcher.service;

import com.example.matcher.dto.DriversLocationsDTO;
import com.example.matcher.dto.LocationsDTO;
import com.example.matcher.repository.DistanceDriverRepository;
import com.example.matcher.repository.DistanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.TreeMap;

@Service
@RequiredArgsConstructor
public class DriversDistanceService {

    private final DistanceDriverRepository distanceDriverRepository;
    private final DistanceRepository distanceRepository;
    private final CalculateService calculateService;

    public int setLocations(DriversLocationsDTO driversLocationsDTO) {
        LocationsDTO locations = distanceRepository.getLocations(driversLocationsDTO.getStreetName());
        return distanceDriverRepository.setLocations(locations,driversLocationsDTO);
    }

    public Integer getNearestDriver(String clientLocations) {
        LocationsDTO clientLocation = distanceRepository.getLocations(clientLocations);

        List<LocationsDTO> locations = distanceDriverRepository.getLocationsNearClient(clientLocation.getXCoordinate(), clientLocation.getYCoordinate());

        TreeMap<Double, LocationsDTO> distanceLocations = countDistanceDriverClient(locations, clientLocation);

        return distanceDriverRepository.getNearestDriver(distanceLocations);
    }

    private TreeMap<Double, LocationsDTO> countDistanceDriverClient(List<LocationsDTO> locationsDTOS, LocationsDTO clientLocations){
        TreeMap<Double, LocationsDTO> distanceLocations = new TreeMap<>();

        for (LocationsDTO locations : locationsDTOS){
            distanceLocations.put(calculateService.calculateDistance(locations, clientLocations),locations);
        }

        return distanceLocations;
    }
}