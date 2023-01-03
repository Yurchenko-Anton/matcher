package com.example.matcher.service;

import com.example.matcher.config.TestPLConfig;
import com.example.matcher.dto.CreateOrderDTO;
import com.example.matcher.repository.DistanceRepository;
import com.kenshoo.pl.entity.PLContext;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DistanceServiceTest {

    private final String START_POSITION = "Kosmonavtov";
    private final String FINISH_POSITION = "Junosty";

    private final TestPLConfig testPLConfig = new TestPLConfig();
    private final PLContext plContext = testPLConfig.configPL();

    private final DistanceRepository distanceRepository = new DistanceRepository(plContext);

    private final CalculateService calculateService = new CalculateService();

    private final DistanceService distanceService = new DistanceService(calculateService, distanceRepository);

    @Test
    void getDistance() {
        CreateOrderDTO createOrderDTO = new CreateOrderDTO(START_POSITION, FINISH_POSITION, null);

        assertEquals(820.1, distanceService.getDistance(createOrderDTO));
    }
}