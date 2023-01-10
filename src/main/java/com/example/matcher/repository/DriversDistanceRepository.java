package com.example.matcher.repository;

import com.example.matcher.command.drivers.locations.CreateDriversLocationsCommand;
import com.example.matcher.command.drivers.locations.UpdateDriversLocationsCommand;
import com.example.matcher.dto.DriversLocationsDTO;
import com.example.matcher.dto.DriversStatusDTO;
import com.example.matcher.dto.LocationsDTO;
import com.example.matcher.model.Status;
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
        Status status = getStatus(driversLocationsDTO);

        final var cmd = new CreateDriversLocationsCommand();
        cmd.set(DriversLocationsEntity.DRIVER_ID, driversLocationsDTO.getId());
        cmd.set(DriversLocationsEntity.LOCATIONS_ID, locations.getId());
        cmd.set(DriversLocationsEntity.STATUS, status.name());
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
                .and(DriversLocations.TABLE.status.eq(Status.FREE.name()))
                .fetchInto(Integer.class);
    }

    public Boolean setStatus(DriversStatusDTO driversStatusDTO){
        int driversLocationsId = getDriversLocationsIdByDriverId(driversStatusDTO.getId());
        Status status = Status.valueOf(driversStatusDTO.getStatus().toUpperCase());

        final var cmd = new UpdateDriversLocationsCommand(driversLocationsId);
        cmd.set(DriversLocationsEntity.STATUS, status.name());

        return driversLocationsPersistence.update(List.of(cmd)).hasErrors();
    }

    private Status getStatus(DriversLocationsDTO driversLocationsDTO){
        List<Status> status = plContext.dslContext()
                .select(DriversLocations.TABLE.status)
                .from(DriversLocations.TABLE)
                .where(DriversLocations.TABLE.driverId.eq(driversLocationsDTO.getId()))
                .fetchInto(Status.class);

        return status.isEmpty() ? Status.FREE : status.stream().findFirst().get();
    }

    private Integer getDriversLocationsIdByDriverId(Integer driverId){
        return plContext.dslContext()
                .select(DriversLocations.TABLE.id)
                .from(DriversLocations.TABLE)
                .where(DriversLocations.TABLE.driverId.eq(driverId))
                .fetchInto(Integer.class)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Can`t find driver locations, for driver id: " + driverId));
    }
}