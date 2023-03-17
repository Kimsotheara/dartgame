package com.dartgame.dartboardgame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class DartboardGameApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(DartboardGameApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DartboardGameApplication.class);
    }

}
