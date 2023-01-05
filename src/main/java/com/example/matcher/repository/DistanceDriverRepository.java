package com.example.matcher.repository;

import com.example.matcher.command.drivers.locations.CreateDriversLocationsCommand;
import com.example.matcher.dto.DriversLocationsDTO;
import com.example.matcher.dto.LocationsDTO;
import com.example.matcher.persistence.DriversLocationsEntity;
import com.example.matcher.table.schema.DriversLocations;
import com.example.matcher.table.schema.Locations;
import com.kenshoo.pl.entity.PLContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

@Component
@RequiredArgsConstructor
public class DistanceDriverRepository {

    private static final int RANGE = 1000;

    private final PLContext plContext;
    private final DriversLocationsPersistence driversLocationsPersistence;

    public int setLocations(LocationsDTO locations, DriversLocationsDTO driversLocationsDTO) {
        final var cmd = new CreateDriversLocationsCommand();
        cmd.set(DriversLocationsEntity.DRIVER_ID, driversLocationsDTO.getId());
        cmd.set(DriversLocationsEntity.PLACE_ID, locations.getId());
        driversLocationsPersistence.create(List.of(cmd));

        return locations.getId();
    }

    public Integer getNearestDriver(TreeMap<Double, LocationsDTO> distanceLocations) {
        distanceLocations.values().forEach(System.out::println);
        List<Integer> id = new ArrayList<>();

        for (LocationsDTO locations : distanceLocations.values()) {
            id.addAll(getDriverId(locations));
            if (!id.isEmpty()) {
                break;
            }
        }

        return id.stream().findFirst().orElseThrow();
    }

    public List<LocationsDTO> getLocationsNearClient(int clientXCoordinate, int clientYCoordinate) {
        return plContext
                .dslContext()
                .selectFrom(Locations.TABLE)
                .where(Locations.TABLE.xCoordinate
                        .between(clientXCoordinate - RANGE, clientXCoordinate + RANGE))
                .and(Locations.TABLE.yCoordinate
                        .between(clientYCoordinate - RANGE, clientYCoordinate + RANGE))
                .fetchInto(LocationsDTO.class);
    }

    private List<Integer> getDriverId(LocationsDTO locations) {
        return plContext.dslContext()
                .select(DriversLocations.TABLE.driverId)
                .from(DriversLocations.TABLE)
                .where(DriversLocations.TABLE.placeId.eq(locations.getId()))
                .fetchInto(Integer.class);
    }
}