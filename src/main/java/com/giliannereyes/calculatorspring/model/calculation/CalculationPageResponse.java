package com.giliannereyes.calculatorspring.model.calculation;

import java.util.List;

/**
 * Paged response for calculation history.
 */
public class CalculationPageResponse {
  private final List<CalculationItemResponse> items;
  private final int page;
  private final int size;
  private final long totalElements;
  private final int totalPages;

  /**
   * Creates a paged calculation response.
   *
   * @param items current page items
   * @param page zero-based page index
   * @param size requested page size
   * @param totalElements total number of stored calculations
   * @param totalPages total number of pages
   */
  public CalculationPageResponse(List<CalculationItemResponse> items, int page, int size, long totalElements,
                                 int totalPages) {
    this.items = items;
    this.page = page;
    this.size = size;
    this.totalElements = totalElements;
    this.totalPages = totalPages;
  }

  /**
   * @return calculations on the current page
   */
  public List<CalculationItemResponse> getItems() {
    return items;
  }

  /**
   * @return zero-based page index
   */
  public int getPage() {
    return page;
  }

  /**
   * @return requested page size
   */
  public int getSize() {
    return size;
  }

  /**
   * @return total number of calculations
   */
  public long getTotalElements() {
    return totalElements;
  }

  /**
   * @return total number of pages
   */
  public int getTotalPages() {
    return totalPages;
  }
}
