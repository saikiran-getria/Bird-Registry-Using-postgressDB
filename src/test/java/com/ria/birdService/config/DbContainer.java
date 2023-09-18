package com.ria.birdService.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;

@TestConfiguration
public class DbContainer extends PostgreSQLContainer<DbContainer> {
    private static final String IMAGE_VERSION = "postgres:13.10";
    private static DbContainer container;

    private DbContainer() {
        super(IMAGE_VERSION);
    }

    public static DbContainer getInstance() {
        if (container == null) {
            container = new DbContainer();
        }
        return container;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("DB_URL", container.getJdbcUrl());
        System.setProperty("DB_USERNAME", container.getUsername());
        System.setProperty("DB_PASSWORD", container.getPassword());
    }

    @Override
    public void stop() {
        //do nothing, JVM handles shut down
    }
}
