/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asomercado.dto;

import com.asomercado.model.ListItem;

/**
 *
 * @author Edgar
 */
public class ListItemDTO{
    private Integer pk;
    private String description;
    private float amount;
    private MeasurementUnitDTO measurementUnit;
    private Integer shoppingListPk;
    
    public ListItemDTO()
    {
        
    }
    
    public ListItemDTO(ListItem listItem)
    {
        this.pk = listItem.getPk();
        this.description = listItem.getDescription();
        this.amount = listItem.getAmount();
        this.measurementUnit = new MeasurementUnitDTO(listItem.getMeasurementUnit());
        this.shoppingListPk = listItem.getShoppingList().getPk();
    }

    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Integer getShoppingListPk() {
        return shoppingListPk;
    }

    public void setShoppingListPk(Integer shoppingListPk) {
        this.shoppingListPk = shoppingListPk;
    }

    public MeasurementUnitDTO getMeasurementUnit() {
        return measurementUnit;
    }

    public void setMeasurementUnit(MeasurementUnitDTO measurementUnit) {
        this.measurementUnit = measurementUnit;
    }
    
    public String getFormattedAmount()
    {
        return amount + " " + measurementUnit.getDescription();
    }
}
