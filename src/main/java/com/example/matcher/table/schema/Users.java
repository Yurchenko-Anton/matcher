package com.example.matcher.table.schema;

import com.kenshoo.jooq.AbstractDataTable;
import org.jooq.Record;
import org.jooq.TableField;
import org.jooq.impl.SQLDataType;

public class Users extends AbstractDataTable<Users> {

    public static final Users TABLE = new Users("users");

    private Users(String name) {
        super(name);
    }

    @Override
    public Users as(String alias) {
        return null;
    }

    public final TableField<Record, Integer> id = createPKField("id", SQLDataType.INTEGER.identity(true));

    public final TableField<Record, String> streetName = createField("first_name", SQLDataType.VARCHAR.length(255));

    public final TableField<Record, String> xCoordinate = createField("last_name", SQLDataType.VARCHAR.length(255));

    public final TableField<Record, String> yCoordinate = createField("phone", SQLDataType.VARCHAR.length(255));
}
