package com.codedecode.FoodCatalogueMIcroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class FoodCatalogueMIcroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodCatalogueMIcroserviceApplication.class, args);
    }

    @Bean
//    @LoadBalanced
    public RestTemplate getRestTemplate() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(3000); // 3 seconds connection timeout
        factory.setReadTimeout(5000);    // 5 seconds read timeout
        return new RestTemplate(factory);
    }
}

