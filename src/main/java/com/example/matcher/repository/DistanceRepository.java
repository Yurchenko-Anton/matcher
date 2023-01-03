package com.example.matcher.repository;

import com.example.matcher.dto.LocationsDTO;
import com.example.matcher.persistence.LocationsEntity;
import com.kenshoo.pl.entity.PLContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DistanceRepository {

    private final PLContext plContext;

    public LocationsDTO getLocations(String locations){
       final  var request = plContext
                .select(LocationsEntity.ID, LocationsEntity.STREET_NAME, LocationsEntity.X_COORDINATE, LocationsEntity.Y_COORDINATE)
                .from(LocationsEntity.INSTANCE)
                .where(LocationsEntity.STREET_NAME.eq(locations))
                .fetch();

        int id = request.stream().findFirst().orElseThrow().get(LocationsEntity.ID);
        String streetName = request.stream().findFirst().orElseThrow().get(LocationsEntity.STREET_NAME);
        int xCoordinate = request.stream().findFirst().orElseThrow().get(LocationsEntity.X_COORDINATE);
        int yCoordinate = request.stream().findFirst().orElseThrow().get(LocationsEntity.Y_COORDINATE);

    return new LocationsDTO(id, streetName, xCoordinate, yCoordinate);
    }
}