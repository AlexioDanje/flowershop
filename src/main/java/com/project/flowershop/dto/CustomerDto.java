package com.project.flowershop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerDto {
  private String address1;

  private String fullname;

  private String city;

  private String province;

  private String postalCode;

  private String phoneNumber;

  private String birthDate;

  private String loyaltyCard;
}
