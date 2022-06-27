package com.roytuts.spring.integration.mqtt.service;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingQueue;

@Service
public class MessagingService {

	@Autowired
	private IMqttClient mqttClient;

	public void publish(final String topic, final String payload, int qos, boolean retained) throws MqttException {
		MqttMessage mqttMessage = new MqttMessage();
		mqttMessage.setPayload(payload.getBytes());
		mqttMessage.setQos(qos);
		mqttMessage.setRetained(retained);
		mqttClient.publish(topic, mqttMessage);
		mqttClient.disconnect();
	}

	public void subscribe(final String topic, final BlockingQueue blockingQueue) throws MqttException {
		mqttClient.subscribeWithResponse(topic, (tpic, msg) -> {
			blockingQueue.add(new String(msg.getPayload()));
		});
	}

}
