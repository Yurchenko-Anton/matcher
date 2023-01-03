package com.example.matcher.table.schema;

import com.kenshoo.jooq.AbstractDataTable;
import org.jooq.Record;
import org.jooq.TableField;
import org.jooq.impl.SQLDataType;

public class DriversLocations extends AbstractDataTable<DriversLocations> {

    public static final DriversLocations TABLE = new DriversLocations("drivers_locations");

    private DriversLocations(String name) {
        super(name);
    }

    @Override
    public DriversLocations as(String alias) {
        return null;
    }

    public final TableField<Record, Integer> id = createPKField("id", SQLDataType.INTEGER.identity(true));

    public final TableField<Record, Integer> driverId = createFKField("driver_id", Users.TABLE.id);

    public final TableField<Record, Integer> placeId = createFKField("place_id", Locations.TABLE.id);
}