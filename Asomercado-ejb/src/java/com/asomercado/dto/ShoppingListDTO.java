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
public class ShoppingListDTO extends BaseDTO{
    private String name;
    public ShoppingListDTO()
    {
        
    }
    
    public ShoppingListDTO(ShoppingList shoppingList)
    {
        this.pk = shoppingList.getPk();
        this.name = shoppingList.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
