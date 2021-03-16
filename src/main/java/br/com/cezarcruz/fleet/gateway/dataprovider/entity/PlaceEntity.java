package br.com.cezarcruz.fleet.gateway.dataprovider.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Data;
import javax.persistence.Entity;

@Data
@Entity
public class PlaceEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String description;

  @OneToOne
  private AddressEntity address;

}
