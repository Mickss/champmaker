package org.micks.champmaker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class ChampmakerApplication {

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:25002")
                .build();
    }

    public static void main(String[] args) {
        SpringApplication.run(ChampmakerApplication.class, args);
    }
}
