package com.example.demosqs.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AppPropertiesTest {

    @Autowired
    private AppProperties properties;

    @Test
    public void readProperties() {
        assertEquals(120000, properties.getDurationSimulating());
    }
}