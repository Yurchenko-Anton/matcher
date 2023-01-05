package com.example.matcher.config;

import com.example.matcher.repository.DriversLocationsPersistence;
import com.kenshoo.pl.entity.PLContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DriversLocationsConfig {

    private final PLContext plContext;

    @Bean
    public DriversLocationsPersistence init(){
        return new DriversLocationsPersistence(plContext);
    }
}
