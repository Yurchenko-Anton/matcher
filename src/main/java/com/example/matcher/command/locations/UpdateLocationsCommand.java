package com.example.matcher.command.locations;

import com.example.matcher.persistence.LocationsEntity;
import com.kenshoo.pl.entity.Identifier;
import com.kenshoo.pl.entity.UpdateEntityCommand;

import static com.kenshoo.pl.entity.IdentifierType.uniqueKey;

public class UpdateLocationsCommand extends UpdateEntityCommand<LocationsEntity, Identifier<LocationsEntity>> {

    public UpdateLocationsCommand(int idValue) {
        super(LocationsEntity.INSTANCE, uniqueKey(LocationsEntity.ID).createIdentifier(idValue));
    }
}