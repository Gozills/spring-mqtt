package com.roytuts.spring.integration.mqtt;

import com.roytuts.spring.integration.mqtt.service.impl.MqttMessageSubscribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@SpringBootApplication(scanBasePackages = "com.roytuts.spring.integration.mqtt")
public class SpringIntegrationMqttPubSubApp implements CommandLineRunner {

	@Autowired
	private ConfigurableApplicationContext context;

	private MqttMessageSubscribeService mqttMessageSubscribeService;

	private BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>();

	public static void main(String[] args) {
		SpringApplication.run(SpringIntegrationMqttPubSubApp.class, args);
	}

	@Override
	public void run(String... args) {
		mqttMessageSubscribeService = new MqttMessageSubscribeService();
		mqttMessageSubscribeService.setBlockingQueue(blockingQueue);
		mqttMessageSubscribeService.setTopic("");
		Thread threadMqttMessageSubscribe = new Thread(mqttMessageSubscribeService);
		threadMqttMessageSubscribe.start();
		context.close();
	}

}
