package com.camunda.selfmanaged;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.camunda.zeebe.spring.client.annotation.Deployment;

@SpringBootApplication
@Deployment(resources = {"process.bpmn"})
public class CamundaSelfManagedLocalhostApplication {

	public static void main(String[] args) {
		SpringApplication.run(CamundaSelfManagedLocalhostApplication.class, args);
	}

}
