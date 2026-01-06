package com.example.repository;

import com.example.model.EventType;
import com.example.model.RegistrationEvent;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Репозиторий сервиса регистратора.
 */
public interface RegistrationRepository extends JpaRepository<RegistrationEvent, Long>,
    JpaSpecificationExecutor<RegistrationEvent> {

  List<RegistrationEvent> getAllByEventTypeInAndCreatorInAndCreatedByBetween(
      List<EventType> eventType, List<String> creator, Date dateFrom, Date dateTo);

  //AndStartTimeLessThanEqualAndEndTimeGreaterThan
}
