package com.example.matcher.web;

import com.example.matcher.dto.CreateOrderDTO;
import com.example.matcher.service.DistanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/distance")
@RequiredArgsConstructor
public class Distance {

    private final DistanceService distanceService;

    @GetMapping
    public Double getDistance(CreateOrderDTO createOrderDTO){
        return distanceService.getDistance(createOrderDTO);
    }
}