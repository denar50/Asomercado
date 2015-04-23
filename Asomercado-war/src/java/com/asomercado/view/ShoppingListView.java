/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asomercado.view;

import com.asomercado.control.ShoppingListController;
import com.asomercado.dto.ListItemDTO;
import com.asomercado.dto.MeasurementUnitDTO;
import com.asomercado.dto.ShoppingListDTO;
import com.asomercado.util.Routes;
import com.asomercado.util.Util;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.convert.FloatConverter;
import org.jboss.weld.context.RequestContext;

/**
 *
 * @author USUARIO1
 */
@ManagedBean(name = "ShoppingListView")
@SessionScoped
public class ShoppingListView extends BaseView{
    @EJB
    private ShoppingListController shoppingListController;
    
    private ShoppingListDTO currentShoppingList;
    private List<ListItemDTO> currentListItems;
    private Map<Integer, ListItemDTO> currentListItemsMap;
    private Map<Integer, MeasurementUnitDTO> measurementUnitsMap;
    private List<MeasurementUnitDTO> measurementUnitsList;
    private ListItemDTO currentListItem;
    private ListItemDTO currentListItemBeforeEdit;
    private boolean isCurrentListItemInEditMode = false;
    final private FloatConverter floatConverter = new FloatConverter();
    /**
     * Creates a new instance of NewJSFManagedBean
     */
    public ShoppingListView() {

    }
    
    public String goCreateNewList()
    {
        currentShoppingList = new ShoppingListDTO();
        currentListItems = new ArrayList<>();
        return Routes.CREATE_EDIT_LIST;
    }
    
    public String goEditShoppingList(ShoppingListDTO shoppingListDTO)
    { 
        try
        {
            currentShoppingList = shoppingListDTO;
            currentListItems = shoppingListController.getShoppingListItems(currentShoppingList.getPk());
            return Routes.CREATE_EDIT_LIST;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return "";
    }

    public ShoppingListDTO getCurrentShoppingList() {
        return currentShoppingList;
    }

    public void setCurrentListItemList(ShoppingListDTO currentShoppingList) {
        this.currentShoppingList = currentShoppingList;
    }
    
    public List<ListItemDTO> getCurrentListItems()
    {
        return currentListItems;
    }

    public ListItemDTO getCurrentListItem() {
        if(currentListItem == null)
        {
            currentListItem = new ListItemDTO();
            currentListItem.setMeasurementUnitPk(getMeasurementUnitsList().get(0).getPk());
        }
        return currentListItem;
    }

    public void setCurrentListItem(ListItemDTO currentListItem) {
        this.currentListItem = currentListItem;
    }
    
    public void editItem(ListItemDTO listItem)
    {
        currentListItems.remove(listItem);
        currentListItem = listItem;
        currentListItemBeforeEdit = listItem.clone();
        isCurrentListItemInEditMode = true;
    }
    public void deleteItem(ListItemDTO item)
    {
        try
        {
            shoppingListController.deleteListItem(item);
            currentListItems.remove(item);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }
    public void addListItem()
    {
        currentListItem.setModified(true);
        currentListItems.add(currentListItem);
        try
        {
            shoppingListController.saveListItem(currentShoppingList, currentListItem);
            isCurrentListItemInEditMode = false;
            resetAddItemForm();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            currentListItems.remove(currentListItem);
        }
        
        
    }
    
    public void resetAddItemForm()
    {
        if(isCurrentListItemInEditMode)
        {
            currentListItems.add(currentListItemBeforeEdit);
            currentListItemBeforeEdit = null;
            isCurrentListItemInEditMode = false;
        }
        currentListItem = null;
    }
    
    public FloatConverter getFloatConverter()
    {
        return floatConverter;
    }
    
    public MeasurementUnitDTO getMeasurementUnitDTO(Integer pk)
    {
        return shoppingListController.getMeasurementUnit(pk);
    }

    public Map<Integer, MeasurementUnitDTO> getMeasurementUnitsMap() {
        if(measurementUnitsMap == null)
        {
            measurementUnitsMap = MeasurementUnitDTO.getDTOMapFromDTOList(getMeasurementUnitsList());
        }
        return measurementUnitsMap;
    }

    public void setMeasurementUnitsMap(Map<Integer, MeasurementUnitDTO> measurementUnitsMap) {
        this.measurementUnitsMap = measurementUnitsMap;
    }
    
    public String getFormattedAmount(ListItemDTO item)
    {
        return Util.toXDecimals(item.getAmount(), 2) + " " + getMeasurementUnitsMap().get(item.getMeasurementUnitPk()).getDescription();
    }

    public List<MeasurementUnitDTO> getMeasurementUnitsList() {
        if(measurementUnitsList == null)
        {
           measurementUnitsList = shoppingListController.getMeasurementUnits();
        }
        return measurementUnitsList;
    }

    public void setMeasurementUnitsList(List<MeasurementUnitDTO> measurementUnitsList) {
        this.measurementUnitsList = measurementUnitsList;
    }
    
    public ListItemDTO getListItemFromCurrentListItemList(Integer pk)
    {
        ListItemDTO searchResult = null;
        for(ListItemDTO listItem : getCurrentListItems())
        {
            if(listItem.getPk().intValue() == pk.intValue())
            {
                searchResult = listItem;
                break;
            }
        }
        return searchResult;
    }
    
    public List<ShoppingListDTO> getShoppingLists()
    {
        return shoppingListController.getShoppingLists();
    }
    
    public void updateShoppingList()
    {
        try
        {
            shoppingListController.saveShoppingList(currentShoppingList);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void deleteShoppingList(ShoppingListDTO shoppingList)
    {
        try
        {
            shoppingListController.deleteShoppingList(shoppingList);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void resetView()
    {
        currentListItem = null;
        currentListItemBeforeEdit = null;
        currentListItems = null;
        currentListItemsMap = null;
        currentShoppingList = null;
    }
    
    public String goToShoppingLists()
    {
        resetView();
        return Routes.SHOW_SHOPPING_LIST;
    }
}
