package com.camunda.selfmanaged.workers;

import org.springframework.stereotype.Component;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;

@Component
public class FlightStatusWorker {
	
	@JobWorker(type = "setFlightStatus")
	public void setFlightStatus (final JobClient client, final ActivatedJob jo) {
		System.out.print("\nInside Set Flight Status");
		jo.getVariablesAsMap().put("route", "domestic");
	}

}
