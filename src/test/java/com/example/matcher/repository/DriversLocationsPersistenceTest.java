package com.example.matcher.repository;

import com.example.matcher.BaseTest;
import com.example.matcher.command.drivers.locations.CreateDriversLocationsCommand;
import com.example.matcher.command.drivers.locations.UpdateDriversLocationsCommand;
import com.example.matcher.persistence.DriversLocationsEntity;
import com.example.matcher.table.schema.DriversLocations;
import org.jooq.impl.DSL;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DriversLocationsPersistenceTest extends BaseTest {

    private static final Integer PLACE_ID = 4;
    private static final Integer DRIVER_ID = 1;

    private static final DriversLocationsPersistence driversLocationsPersistence = new DriversLocationsPersistence(PL_CONTEXT);

    @BeforeAll
    static void createTestData() {
        final var cmd = new CreateDriversLocationsCommand();
        cmd.set(DriversLocationsEntity.DRIVER_ID, DRIVER_ID);
        driversLocationsPersistence.create(List.of(cmd));
    }

    @AfterAll
    static void deleteTestData() {
        PL_CONTEXT.dslContext()
                .delete(DriversLocations.TABLE)
                .where(DSL.field(DriversLocationsEntity.ID.toString()).eq(DRIVER_ID))
                .execute();
    }

    @Test
    void shouldUpdateData() {
        final var queryBeforeUpdate = PL_CONTEXT
                .select(DriversLocationsEntity.ID)
                .from(DriversLocationsEntity.INSTANCE)
                .where(DriversLocationsEntity.DRIVER_ID.eq(DRIVER_ID))
                .fetch();

        int id = queryBeforeUpdate.stream().findFirst().orElseThrow().get(DriversLocationsEntity.ID);

        final var cmd = new UpdateDriversLocationsCommand(id);
        cmd.set(DriversLocationsEntity.PLACE_ID, PLACE_ID);

        driversLocationsPersistence.update(List.of(cmd));

        final var queryAfterUpdate = PL_CONTEXT
                .select(DriversLocationsEntity.PLACE_ID)
                .from(DriversLocationsEntity.INSTANCE)
                .where(DriversLocationsEntity.ID.eq(id))
                .fetch();

        queryAfterUpdate.forEach(query -> {
            final var resultPlaceId = query.get(DriversLocationsEntity.PLACE_ID);

            assertEquals(PLACE_ID, resultPlaceId);
        });
    }
}