package com.project.flowershop.service;

import com.project.flowershop.dto.CustomerDto;
import com.project.flowershop.model.Customer;
import com.project.flowershop.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
  private final CustomerRepository customerRepository;
  @Autowired
  public CustomerService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  public Customer save(CustomerDto customerDto){
    Customer customer = new Customer();
    customer.setAddress1(customerDto.getAddress1());
    customer.setBirthDate(customerDto.getBirthDate());
    customer.setFullName(customerDto.getFullname());
    customer.setPhoneNumber(customerDto.getPhoneNumber());
    customer.setProvince(customerDto.getProvince());
    customer.setLoyaltyCard(customerDto.getLoyaltyCard());
    return customerRepository.save(customer);
  }

}
