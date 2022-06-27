package com.roytuts.spring.integration.mqtt.service.impl;

import com.roytuts.spring.integration.mqtt.service.MessagingService;
import com.roytuts.spring.integration.mqtt.service.MongodbSubscribeService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.BlockingQueue;

@Data
public class MqttMessageSubscribeService extends Thread {

    @Autowired
    private MessagingService messagingService;

    @Autowired
    private MongodbSubscribeService mongodbSubscribeService;

    private String topic;

    private BlockingQueue<String> blockingQueue;

    @Override
    public void run() {
        try {
            messagingService.subscribe(this.topic, this.blockingQueue);
        } catch (Exception e) {

        }
    }
}
