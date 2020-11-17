package com.project.flowershop.repository;


import com.project.flowershop.model.FlowerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;

@Repository
public interface FlowerOrderRepository extends JpaRepository<FlowerOrder, Integer> {
    
    ArrayList<FlowerOrder> findByOrderDateBetween(LocalDate orderDate1,LocalDate orderDate2);
//    ArrayList<FlowerOrder> findAllByLastName(String lastName); //created but not used in project yet.
    
}