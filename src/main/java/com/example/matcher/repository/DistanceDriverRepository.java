package com.example.matcher.repository;

import com.example.matcher.command.drivers.locations.CreateDriversLocationsCommand;
import com.example.matcher.dto.DriversLocationsDTO;
import com.example.matcher.persistence.DriversLocationsEntity;
import com.example.matcher.persistence.LocationsEntity;
import com.kenshoo.pl.entity.PLContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DistanceDriverRepository {

    private final PLContext plContext;
    private final DriversLocationsPersistence driversLocationsPersistence;

    public int setLocations(DriversLocationsDTO driversLocationsDTO) {
        int locationsId = getLocationsId(driversLocationsDTO.getStreetName());

        final var cmd = new CreateDriversLocationsCommand();
        cmd.set(DriversLocationsEntity.DRIVER_ID, driversLocationsDTO.getId());
        cmd.set(DriversLocationsEntity.PLACE_ID, locationsId);
        driversLocationsPersistence.create(List.of(cmd));

        return locationsId;
    }

    private int getLocationsId(String streetName) {
        final var request = plContext.select(LocationsEntity.ID, LocationsEntity.STREET_NAME, LocationsEntity.X_COORDINATE, LocationsEntity.Y_COORDINATE)
                .from(LocationsEntity.INSTANCE)
                .where(LocationsEntity.STREET_NAME.eq(streetName))
                .fetch();

        return request.stream().findFirst().orElseThrow().get(LocationsEntity.ID);
    }
}