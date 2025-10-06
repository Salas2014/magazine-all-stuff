package com.salas.producers.ervice;

import net.salas.avro.Event;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.UUID;

@SpringBootApplication
@EnableScheduling
public class ProducerServiceApplication implements CommandLineRunner {

    private final KafkaTemplate<String, Event> kafkaTemplate;
    @Value("${event.topic}")
    private String topic;

    public ProducerServiceApplication(KafkaTemplate<String, Event> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProducerServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Event producer started");
    }

    @Scheduled(fixedRateString = "${event.generation.interval-ms}")
    public void sendEvent() {
        Event event = Event.newBuilder()
                .setUid(UUID.randomUUID().toString())
                .setSubject("subject")
                .setDescription("description")
                .build();

        kafkaTemplate.send(topic, event.getUid().toString(), event);
        System.out.println("Sent: " + event);
    }

}
