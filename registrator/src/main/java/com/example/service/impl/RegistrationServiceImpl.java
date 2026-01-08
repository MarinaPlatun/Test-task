package com.example.service.impl;

import com.example.dto.FilterDataDto;
import com.example.dto.PaginationData;
import com.example.dto.RegistrationEventDto;
import com.example.dto.SearchResponseDto;
import com.example.model.RegistrationEvent;
import com.example.repository.RegistrationRepository;
import com.example.service.RegistrationEventSpecifications;
import com.example.service.RegistrationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * Сервис для обработки входящих запросов на контроллер.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class RegistrationServiceImpl implements RegistrationService {

  private final RegistrationRepository registrationRepository;

  @Value("${spring.application.name}")
  private String serviceName;

  @Override
  public SearchResponseDto<RegistrationEventDto> getAllRegistration(FilterDataDto filterDataDto,
                                                                    Pageable pageable) {

    log.info("Service {} return all events by filter", serviceName);
    SearchResponseDto<RegistrationEventDto> searchResponseDto = new SearchResponseDto<>();
    Page<RegistrationEvent> registrationEvents;
    Specification<RegistrationEvent> spec = null;
    if (filterDataDto.getDateFrom() != null) {
      spec = RegistrationEventSpecifications.dateFromGreaterThan(
          filterDataDto.getDateFrom());
    }
    if (filterDataDto.getDateTo() != null) {
      spec = spec == null
          ? RegistrationEventSpecifications.dateToLessThan(filterDataDto.getDateTo())
          : spec.and(RegistrationEventSpecifications.dateToLessThan(filterDataDto.getDateTo()));
    }
    if (filterDataDto.getEventType() != null) {
      spec = spec == null
          ? RegistrationEventSpecifications.hasEventType(filterDataDto.getEventType())
          : spec.and(RegistrationEventSpecifications.hasEventType(filterDataDto.getEventType()));
    }
    if (filterDataDto.getCreator() != null && !filterDataDto.getCreator().isEmpty()) {
      spec = spec == null
          ? RegistrationEventSpecifications.hasCreator(filterDataDto.getCreator())
          : spec.and(RegistrationEventSpecifications.hasCreator(filterDataDto.getCreator()));
    }
    if (spec != null) {
      registrationEvents = registrationRepository.findAll(spec, pageable);
      if (registrationEvents.getTotalElements() != 0) {
        searchResponseDto.setPageable(new PaginationData(registrationEvents));
        searchResponseDto.setFilterDataDto(filterDataDto);
        List<RegistrationEventDto> registrationEventDto = registrationEvents.stream()
            .map(registrationEvent -> new RegistrationEventDto(registrationEvent.getId(),
                registrationEvent.getIdEvent(),
                registrationEvent.getEventType(), registrationEvent.getCreator(),
                registrationEvent.getCreatedBy(), registrationEvent.getCreatedByEvent()))
            .toList();
        if (!CollectionUtils.isEmpty(registrationEventDto)) {
          searchResponseDto.setDtoList(registrationEventDto);
        }
      }
    }

    return searchResponseDto;
  }
}

