/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.flowershop.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author bjmac
 */
@Entity
@Table(name = "flowerorder")
public class FlowerOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "customerId")
    private Integer customerId;

    @Column(name = "orderDate")
    private LocalDate orderDate;
    @Column(name = "item1")
    private Integer item1;
    @Column(name = "item2")
    private Integer item2;
    @Column(name = "item3")
    private Integer item3;
    @Column(name = "item4")
    private Integer item4;
    @Column(name = "item5")
    private Integer item5;
    @Column(name = "item6")
    private Integer item6;
    @Column(name = "orderStatus")
    private Integer orderStatus;
    @Column(name = "totalCost")
    private BigDecimal totalCost;
    @Column(name = "amountPaid")
    private BigDecimal amountPaid;

    @Column(name = "createdDateTime")
    private String createdDateTime;

    @Transient
    public String flowerorderTypeDescription;

    public String getFlowerOrderTypeDescription() {
        return flowerorderTypeDescription;
    }

    public void setFlowerOrderTypeDescription(String flowerorderTypeDescription) {
        this.flowerorderTypeDescription = flowerorderTypeDescription;
    }
    
    public FlowerOrder() {
    }

    public FlowerOrder(Integer id) {
        this.id = id;
    }

    public FlowerOrder(Integer id, Integer customerId, Integer item1, Integer item2, Integer item3, Integer item4, Integer item5, Integer item6, Integer orderStatus, BigDecimal totalCost, BigDecimal amountPaid) {
        this.id = id;
        this.customerId = customerId; 
        this.orderDate = LocalDate.now();
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3; 
        this.item4 = item4; 
        this.item6 = item6; 
        this.orderStatus = orderStatus;
        this.totalCost = totalCost; 
        this.amountPaid = amountPaid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    
    /**
     * @return the customerId
     */
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId the customerId to set
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     * @return the orderDate
     */
    public LocalDate getOrderDate() {
        return orderDate;
    }

    /**
     * @param orderDate the orderDate to set
     */
    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * @return the item1
     */
    public Integer getItem1() {
        return item1;
    }

    /**
     * @param item1 the item1 to set
     */
    public void setItem1(Integer item1) {
        this.item1 = item1;
    }

    /**
     * @return the item2
     */
    public Integer getItem2() {
        return item2;
    }

    /**
     * @param item2 the item2 to set
     */
    public void setItem2(Integer item2) {
        this.item2 = item2;
    }

    /**
     * @return the item3
     */
    public Integer getItem3() {
        return item3;
    }

    /**
     * @param item3 the item3 to set
     */
    public void setItem3(Integer item3) {
        this.item3 = item3;
    }

    /**
     * @return the item4
     */
    public Integer getItem4() {
        return item4;
    }

    /**
     * @param item4 the item4 to set
     */
    public void setItem4(Integer item4) {
        this.item4 = item4;
    }

    /**
     * @return the item5
     */
    public Integer getItem5() {
        return item5;
    }

    /**
     * @param item5 the item5 to set
     */
    public void setItem5(Integer item5) {
        this.item5 = item5;
    }

    /**
     * @return the item6
     */
    public Integer getItem6() {
        return item6;
    }

    /**
     * @param item6 the item6 to set
     */
    public void setItem6(Integer item6) {
        this.item6 = item6;
    }

    /**
     * @return the orderStatus
     */
    public Integer getOrderStatus() {
        return orderStatus;
    }

    /**
     * @param orderStatus the orderStatus to set
     */
    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * @return the totalCost
     */
    public BigDecimal getTotalCost() {
        return totalCost;
    }

    /**
     * @param totalCost the totalCost to set
     */
    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    /**
     * @return the amountPaid
     */
    public BigDecimal getAmountPaid() {
        return amountPaid;
    }

    /**
     * @param amountPaid the amountPaid to set
     */
    public void setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FlowerOrder)) {
            return false;
        }
        FlowerOrder other = (FlowerOrder) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "info.hccis.camper.jpa.entity.FlowerOrder[ id=" + id + " ]";
    }

    
}
