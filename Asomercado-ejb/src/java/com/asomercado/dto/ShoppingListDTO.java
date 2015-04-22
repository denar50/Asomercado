/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asomercado.dto;

import com.asomercado.model.ShoppingList;

/**
 *
 * @author Edgar
 */
public class ShoppingListDTO {
    private Integer pk;
    private String name;
    public ShoppingListDTO()
    {
        
    }
    
    public ShoppingListDTO(ShoppingList shoppingList)
    {
        this.pk = shoppingList.getPk();
        this.name = shoppingList.getName();
    }

    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
