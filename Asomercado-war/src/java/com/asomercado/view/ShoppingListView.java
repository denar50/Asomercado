/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asomercado.view;

import com.asomercado.control.ShoppingListController;
import com.asomercado.dto.MeasurementUnitDTO;
import com.asomercado.model.ListItem;
import com.asomercado.model.ShoppingList;
import com.asomercado.util.Routes;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author USUARIO1
 */
@ManagedBean(name = "ShoppingListView")
@RequestScoped
public class ShoppingListView {
    @EJB
    private ShoppingListController shoppingListController;
    
    private ShoppingList currentShoppingList;

    /**
     * Creates a new instance of NewJSFManagedBean
     */
    public ShoppingListView() {
    }
    
    public String goCreateNewList()
    {
        currentShoppingList = new ShoppingList();
        currentShoppingList.setListItemList(new LinkedList<ListItem>());
        return Routes.CREATE_EDIT_LIST;
    }

    public ShoppingList getCurrentShoppingList() {
        return currentShoppingList;
    }

    public void setCurrentShoppingList(ShoppingList currentShoppingList) {
        this.currentShoppingList = currentShoppingList;
    }
    
    public List<ListItem> getCurrentShoppingListItems()
    {
        return currentShoppingList.getListItemList();
    }
    
    public ListItem getNewListItem()
    {
        return new ListItem();
    }
    
    public List<MeasurementUnitDTO> getMeasurementUnits()
    {
        return shoppingListController.getMeasurementUnits();
    }
    
}
