package com.example.microservices.limitsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.microservices.limitsservice.bean.LimitsConfiguration;

@RestController
public class LimitsConfigurationController {

	@Autowired
	private Configuration configuration;
	
	@GetMapping("/limits")
	public LimitsConfiguration retrieveLimitsFromConfigurations() {
		return new LimitsConfiguration(configuration.getMaximum(), configuration.getMinimum());
	}
	
	@GetMapping("/fault-tolerance-example")
	public LimitsConfiguration retrieveConfigurations() {
		return new LimitsConfiguration(configuration.getMaximum(), configuration.getMinimum());
	}
}
