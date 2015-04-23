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
import com.asomercado.model.ListItem;
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
    
    public void saveListItem(ShoppingListDTO shoppingListDTO, ListItemDTO listItem) throws Exception
    {
        ShoppingList shoppingList = shoppingListDAO.saveShoppingList(shoppingListDTO);
        listItem.setShoppingListPk(shoppingList.getPk());
        MeasurementUnit measurementUnit = measurementUnitDAO.find(listItem.getMeasurementUnitPk());
        listItemDAO.saveListItem(listItem, shoppingList,measurementUnit);
    }
    
    public void deleteListItem(ListItemDTO listItem) throws Exception
    {
        listItemDAO.deleteListItem(listItem);
    }
    
    
    public List<ShoppingListDTO> getShoppingLists()
    {
        List<ShoppingListDTO> dtoList = new ArrayList<ShoppingListDTO>();
        for(ShoppingList entity : shoppingListDAO.findAll())
        {
            ShoppingListDTO dto = new ShoppingListDTO(entity);
            dtoList.add(dto);
        }
        return dtoList;
    }
    
    public List<ListItemDTO> getShoppingListItems(Integer shoppingListPk)
    {
        List<ListItem> entities = shoppingListDAO.find(shoppingListPk).getListItemList();
        List<ListItemDTO> dtos = new ArrayList<>();
        for(ListItem entity : entities)
        {
            dtos.add(new ListItemDTO(entity));
        }
        return dtos;
    }

}
