package com.example.service;

import com.example.model.EventType;
import com.example.model.RegistrationEvent;
import java.util.Date;
import org.springframework.data.jpa.domain.Specification;

/**
 * Класс спецификаций для построения запроса к базе данных.
 */
public class RegistrationEventSpecifications {

  public static Specification<RegistrationEvent> dateFromGreaterThan(Date dateFrom) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("createdBy"),
        dateFrom);
  }

  public static Specification<RegistrationEvent> dateToLessThan(Date dateTo) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(
        root.get("createdBy"), dateTo);
  }

  public static Specification<RegistrationEvent> hasEventType(EventType eventType) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("eventType"),
        eventType);
  }

  public static Specification<RegistrationEvent> hasCreator(String creator) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("creator"), creator);
  }

}
