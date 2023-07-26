package com.example.sigmback.config;

import org.springframework.context.annotation.Configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class CorsConfig {


    @Bean
    public WebMvcConfigurer getCorsConfiguration(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {

                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4200")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
//                        .allowedHeaders("Access-Control-Allow-Origin","Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me")
                        .allowedHeaders("*")
                        .allowCredentials(true);
//                        .exposedHeaders("Access-Control-Allow-Origin","Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");
            }
        };
    }
}
