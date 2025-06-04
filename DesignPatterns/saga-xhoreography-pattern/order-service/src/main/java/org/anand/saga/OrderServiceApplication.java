package org.anand.saga;


import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OrderServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class,args);
        System.out.println("order-service-application");
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }



}