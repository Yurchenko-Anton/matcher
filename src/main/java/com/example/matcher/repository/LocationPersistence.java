package com.example.matcher.repository;

import com.example.matcher.command.CreateLocationsCommand;
import com.example.matcher.persistence.LocationsEntity;
import com.kenshoo.pl.entity.*;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class LocationPersistence {

    private final PersistenceLayer persistenceLayer;
    private final PLContext settings;

    public LocationPersistence(PLContext settings) {
        this.settings = settings;
        this.persistenceLayer = new PersistenceLayer(settings);
    }

    public CreateResult<LocationsEntity, Identifier<LocationsEntity>> create(Collection<CreateLocationsCommand> commands) {
        return persistenceLayer.create(commands, flowBuilder().build());
    }

    public <ID extends Identifier<LocationsEntity>>
    UpdateResult<LocationsEntity, ID> update(Collection<? extends UpdateEntityCommand<LocationsEntity, ID>> commands) {
        return persistenceLayer.update(commands, flowBuilder().build());
    }

    private ChangeFlowConfig.Builder<LocationsEntity> flowBuilder() {
        return ChangeFlowConfigBuilderFactory.newInstance(settings, LocationsEntity.INSTANCE);
    }
}