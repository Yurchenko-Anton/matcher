package com.example.matcher.repository;

import com.example.matcher.command.drivers.locations.CreateDriversLocationsCommand;
import com.example.matcher.dto.DriversLocationsDTO;
import com.example.matcher.dto.LocationsDTO;
import com.example.matcher.persistence.DriversLocationsEntity;
import com.example.matcher.table.schema.DriversLocations;
import com.example.matcher.table.schema.Locations;
import com.kenshoo.pl.entity.PLContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DriversDistanceRepository {

    @Value("${distance.range}")
    private int range;

    private final PLContext plContext;
    private final DriversLocationsPersistence driversLocationsPersistence;

    public int setLocations(LocationsDTO locations, DriversLocationsDTO driversLocationsDTO) {
        final var cmd = new CreateDriversLocationsCommand();
        cmd.set(DriversLocationsEntity.DRIVER_ID, driversLocationsDTO.getId());
        cmd.set(DriversLocationsEntity.LOCATIONS_ID, locations.getId());
        driversLocationsPersistence.create(List.of(cmd));

        return locations.getId();
    }

    public List<LocationsDTO> getLocationsNearClient(LocationsDTO locations) {
        return plContext
                .dslContext()
                .selectFrom(Locations.TABLE)
                .where(Locations.TABLE.xCoordinate
                        .between(locations.getXCoordinate() - range, locations.getXCoordinate() + range))
                .and(Locations.TABLE.yCoordinate
                        .between(locations.getYCoordinate() - range, locations.getYCoordinate() + range))
                .fetchInto(LocationsDTO.class);
    }

    public List<Integer> getDriverId(LocationsDTO locations) {
        return plContext.dslContext()
                .select(DriversLocations.TABLE.driverId)
                .from(DriversLocations.TABLE)
                .where(DriversLocations.TABLE.locationsId.eq(locations.getId()))
                .fetchInto(Integer.class);
    }
}