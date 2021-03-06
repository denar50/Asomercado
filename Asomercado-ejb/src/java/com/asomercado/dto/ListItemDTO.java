package com.asomercado.dto;

import com.asomercado.model.ListItem;

/**
 * Data Transport Object for ListItem entities.
 * @author Edgar
 */
public class ListItemDTO extends BaseDTO{
    private String description;
    private float amount;
    private Integer measurementUnitPk;
    private Integer shoppingListPk;
    
    /**
     * Constructor
     */
    public ListItemDTO()
    {
        super();
    }
    
    /**
     * Constructor that extracts all the relevant information of the ListItem entity object
     * @param listItem 
     */
    public ListItemDTO(ListItem listItem)
    {
        super();
        this.pk = listItem.getPk();
        this.description = listItem.getDescription();
        this.amount = listItem.getAmount();
        this.measurementUnitPk = listItem.getMeasurementUnit().getPk();
        this.shoppingListPk = listItem.getShoppingList().getPk();
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
    
    /**
     * Rough implementation of a clone method that returns a new ListItemDTO with all the information of the caller object.
     * @return 
     */
    @Override
    public ListItemDTO clone()
    {
        ListItemDTO newObject = new ListItemDTO();
        newObject.setAmount(amount);
        newObject.setDescription(description);
        newObject.setMeasurementUnitPk(measurementUnitPk);
        newObject.setShoppingListPk(shoppingListPk);
        newObject.setPk(pk);
        return newObject;
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

    public Integer getMeasurementUnitPk() {
        return measurementUnitPk;
    }

    public void setMeasurementUnitPk(Integer measurementUnitPk) {
        this.measurementUnitPk = measurementUnitPk;
    }
    
}
