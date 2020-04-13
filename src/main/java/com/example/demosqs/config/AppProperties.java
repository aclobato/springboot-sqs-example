package com.example.demosqs.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class AppProperties {

    @Value("${queue.simulating.duration.ms}")
    private int durationSimulating;
}
