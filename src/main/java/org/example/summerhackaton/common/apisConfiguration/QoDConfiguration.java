package org.example.summerhackaton.common.apisConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class QoDConfiguration {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}