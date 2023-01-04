package com.example.matcher;

import com.example.matcher.config.JsonAuditRecordPublisher;
import com.kenshoo.pl.entity.PLContext;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

public class BaseTest {

    protected static final PLContext PL_CONTEXT = configPL();

    private static final String URL = "jdbc:mysql://localhost:3300/security";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static PLContext configPL(){
        DSLContext dslContext = DSL.using(URL, USERNAME, PASSWORD);

        return new PLContext.Builder(dslContext).withAuditRecordPublisher(new JsonAuditRecordPublisher()).build();
    }
}