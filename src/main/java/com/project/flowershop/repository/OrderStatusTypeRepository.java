/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.flowershop.repository;


import com.project.flowershop.model.OrderStatusType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author The source
 */
public interface OrderStatusTypeRepository extends JpaRepository<OrderStatusType, Integer> {
    
}
