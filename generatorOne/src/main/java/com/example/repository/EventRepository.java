package com.example.repository;

import com.example.model.Event;
import com.example.model.StatusType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий сервиса генератора.
 */
public interface EventRepository extends JpaRepository<Event, Long> {

  Long countByStatus(StatusType status);

}
