package com.example.matcher.command.drivers.locations;

import com.example.matcher.persistence.DriversLocationsEntity;
import com.kenshoo.pl.entity.CreateEntityCommand;

public class CreateDriversLocationsCommand extends CreateEntityCommand<DriversLocationsEntity> {

    public CreateDriversLocationsCommand() {
        super(DriversLocationsEntity.INSTANCE);
    }
}