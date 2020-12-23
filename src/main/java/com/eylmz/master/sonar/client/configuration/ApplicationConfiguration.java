package com.eylmz.master.sonar.client.configuration;

import com.eylmz.master.sonar.client.integration.shell.ShellIntegrator;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public ShellIntegrator shellIntegrator() { return new ShellIntegrator(); }

}
