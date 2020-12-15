package com.eylmz.master.sonar.client;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


//@EnableAutoConfiguration(exclude = {DataSourceTransactionManagerAutoConfiguration.class})
@EnableFeignClients
@SpringBootApplication(scanBasePackages = {"com.eylmz.master.sonar.client"})
@EnableJpaRepositories(basePackages = {"com.eylmz.master.sonar.client"})
@EntityScan(basePackages = {"com.eylmz.master.sonar.client"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
