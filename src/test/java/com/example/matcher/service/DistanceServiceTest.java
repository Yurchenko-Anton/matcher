package com.example.matcher.service;

import com.example.matcher.BaseTest;
import com.example.matcher.dto.CreateOrderDTO;
import com.example.matcher.repository.DistanceRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DistanceServiceTest extends BaseTest {

    private static final String START_POSITION = "Kosmonavtov";
    private static final String FINISH_POSITION = "Junosty";

    private final DistanceRepository distanceRepository = new DistanceRepository(PL_CONTEXT);

    private final CalculateService calculateService = new CalculateService();

    private final DistanceService distanceService = new DistanceService(calculateService, distanceRepository);

    @Test
    void getDistance() {
        CreateOrderDTO createOrderDTO = new CreateOrderDTO(START_POSITION, FINISH_POSITION);

        assertEquals(820.1, distanceService.getDistance(createOrderDTO));
    }
}