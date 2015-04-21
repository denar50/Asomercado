/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asomercado.view;

import com.asomercado.control.ListController;
import com.asomercado.model.ListItem;
import com.asomercado.model.MeasurementUnit;
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
@ManagedBean(name = "ListView")
@RequestScoped
public class ListView {
    @EJB
    private ListController listController;
    
    private ShoppingList currentShoppingList;

    /**
     * Creates a new instance of NewJSFManagedBean
     */
    public ListView() {
    }
    
    public String goCreateNewList()
    {
        currentShoppingList = new ShoppingList();
        currentShoppingList.setListItemList(new LinkedList<>());
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
    
    public List<MeasurementUnit> getMeasurementUnits()
    {
        return null;
    }
    
}
