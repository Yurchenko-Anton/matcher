package com.example.matcher.config;

import com.kenshoo.pl.entity.PLContext;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

public class TestPLConfig {

    private final String URL = "jdbc:mysql://localhost:3300/security";
    private final String USERNAME = "root";
    private final String PASSWORD = "root";

    public PLContext configPL(){
        DSLContext dslContext = DSL.using(URL, USERNAME, PASSWORD);

        return new PLContext.Builder(dslContext).withAuditRecordPublisher(new JsonAuditRecordPublisher()).build();
    }
}