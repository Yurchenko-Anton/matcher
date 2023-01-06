package com.example.matcher.service;

import com.example.matcher.dto.DriversLocationsDTO;
import com.example.matcher.dto.LocationsDTO;
import com.example.matcher.repository.DistanceRepository;
import com.example.matcher.repository.DriversDistanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DriversDistanceService {

    private final DriversDistanceRepository driversDistanceRepository;
    private final DistanceRepository distanceRepository;
    private final CalculateService calculateService;

    public int setLocations(DriversLocationsDTO driversLocationsDTO) {
        LocationsDTO locations = distanceRepository.getLocations(driversLocationsDTO.getStreetName());
        return driversDistanceRepository.setLocations(locations, driversLocationsDTO);
    }

    public Integer getNearestDriverToClient(String clientStreetName) {
        LocationsDTO clientLocation = distanceRepository.getLocations(clientStreetName);
        List<LocationsDTO> locationsNearClient = driversDistanceRepository.getLocationsNearClient(clientLocation);

        LocationsDTO nearestLocation = getNearestLocationWithDrivers(locationsNearClient, clientLocation);

        return driversDistanceRepository.getDriverId(nearestLocation).stream().findFirst().orElseThrow();
    }

    private LocationsDTO getNearestLocationWithDrivers(List<LocationsDTO> locationsNearClient, LocationsDTO clientLocation) {
        return locationsNearClient.stream()
                .filter(locations -> !driversDistanceRepository.getDriverId(locations).isEmpty())
                .map(locations -> {
                    Double distance = calculateService.calculateDistance(locations, clientLocation);

                    return new HashMap<LocationsDTO, Double>() {{
                        put(locations, distance);
                    }};
                })
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.summingDouble(Map.Entry::getValue)))
                .entrySet().stream()
                .min(Map.Entry.comparingByValue()).orElseThrow(NullPointerException::new).getKey();
    }
}