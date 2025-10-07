package com.salas.event.consumer.repository;

import com.salas.event.consumer.module.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<EventEntity, Long> {
}
