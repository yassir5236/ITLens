package org.yassir.itlens;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "org.yassir.itlens")
public class ItLensApplication {
    public static void main(String[] args) {
        SpringApplication.run(ItLensApplication.class, args);
    }
}

