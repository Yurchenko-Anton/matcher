package com.example.matcher.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenshoo.pl.entity.audit.AuditRecord;
import com.kenshoo.pl.entity.spi.audit.AuditRecordPublisher;
import org.springframework.context.annotation.Configuration;

import java.util.stream.Stream;

@Configuration
public class JsonAuditRecordPublisher implements AuditRecordPublisher {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void publish(Stream<? extends AuditRecord> auditRecords) {
        final String auditRecordsStr;
        try {
            auditRecordsStr = objectMapper.writeValueAsString(auditRecords);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(auditRecordsStr);
    }
}