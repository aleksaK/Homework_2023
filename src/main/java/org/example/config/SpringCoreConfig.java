package org.example.config;

import groovy.lang.GroovyShell;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.example.core")
public class SpringCoreConfig {

    @Bean
    public GroovyShell getGroovyShell() {
        return new GroovyShell();
    }

}