package com.example.matcher.repository;

import com.example.matcher.command.CreateLocationsCommand;
import com.example.matcher.command.UpdateLocationsCommand;
import com.example.matcher.config.JsonAuditRecordPublisher;
import com.example.matcher.persistence.LocationsEntity;
import com.example.matcher.table.schema.Locations;
import com.kenshoo.pl.entity.PLContext;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LocationPersistenceTest {

    private static final String URL = "jdbc:mysql://localhost:3300/security";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static final String START_STREET_NAME = "Test";
    private static final String UPDATE_STREET_NAME = "UpdateTest";

    private static final DSLContext dslContext = DSL.using(URL, USERNAME, PASSWORD);

    private static final PLContext plContext = new PLContext.Builder(dslContext).withAuditRecordPublisher(new JsonAuditRecordPublisher()).build();

    private static final LocationPersistence locationPersistence = new LocationPersistence(plContext);

    @BeforeAll
    static void createTestData() {
        var cmd = new CreateLocationsCommand();
        cmd.set(LocationsEntity.STREET_NAME, START_STREET_NAME);
        cmd.set(LocationsEntity.X_COORDINATE, 25);
        cmd.set(LocationsEntity.Y_COORDINATE, 15);
        locationPersistence.create(List.of(cmd));
    }

    @AfterAll
    static void deleteTestData() {
        plContext.dslContext()
                .delete(Locations.TABLE)
                .where(DSL.field(LocationsEntity.STREET_NAME.toString()).eq(UPDATE_STREET_NAME))
                .execute();
    }

    @Test
    void shouldUpdateData() {
        final var queryBeforeUpdate = plContext
                .select(LocationsEntity.ID)
                .from(LocationsEntity.INSTANCE)
                .where(LocationsEntity.STREET_NAME.eq(START_STREET_NAME))
                .fetch();

        int id = queryBeforeUpdate.stream().findFirst().orElseThrow().get(LocationsEntity.ID);

        final var cmd = new UpdateLocationsCommand(id);
        cmd.set(LocationsEntity.STREET_NAME, UPDATE_STREET_NAME);

        locationPersistence.update(List.of(cmd));

        final var queryAfterUpdate = plContext
                .select(LocationsEntity.STREET_NAME)
                .from(LocationsEntity.INSTANCE)
                .where(LocationsEntity.ID.eq(id))
                .fetch();

        queryAfterUpdate.forEach(query -> {
            var resultName = query.get(LocationsEntity.STREET_NAME);

            assertEquals(cmd.get(LocationsEntity.STREET_NAME), resultName);
        });
    }
}