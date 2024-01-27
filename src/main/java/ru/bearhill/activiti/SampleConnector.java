package ru.bearhill.activiti;

import java.util.HashMap;
import java.util.Map;

import org.activiti.cloud.api.process.model.IntegrationRequest;
import org.activiti.cloud.api.process.model.IntegrationResult;
import org.activiti.cloud.connectors.starter.channels.IntegrationResultSender;
import org.activiti.cloud.connectors.starter.configuration.ConnectorProperties;
import org.activiti.cloud.connectors.starter.model.IntegrationResultBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(SampleConnectorChannel.class)
public class SampleConnector {

	private final Logger logger = LoggerFactory.getLogger(SampleConnector.class);
	
	@Value("${spring.application.name}")
	private String appName;

	@Autowired
	private ConnectorProperties connectorProperties;

	private final IntegrationResultSender integrationResultSender;

	public SampleConnector(IntegrationResultSender integrationResultSender) {
		this.integrationResultSender = integrationResultSender;
	}

	@StreamListener(value = SampleConnectorChannel.SAMPLE_CONNECTOR_CONSUMER)
	public void execute(IntegrationRequest event) throws InterruptedException {

		//System.out.println("Called Cloud Connector " + appName);
		logger.info("Called Cloud Connector " + appName);

		String var1 = SampleConnector.class.getSimpleName() + " was called for instance "
				+ event.getIntegrationContext().getProcessInstanceId();

		// Implement your business logic here

		Map<String, Object> results = new HashMap<>();
		results.put("var1", var1);
		Message<IntegrationResult> message = IntegrationResultBuilder.resultFor(event, connectorProperties)
				.withOutboundVariables(results).buildMessage();
		integrationResultSender.send(message);
	}
}