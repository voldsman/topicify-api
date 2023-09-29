package io.voldsman.topicify.core.config;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.util.TimeZone;

@Slf4j
@Configuration
public class AppConfig {

    @PostConstruct
    void init() {
        log.info("Initializing default timezone..");
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }
}
