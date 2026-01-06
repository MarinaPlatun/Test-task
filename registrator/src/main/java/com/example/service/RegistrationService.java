package com.example.service;

import com.example.dto.FilterDataDto;
import com.example.dto.RegistrationEventDto;
import com.example.dto.SearchResponseDto;
import org.springframework.data.domain.Pageable;

/**
 * Интерфейс для обработки входящих запросов на контроллер.
 */
public interface RegistrationService {

  SearchResponseDto<RegistrationEventDto> getAllRegistration(FilterDataDto filterDataDto,
                                                             Pageable pageable);
}
