package com.example.matcher.persistence;

import com.example.matcher.table.schema.Locations;
import com.kenshoo.jooq.DataTable;
import com.kenshoo.pl.entity.AbstractEntityType;
import com.kenshoo.pl.entity.EntityField;
import com.kenshoo.pl.entity.annotation.Id;
import com.kenshoo.pl.entity.annotation.Immutable;
import com.kenshoo.pl.entity.annotation.audit.Audited;

@Audited
public class LocationsEntity extends AbstractEntityType<LocationsEntity> {

    public static final LocationsEntity INSTANCE = new LocationsEntity();

    private LocationsEntity() {
        super("locations");
    }

    @Override
    public DataTable getPrimaryTable() {
        return Locations.TABLE;
    }

    @Id
    @Immutable
    public static final EntityField<LocationsEntity, Integer> ID = INSTANCE.field(Locations.TABLE.id);

    public static final EntityField<LocationsEntity, String> STREET_NAME = INSTANCE.field(Locations.TABLE.streetName);

    public static final EntityField<LocationsEntity, Integer> X_COORDINATE = INSTANCE.field(Locations.TABLE.xCoordinate);

    public static final EntityField<LocationsEntity, Integer> Y_COORDINATE = INSTANCE.field(Locations.TABLE.yCoordinate);
}