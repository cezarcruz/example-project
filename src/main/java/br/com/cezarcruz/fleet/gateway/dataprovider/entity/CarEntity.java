package br.com.cezarcruz.fleet.gateway.dataprovider.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "car_entity")
@Builder(toBuilder = true)
public class CarEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String plate;

  private Integer mileage;

  private String model;

  private String status;

  @OneToOne
  @JoinColumn(name = "actual_place")
  private PlaceEntity actualPlace;
}
