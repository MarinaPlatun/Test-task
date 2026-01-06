package com.example.controller;

import com.example.dto.FilterDataDto;
import com.example.dto.RegistrationEventDto;
import com.example.dto.SearchResponseDto;
import com.example.service.RegistrationService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для возврата списка событий с поддержкой пэйджинга, фильтрацией по датам (дата начала
 * и дата конца периода), по типу события, по источнику.
 */

@RestController
@RequestMapping("/registrator")
@RequiredArgsConstructor
public class RegistrationController {

  private final RegistrationService registrationService;

  @PostMapping(value = "/all")
  @ApiOperation(value = "Get all events by filter",
      notes = "return events", response = SearchResponseDto.class)
  public SearchResponseDto<RegistrationEventDto> getAllEvents(
      @RequestBody(required = false) FilterDataDto filterDataDto,
      @PageableDefault(value = Integer.MAX_VALUE) Pageable pageable
  ) {
    return registrationService.getAllRegistration(filterDataDto, pageable);
  }


}
