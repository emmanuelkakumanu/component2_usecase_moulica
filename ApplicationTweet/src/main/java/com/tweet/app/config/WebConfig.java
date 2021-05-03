package com.tweet.app.config;

import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.logging.Logger;

@Configuration
public class WebConfig implements WebMvcConfigurer {
//    public final Logger LOGGER = LoggerFactory.getLogger(BloodServicApplication.class);



    @Override
    public void addCorsMappings(CorsRegistry registry) {
//        LOGGER.info("START");
//        LOGGER.info("END");
        registry.addMapping("/**").allowedMethods("*").allowedOrigins("*");
    }
}
