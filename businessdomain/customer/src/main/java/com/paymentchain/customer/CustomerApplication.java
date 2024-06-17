package com.paymentchain.customer;

//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Info;
//import io.swagger.v3.oas.models.info.License;
//import org.springdoc.core.GroupedOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableEurekaClient
public class CustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }

//
//     @Bean
//  public GroupedOpenApi publicApi() {
//      return GroupedOpenApi.builder()
//              .group("customer")
//              .packagesToScan("com.paymentchain.customer.controller")
//              .build();
//  }
//
//    @Bean
//    public OpenAPI springShopOpenAPI() {
//        return new OpenAPI()
//                .info(new Info().title("cloud API")
//                        .description("cloud API")
//                        .version("1.0")
//                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
//    }
//

}
