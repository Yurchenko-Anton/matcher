package com.example.matcher.table.schema;

import com.kenshoo.jooq.AbstractDataTable;
import org.jooq.Record;
import org.jooq.TableField;
import org.jooq.impl.SQLDataType;


public class Locations extends AbstractDataTable<Locations> {

    public static final Locations TABLE = new Locations("locations");

    private Locations(String name) {
        super(name);
    }

    @Override
    public Locations as(String alias) {
        return null;
    }

    public final TableField<Record, Integer> id = createPKField("id", SQLDataType.INTEGER.identity(true));

    public final TableField<Record, String> streetName = createField("street_name", SQLDataType.VARCHAR.length(255));

    public final TableField<Record, Integer> xCoordinate = createField("x_coordinate", SQLDataType.INTEGER);

    public final TableField<Record, Integer> yCoordinate = createField("y_coordinate", SQLDataType.INTEGER);
}