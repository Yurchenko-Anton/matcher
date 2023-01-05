package com.example.matcher.config;

import com.example.matcher.repository.DriversLocationsPersistence;
import com.kenshoo.pl.entity.PLContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class PersistenceConfig {

    private final PLContext plContext;

    @Bean
    public DriversLocationsPersistence driversLocationsPersistence(){
        return new DriversLocationsPersistence(plContext);
    }
}