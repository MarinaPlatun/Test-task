package com.example.model;

/**
 * Типы статусов событий.
 */
public enum StatusType {

  NEW("NEW"),
  CONFIRMED("CONFIRMED");

  private final String name;

  StatusType(String name) {
    this.name = name;
  }
}
