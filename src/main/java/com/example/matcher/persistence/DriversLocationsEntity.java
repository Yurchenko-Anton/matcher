package com.example.matcher.persistence;

import com.example.matcher.table.schema.DriversLocations;
import com.kenshoo.jooq.DataTable;
import com.kenshoo.pl.entity.AbstractEntityType;
import com.kenshoo.pl.entity.EntityField;
import com.kenshoo.pl.entity.annotation.Id;
import com.kenshoo.pl.entity.annotation.Immutable;

public class DriversLocationsEntity extends AbstractEntityType<DriversLocationsEntity> {

    public static final DriversLocationsEntity INSTANCE = new DriversLocationsEntity();

    private DriversLocationsEntity() {
        super("drivers_locations");

    }

    @Override
    public DataTable getPrimaryTable() {
        return DriversLocations.TABLE;
    }

    @Id
    @Immutable
    public static final EntityField<DriversLocationsEntity, Integer> ID = INSTANCE.field(DriversLocations.TABLE.id);

    public static final EntityField<DriversLocationsEntity, Integer> DRIVER_ID = INSTANCE.field(DriversLocations.TABLE.driverId);

    public static final EntityField<DriversLocationsEntity, Integer> LOCATIONS_ID = INSTANCE.field(DriversLocations.TABLE.locationsId);
}