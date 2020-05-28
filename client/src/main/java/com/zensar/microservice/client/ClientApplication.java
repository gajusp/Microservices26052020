package com.zensar.microservice.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
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

	@Autowired
	private RestTemplateBuilder builder;

	// EurekaClient DiscoveryClient
	@Autowired
	private EurekaClient client;

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}

	// http://localhost:8080/
	@GetMapping("/")
	public String callToServiceApplication() {
		RestTemplate template = builder.build();

		InstanceInfo info = client.getNextServerFromEureka("SERVICE", false);

		String homePageUrl = info.getHomePageUrl();

		ResponseEntity<String> exchange = template.exchange(homePageUrl, HttpMethod.GET, null, String.class);
// Address Line
		
// Headers
		
// BODY
		
		return exchange.getBody();
	}

}
