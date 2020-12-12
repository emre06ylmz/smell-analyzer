package com.eylmz.master.sonar.client.configuration;

import feign.Contract;
import org.springframework.context.annotation.Bean;

public class FeignConfiguration {

    @Bean
    public Contract feignContract() {
        return new feign.Contract.Default();
    }

}
