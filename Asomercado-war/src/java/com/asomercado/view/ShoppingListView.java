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
    
    public void editItem()
    {
        Integer itemPk = Util.stringToInt(getRequestParameter("pk"));
        ListItemDTO listItem = getListItemFromCurrentListItemList(itemPk);
        currentListItems.remove(listItem);
        currentListItem = listItem;
        currentListItemBeforeEdit = listItem.clone();
    }
    public void deleteItem()
    {
        Integer itemPk = Util.stringToInt(getRequestParameter("pk"));
        ListItemDTO listItem = getListItemFromCurrentListItemList(itemPk);
        try
        {
            shoppingListController.deleteListItem(listItem);
            currentListItems.remove(listItem);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }
    public void addNewListItem()
    {
        currentListItem.setModified(true);
        currentListItems.add(currentListItem);
        try
        {
            shoppingListController.saveShoppingList(currentShoppingList, currentListItems);
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
        return item.getAmount() + " " + getMeasurementUnitsMap().get(item.getMeasurementUnitPk()).getDescription();
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
    
    
    
}
