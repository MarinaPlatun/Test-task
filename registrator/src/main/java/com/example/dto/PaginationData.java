package com.example.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

/**
 * Класс для возврата информации о пэйджинге.
 */
@Getter
@Setter
@NoArgsConstructor
public class PaginationData {

  private int currentPage;
  private long numberOfElements;
  private long numberOfResults;

  public PaginationData(Page page) {
    this.currentPage = page.getPageable().getPageNumber();
    this.numberOfElements = page.getNumberOfElements();
    this.numberOfResults = page.getTotalElements();
  }
}

