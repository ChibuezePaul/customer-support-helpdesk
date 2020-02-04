package com.isoft.customersupport;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass @Data
public abstract class AbstractEntity {
  @Id
  @GeneratedValue ( strategy = GenerationType.IDENTITY )
  protected Integer id;
}
