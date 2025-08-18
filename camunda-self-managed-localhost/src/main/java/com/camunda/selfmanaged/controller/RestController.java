package com.camunda.selfmanaged.controller;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ProcessInstanceEvent;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@org.springframework.web.bind.annotation.RestController
public class RestController {

  @Autowired ZeebeClient client;

  @GetMapping("/start/{route}")
  public String getCall(@PathVariable("route") String route) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("route", route);
    ProcessInstanceEvent proceInstEvent = client
        .newCreateInstanceCommand()
        .bpmnProcessId("TestProcess")
        .latestVersion()
        .variables(map)
        .send()
        .join();
    
    return "<h1><span style=\"color: #ff6600;\"><strong>Process started with ID = </strong></span></h1>" 
    + "<h1><span style=\"color: #ff6610;\"><strong>" + proceInstEvent.getProcessInstanceKey() + "</strong></span></h1>";
  }
}
