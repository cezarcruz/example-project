package br.com.cezarcruz.fleet.core.model;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CarStatus {

  ACTIVE(1, "active"),
  RENTED(2, "rented"),
  IN_MAINTENANCE(3, "maintenance"),
  IN_WASH(4, "washing"),
  INACTIVE(5, "inactive"),
  CREATED(6, "created");

  private final Integer id;
  private final String label;

  public static CarStatus of(final String status) {
    return Arrays.stream(CarStatus.values())
        .filter(c -> c.getLabel().equals(status))
        .findFirst()
        .orElseThrow(() -> new RuntimeException("invalid state"));
  }
}
