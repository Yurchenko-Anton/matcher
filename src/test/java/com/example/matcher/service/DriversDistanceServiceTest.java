package com.example.matcher.service;

import com.example.matcher.BaseTest;
import com.example.matcher.dto.DriversLocationsDTO;
import com.example.matcher.persistence.DriversLocationsEntity;
import com.example.matcher.repository.DistanceDriverRepository;
import com.example.matcher.repository.DriversLocationsPersistence;
import com.example.matcher.table.schema.DriversLocations;
import com.kenshoo.pl.entity.PLContext;
import org.jooq.impl.DSL;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DriversDistanceServiceTest extends BaseTest {

    private static final int DRIVER_ID = 3;
    private static final String STREET_NAME = "Junosty";

    private final DriversLocationsPersistence driversLocationsPersistence = new DriversLocationsPersistence(PL_CONTEXT);
    private final DistanceDriverRepository distanceDriverRepository = new DistanceDriverRepository(PL_CONTEXT,driversLocationsPersistence);

    @AfterAll
    static void deleteTestData() {
        PL_CONTEXT.dslContext()
                .delete(DriversLocations.TABLE)
                .where(DSL.field(DriversLocationsEntity.DRIVER_ID.toString()).eq(DRIVER_ID))
                .execute();
    }

    @Test
    void setLocations() {
        DriversLocationsDTO driversLocationsDTO = new DriversLocationsDTO(DRIVER_ID, STREET_NAME);

        assertEquals(3,distanceDriverRepository.setLocations(driversLocationsDTO));
    }
}