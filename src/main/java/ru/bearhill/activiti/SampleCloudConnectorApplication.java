package ru.bearhill.activiti;

import org.activiti.cloud.connectors.starter.configuration.EnableActivitiCloudConnector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableActivitiCloudConnector
@ComponentScan({ "org.activiti.cloud.connectors.starter", "ru.bearhill.activiti",
		"org.activiti.cloud.services.common.security" })
public class SampleCloudConnectorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleCloudConnectorApplication.class, args);
	}

}
