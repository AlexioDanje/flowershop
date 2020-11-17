package com.project.flowershop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ReportDto {
  private LocalDate orderDate1;
  private LocalDate orderDate2;
}
