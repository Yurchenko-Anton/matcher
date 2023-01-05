package com.example.matcher.service;

import com.example.matcher.dto.DriversLocationsDTO;
import com.example.matcher.dto.LocationsDTO;
import com.example.matcher.repository.DriversDistanceRepository;
import com.example.matcher.repository.DistanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

@Service
@RequiredArgsConstructor
public class DriversDistanceService {

    private final DriversDistanceRepository driversDistanceRepository;
    private final DistanceRepository distanceRepository;
    private final CalculateService calculateService;

    public int setLocations(DriversLocationsDTO driversLocationsDTO) {
        LocationsDTO locations = distanceRepository.getLocations(driversLocationsDTO.getStreetName());
        return driversDistanceRepository.setLocations(locations,driversLocationsDTO);
    }

    public Integer getNearestDriver(String clientLocations) {
        LocationsDTO clientLocation = distanceRepository.getLocations(clientLocations);
        List<LocationsDTO> locations = driversDistanceRepository.getLocationsNearClient(clientLocation.getXCoordinate(), clientLocation.getYCoordinate());
        TreeMap<Double, LocationsDTO> distanceLocations = countDistanceDriverClient(locations, clientLocation);

        List<Integer> id = new ArrayList<>();

        for (LocationsDTO location : distanceLocations.values()) {
            id.addAll(driversDistanceRepository.getDriverId(location));
            if (!id.isEmpty()) {
                break;
            }
        }

        return id.stream().findFirst().orElseThrow();
    }

    private TreeMap<Double, LocationsDTO> countDistanceDriverClient(List<LocationsDTO> locationsDTOS, LocationsDTO clientLocations){
        TreeMap<Double, LocationsDTO> distanceLocations = new TreeMap<>();

        for (LocationsDTO locations : locationsDTOS){
            distanceLocations.put(calculateService.calculateDistance(locations, clientLocations),locations);
        }

        return distanceLocations;
    }
}