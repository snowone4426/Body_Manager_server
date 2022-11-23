package net.ict.bodymanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BodyManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BodyManagerApplication.class, args);
    }

}
