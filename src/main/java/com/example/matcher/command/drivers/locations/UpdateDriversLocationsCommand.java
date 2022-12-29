package com.example.matcher.command.drivers.locations;

import com.example.matcher.persistence.DriversLocationsEntity;
import com.kenshoo.pl.entity.Identifier;
import com.kenshoo.pl.entity.UpdateEntityCommand;

import static com.kenshoo.pl.entity.IdentifierType.uniqueKey;

public class UpdateDriversLocationsCommand extends UpdateEntityCommand<DriversLocationsEntity, Identifier<DriversLocationsEntity>> {

    public UpdateDriversLocationsCommand(int idValue) {
        super(DriversLocationsEntity.INSTANCE, uniqueKey(DriversLocationsEntity.ID).createIdentifier(idValue));
    }
}