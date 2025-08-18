package com.camunda.selfmanaged.workers;

import org.springframework.stereotype.Component;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;


@Component
public class DomesticWorker {
	
	@JobWorker(type = "domesticFlightWorker")
	public void setFlightStatus (final JobClient client, final ActivatedJob jo) {
		System.out.print("\nInside Domestic Flights Worker");
	}

}
