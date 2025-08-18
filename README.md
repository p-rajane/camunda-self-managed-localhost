# Camunda 8 - Self Managed on localhost

This is Camunda 8 Spring Boot application demonstrated the step by step guide to create, run Camunda 8 spring boot integrated application, to host as a self managed on localhost

## Prerequisites
- [JDK 21+](https://www.oracle.com/in/java/technologies/downloads/)
- [Desktop Modeler](https://docs.camunda.io/docs/components/modeler/desktop-modeler/)
- [Camunda 8 Run](https://downloads.camunda.cloud/release/camunda/c8run/8.7/)

Once all above software/packages installed, follow below steps,

### 1. Run Camunda 8 on local

Go to Camunda 8 Run folder and execute below command from command prompt
```bash
...\camunda8-run-8.7-windows-x86_64\c8run>c8run.exe start
```
This will run camunda 8 on localhost and you can see below logs on command prompt.
```bash
System Version Information
--------------------------
Camunda Details:
  Version: 8.7.10
Java Details:
  Version: 21.0.2
--------------------------

Starting Elasticsearch 8.13.4...
(Hint: you can find the log output in the 'elasticsearch.log' file in the 'log' folder of your distribution.)
Waiting for Elasticsearch to start. 12 retries left
Waiting for Elasticsearch to start. 11 retries left
Waiting for Elasticsearch to start. 10 retries left
Waiting for Elasticsearch to start. 9 retries left
Waiting for Elasticsearch to start. 8 retries left
Elasticsearch has successfully been started.
Waiting for Camunda to start. 24 retries left
Waiting for Camunda to start. 23 retries left
Waiting for Camunda to start. 22 retries left
Waiting for Camunda to start. 21 retries left
Waiting for Camunda to start. 20 retries left
Waiting for Camunda to start. 19 retries left
Camunda has successfully been started.
-------------------------------------------
Access each component at the following urls with these default credentials:
- username: demo
- password: demo

Operate:                http://localhost:8080/operate
Tasklist:               http://localhost:8080/tasklist

Camunda 8 API:          http://localhost:8080/v2/
Inbound Connectors API: http://localhost:8085/
Zeebe API (gRPC):       http://localhost:26500/

Camunda metrics endpoint:    http://localhost:9600/actuator/prometheus

When using the Desktop Modeler, Authentication may be set to None.
```
### 2. Create Spring Boot Application
- Create one spring boot application using spring initializer and make sure to add below zeebe related dependency.
```bash
        <dependency>
			<groupId>io.camunda</groupId>
			<artifactId>spring-boot-starter-camunda-sdk</artifactId>
			<version>8.7.10</version>
		</dependency>
```
- However, it may be possible, that this and dependent dependencies may not be available at maven central repository location. Hence add below repositories to your pom.xml
```bash
     <repositories>
		<repository>
			<releases>
				<enabled>true</enabled>
			</releases>
			<snapshots>
				<enabled>false</enabled>
			</snapshots>
			<id>zeebe</id>
			<name>Zeebe Repository</name>
			<url>https://artifacts.camunda.com/artifactory/zeebe-io/</url>
		</repository>
		<repository>
			<releases>
				<enabled>false</enabled>
			</releases>
			<snapshots>
				<enabled>true</enabled>
			</snapshots>
			<id>zeebe-snapshots</id>
			<name>Zeebe Snapshot Repository</name>
			<url>https://artifacts.camunda.com/artifactory/zeebe-io-snapshots/</url>
		</repository>
		<repository>
			<releases>
				<enabled>true</enabled>
			</releases>
			<snapshots>
				<enabled>false</enabled>
			</snapshots>
			<id>camunda-identity</id>
			<name>Camunda Identity Repository</name>
			<url>https://artifacts.camunda.com/artifactory/camunda-identity/</url>
		</repository>
		<repository>
			<releases>
				<enabled>false</enabled>
			</releases>
			<snapshots>
				<enabled>true</enabled>
			</snapshots>
			<id>camunda-identity-snapshots</id>
			<name>Camunda Identity Snapshot Repository</name>
			<url>
				https://artifacts.camunda.com/artifactory/camunda-identity-snapshots/</url>
		</repository>
		<repository>
			<releases>
				<enabled>true</enabled>
			</releases>
			<snapshots>
				<enabled>false</enabled>
			</snapshots>
			<id>camunda-bpm</id>
			<name>Camunda BPM Repository</name>
			<url>https://artifacts.camunda.com/artifactory/camunda-bpm/</url>
		</repository>
		<repository>
			<releases>
				<enabled>false</enabled>
			</releases>
			<snapshots>
				<enabled>true</enabled>
			</snapshots>
			<id>camunda-bpm-snapshots</id>
			<name>Camunda BPM Snapshot Repository</name>
			<url>
				https://artifacts.camunda.com/artifactory/camunda-bpm-snapshots/</url>
		</repository>
	</repositories>
```
- Now configure zeebe connection details in this spring boot application. Open application.properties/yml file in /src/main/resources and add below properties
```bash
camunda.client.mode=selfmanaged
camunda.client.zeebe.defaults.enabled=true
camunda.client.zeebe.grpc-address.host=localhost
camunda.client.zeebe.grpc-address.port=26500
camunda.client.zeebe.rest-address.host= localhost
camunda.client.zeebe.rest-address.port=8080
camunda.client.zeebe.rest-address.path=/v2/
```
- Verify once, the values that are added are copied from output of step 'Run Camunda 8 on local'

### 3. Create .bpmn file
- Now create bpmn file in /src/main/resources folder and create the workers for service tasks in the process.

### 3. Start spring boot application.
- Once all configuration done, process created, good to clean and compile the spring boot application.
- Now start the spring boot application and monitor the console. You should see logs like 
```bash
Starting Zeebe worker...
Starting Zeebe worker...
Deployed:<process>
```
This shows that your application is up and processes are successfully deployed to localhost c8 run.

### Monitor and play with processes.
- After successfull start of an application, open operate URL given in step 1 as  [http://localhost:8080/operate]( http://localhost:8080/operate)
- You can check your process deployed, versions. Also can start the instance from tasklist as [ http://localhost:8080/tasklist]( http://localhost:8080/tasklist)
