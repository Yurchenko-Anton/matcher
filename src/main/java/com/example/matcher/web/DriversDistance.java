package com.example.matcher.web;

import com.example.matcher.dto.DriversLocationsDTO;
import com.example.matcher.dto.DriversStatusDTO;
import com.example.matcher.service.DriversDistanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/drivers/distance")
public class DriversDistance {

    private final DriversDistanceService driversDistanceService;

    @PostMapping
    public int setLocations(@RequestBody DriversLocationsDTO driversLocationsDTO) {
        return driversDistanceService.setLocations(driversLocationsDTO);
    }

    @GetMapping("/{clientStreetName}")
    public ResponseEntity<Integer> getNearestDriverIdToClient(@PathVariable String clientStreetName) {
        return driversDistanceService.getNearestDriverIdToClient(clientStreetName);
    }

    @PostMapping("/status")
    public ResponseEntity<String> setStatus(@RequestBody DriversStatusDTO driversStatusDTO){
        return driversDistanceService.setStatus(driversStatusDTO);
    }
}