package com.example.matcher.command;

import com.example.matcher.persistence.LocationsEntity;
import com.kenshoo.pl.entity.CreateEntityCommand;

public class CreateLocationsCommand extends CreateEntityCommand<LocationsEntity> {

    public CreateLocationsCommand() {
        super(LocationsEntity.INSTANCE);
    }
}