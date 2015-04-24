package com.asomercado.dto;

import com.asomercado.model.ShoppingList;

/**
 * Data Transport Object for ShoppingList entities.
 * @author Edgar Santos
 */
public class ShoppingListDTO extends BaseDTO{
    private String name;
    /**
     * Constructor
     */
    public ShoppingListDTO()
    {
        super();
    }
    
    /**
     * Constructor that extracts all the relevant information of the ShoppingList entity object
     * @param shoppingList 
     */
    public ShoppingListDTO(ShoppingList shoppingList)
    {
        super();
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
