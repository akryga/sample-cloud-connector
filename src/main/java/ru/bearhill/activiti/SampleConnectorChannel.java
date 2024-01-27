package ru.bearhill.activiti;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface SampleConnectorChannel {
	/*
	 * The SAMPLE_CONNECTOR_CONSUMER value must match the channel name 
	 * as specified in spring.cloud.stream.bindings.<channel name>.<property> configuration
	 */
	
	 String SAMPLE_CONNECTOR_CONSUMER = "sampleConnectorConsumer";

	 @Input(SAMPLE_CONNECTOR_CONSUMER)
	 SubscribableChannel sampleConnectorConsumer();
	}