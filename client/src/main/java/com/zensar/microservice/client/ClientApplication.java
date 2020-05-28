package com.zensar.microservice.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class ClientApplication {
//8080 9090
	@Autowired
	private RestTemplateBuilder builder;

	// EurekaClient DiscoveryClient
	//@Autowired
	//private EurekaClient client;  // netflix
	@Autowired
	private DiscoveryClient client; // spring 

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}

	// http://localhost:8080/
	@GetMapping()
	public String callToServiceApplication() {
		
		RestTemplate template = builder.build();

		
		
		//InstanceInfo info = client.getNextServerFromEureka("service", false);
		
		
		 // System.out.println(client);
		  
		  System.out.println("---------------------"+client.getInstances("service"));
		  List<ServiceInstance> instances = client.getInstances("service");
		  
		  String homePageUrl = instances.get(0).getUri().toString();
		 
		
		

		
		//String homePageUrl = info.getHomePageUrl();

		ResponseEntity<String> exchange = template.exchange(homePageUrl, HttpMethod.GET, null, String.class);

		
		return exchange.getBody();
	}

}
