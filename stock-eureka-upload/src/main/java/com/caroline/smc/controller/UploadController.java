package com.caroline.smc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.discovery.EurekaClient;

@RestController
public class UploadController {
	private final Logger logger = LoggerFactory.getLogger(UploadController.class);

	@Autowired
	@Lazy
	private EurekaClient eurekaClient;

	@Value("${spring.application.name}")
	private String appName;

	@RequestMapping("/greeting")
	public String greeting() {
		return String.format("Hello from '%s'!", eurekaClient.getApplication(appName).getName());
	}
}