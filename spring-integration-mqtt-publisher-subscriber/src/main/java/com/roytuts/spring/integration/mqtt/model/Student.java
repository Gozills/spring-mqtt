package com.roytuts.spring.integration.mqtt.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "student")
public class Student {
    @Id
    private String id;
    private String firstName;
    private String lastName;
}
