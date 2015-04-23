/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asomercado.control;

import com.asomercado.dao.ShoppingListDAO;
import com.asomercado.dao.ListItemDAO;
import com.asomercado.dao.MeasurementUnitDAO;
import com.asomercado.dto.ListItemDTO;
import com.asomercado.dto.MeasurementUnitDTO;
import com.asomercado.dto.ShoppingListDTO;
import com.asomercado.model.MeasurementUnit;
import com.asomercado.model.ShoppingList;
import com.asomercado.util.Util;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author USUARIO1
 */
@Stateless
@LocalBean
public class ShoppingListController {
    @EJB
    private MeasurementUnitDAO measurementUnitDAO;
    @EJB
    private ListItemDAO listItemDAO;
    @EJB
    private ShoppingListDAO shoppingListDAO;
    
    public MeasurementUnitDTO getFirstMeasurementUnit()
    {
        return new MeasurementUnitDTO(measurementUnitDAO.findAll().get(0));
    }
    
    public MeasurementUnitDTO getMeasurementUnit(Integer pk)
    {
        return new MeasurementUnitDTO(measurementUnitDAO.find(pk));
    }
    
    public List<MeasurementUnitDTO> getMeasurementUnits()
    {
        List<MeasurementUnitDTO> measurementUnits = new ArrayList<>();
        for(MeasurementUnit entity : measurementUnitDAO.findAll())
        {
            measurementUnits.add(new MeasurementUnitDTO(entity));
        }
        return measurementUnits;
    }
    
    public void saveShoppingList(ShoppingListDTO shoppingListDTO, List<ListItemDTO> listItemList) throws Exception
    {
        ShoppingList shoppingList = shoppingListDAO.saveShoppingList(shoppingListDTO);
        List<ListItemDTO> modifiedListItems = new ArrayList<>();
        for(ListItemDTO listItem : listItemList)
        {
            if(listItem.isModified())
            {
                listItem.setShoppingListPk(shoppingList.getPk());
                modifiedListItems.add(listItem);
            }
        }
        Map<Integer, MeasurementUnit> measurementUnits = measurementUnitDAO.getMeasurementUnitsForListItemsDto(listItemList);
        listItemDAO.saveListItems(modifiedListItems, shoppingList,measurementUnits);
    }
    
    

}
