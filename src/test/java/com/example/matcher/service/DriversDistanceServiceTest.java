package com.example.matcher.service;

import com.example.matcher.BaseTest;
import com.example.matcher.dto.DriversLocationsDTO;
import com.example.matcher.persistence.DriversLocationsEntity;
import com.example.matcher.repository.DistanceRepository;
import com.example.matcher.repository.DriversDistanceRepository;
import com.example.matcher.repository.DriversLocationsPersistence;
import com.example.matcher.table.schema.DriversLocations;
import org.jooq.impl.DSL;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DriversDistanceServiceTest extends BaseTest {

    private static final int DRIVER_ID = 3;
    private static final String STREET_NAME = "Junosty";

    private final CalculateService calculateService = new CalculateService();
    private final DistanceRepository distanceRepository = new DistanceRepository(PL_CONTEXT);
    private static final DriversLocationsPersistence driversLocationsPersistence = new DriversLocationsPersistence(PL_CONTEXT);
    private final DriversDistanceRepository driversDistanceRepository = new DriversDistanceRepository(PL_CONTEXT, driversLocationsPersistence);
    private final DriversDistanceService driversDistanceService = new DriversDistanceService(driversDistanceRepository, distanceRepository, calculateService);

    @AfterAll
    static void deleteTestData() {
        PL_CONTEXT.dslContext()
                .delete(DriversLocations.TABLE)
                .where(DSL.field(DriversLocationsEntity.DRIVER_ID.toString()).eq(DRIVER_ID))
                .execute();
    }

    @Test
    void shouldSetLocations() {
        DriversLocationsDTO driversLocationsDTO = new DriversLocationsDTO(DRIVER_ID, STREET_NAME);

        assertEquals(3, driversDistanceService.setLocations(driversLocationsDTO));
    }

    @Test
    void shouldGetNearestDriverId() {
        assertEquals(ResponseEntity.ok(3), driversDistanceService.getNearestDriverIdToClient(STREET_NAME));
    }
}