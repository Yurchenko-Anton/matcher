package com.example.matcher.repository;

import com.example.matcher.command.drivers.locations.CreateDriversLocationsCommand;
import com.example.matcher.persistence.DriversLocationsEntity;
import com.kenshoo.pl.entity.*;

import java.util.Collection;

public class DriversLocationsPersistence {

    private final PersistenceLayer persistenceLayer;
    private final PLContext settings;

    public DriversLocationsPersistence(PLContext settings) {
        this.settings = settings;
        this.persistenceLayer = new PersistenceLayer(settings);
    }

    public CreateResult<DriversLocationsEntity, Identifier<DriversLocationsEntity>> create(Collection<CreateDriversLocationsCommand> commands) {
        return persistenceLayer.create(commands, flowBuilder().build());
    }

    public <ID extends Identifier<DriversLocationsEntity>>
    UpdateResult<DriversLocationsEntity, ID> update(Collection<? extends UpdateEntityCommand<DriversLocationsEntity, ID>> commands) {
        return persistenceLayer.update(commands, flowBuilder().build());
    }

    private ChangeFlowConfig.Builder<DriversLocationsEntity> flowBuilder() {
        return ChangeFlowConfigBuilderFactory.newInstance(settings, DriversLocationsEntity.INSTANCE);
    }
}