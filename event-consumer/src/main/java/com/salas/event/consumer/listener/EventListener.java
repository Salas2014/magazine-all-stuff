package com.salas.event.consumer.listener;

import com.salas.event.consumer.module.EventEntity;
import com.salas.event.consumer.repository.EventRepository;
import net.salas.avro.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EventListener {

    private final EventRepository repository;
    private final Logger logger = LoggerFactory.getLogger(EventListener.class);

    public EventListener(EventRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(topics = "events", groupId = "event-group")
    public void listen(Event event) {
        logger.info("Received event {}", event);
        EventEntity entity = new EventEntity();
        entity.setUid(event.getUid());
        entity.setSubject(event.getSubject());
        entity.setDescription(event.getDescription());
        repository.save(entity);

        logger.info("Saved event {}", entity);
    }
}
