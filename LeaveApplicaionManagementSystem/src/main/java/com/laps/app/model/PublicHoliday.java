package com.laps.app.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;



@Entity
@Data
@Table(name = "publicholidays")
public class PublicHoliday {
  
  @Id
  @Column(name = "holidayid")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer holidayId;
  
  @Column(name = "holidayname")
  @NotBlank(message = "Holiday name cannot be blank!")
  private String name;
  
  @Column(name = "holidaydate")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @NotNull(message = "Holiday date cannot be blank!")
  private LocalDate holidayDate;
  
  
}