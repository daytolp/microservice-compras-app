package com.paymentchain.products;


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
public class ProductsApplication {
    
	public static void main(String[] args) {
		SpringApplication.run(ProductsApplication.class, args); 
	}
//      @Bean
//  public GroupedOpenApi publicApi() {
//      return GroupedOpenApi.builder()
//              .group("product")
//              .packagesToScan("com.paymentchain.products.controller")
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
