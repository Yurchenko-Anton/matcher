package com.example.matcher.web;

import com.example.matcher.dto.DriversLocationsDTO;
import com.example.matcher.service.DriversDistanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/drivers/distance")
public class DriversDistance {

    private final DriversDistanceService driversDistanceService;

    @PostMapping
    public int setLocations(@RequestBody DriversLocationsDTO driversLocationsDTO){
        return driversDistanceService.setLocations(driversLocationsDTO);
    }

    @GetMapping("/{clientPosition}")
    public Integer getNearestDriver(@PathVariable String clientPosition){
        return driversDistanceService.getNearestDriver(clientPosition);
    }
}