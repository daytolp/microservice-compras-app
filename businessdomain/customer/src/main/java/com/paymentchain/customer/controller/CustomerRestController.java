/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paymentchain.customer.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.paymentchain.customer.business.transactions.BussinesTransaction;
import com.paymentchain.customer.entities.Customer;
import com.paymentchain.customer.entities.CustomerProduct;
import com.paymentchain.customer.exception.BussinesRuleException;
import com.paymentchain.customer.respository.CustomerRepository;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;

import java.net.UnknownHostException;
import java.util.Collections;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;
@CrossOrigin
@RestController
@RequestMapping("/customer")
public class CustomerRestController {
    
    @Autowired
    CustomerRepository customerRepository;


    @Autowired
    BussinesTransaction bt;

    @Value("${user.role}")
    private String role;
  
    private final WebClient.Builder webClientBuilder;
    
    public CustomerRestController(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }
    
    //define timeout
    TcpClient tcpClient = TcpClient
            .create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
            .doOnConnected(connection -> {
                connection.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS));
                connection.addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS));
            });

    @GetMapping("/full")
    public Customer get(@RequestParam  String code) {
        return bt.get(code);
    }
    
//    private <T> List<T> getTransacctions(String accountIban) {
//        WebClient client = webClientBuilder.clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
//                .baseUrl("http://businessdomain-transactions/transaction")
//                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                .defaultUriVariables(Collections.singletonMap("url", "http://businessdomain-transactions/transaction"))
//                .build();
//        List<Object> block = client.method(HttpMethod.GET).uri(uriBuilder -> uriBuilder
//                .path("/transactions")
//                .queryParam("ibanAccount", accountIban)
//                .build())
//                .retrieve().bodyToFlux(Object.class).collectList().block();
//        List<T> name = (List<T>) block;
//        return name;
//    }
//
//
//    private  String getProductName(long id) {
//        WebClient client = webClientBuilder.clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
//                .baseUrl("http://businessdomain-product/product")
//                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                .defaultUriVariables(Collections.singletonMap("url", "http://businessdomain-product/product"))
//                .build();
//        JsonNode block = client.method(HttpMethod.GET).uri("/"+id)
//                .retrieve().bodyToMono(JsonNode.class).block();
//        String name = block.get("name").asText();
//        return name;
//    }
//
   
    @GetMapping()
    public ResponseEntity<List<Customer>> list() {
       List<Customer> customers = customerRepository.findAll();
        if (customers == null || customers.isEmpty())
            return ResponseEntity.noContent().build();
       return ResponseEntity.ok(customers);
    }
      @GetMapping("/hello")
    public String sayHello() {
        return "Hello your role is: "+ role;
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable long id) {
        return  customerRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.noContent().build());
    }  
   
    
    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable String id, @RequestBody Customer input) {
        return null;
    }
    
   @PostMapping
    public ResponseEntity<?> post(@RequestBody Customer input) throws UnknownHostException, BussinesRuleException {
        Customer save = bt.save(input);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return null;
    }
    
}
