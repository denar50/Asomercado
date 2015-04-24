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
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 * Manages all the business logic of the application.
 * Three DAO's are injected to it to manage the tables related to shopping lists, list items and measurement units.
 * @author Edgar Santos
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
    
    /*MeasurementUnit entity related methods*/
    
    /**
     * Retrieves the first measurement unit in the list returned by the findAll() method in MeasurementUnitDAO.
     * 
     * @return A DTO containing the first measurement unit.
     */
    public MeasurementUnitDTO getFirstMeasurementUnit()
    {
        return new MeasurementUnitDTO(measurementUnitDAO.findAll().get(0));
    }
    
    /**
     * Gets the DTO of the measurement unit by its primary key.
     * @param pk - primary key of the MeasurementUnits
     * @return a DTO with the information of the measurement unit.
     */
    public MeasurementUnitDTO getMeasurementUnit(Integer pk)
    {
        return new MeasurementUnitDTO(measurementUnitDAO.find(pk));
    }
    
    /**
     * 
     * @return a list of DTO with all the measurement units from the database.
     */
    public List<MeasurementUnitDTO> getMeasurementUnits()
    {
        List<MeasurementUnitDTO> measurementUnits = new ArrayList<>();
        for(MeasurementUnit entity : measurementUnitDAO.findAll())
        {
            measurementUnits.add(new MeasurementUnitDTO(entity));
        }
        return measurementUnits;
    }

    /*ListItem entity related methods*/
    
    /**
     * Saves a ListItem. First the shopping list is saved and then,
     * with a reference to it and to a measurement unit, the list item is updated.
     * @param shoppingListDTO
     * @param listItem
     * @throws Exception 
     */
    public void saveListItem(ShoppingListDTO shoppingListDTO, ListItemDTO listItem) throws Exception
    {
        ShoppingList shoppingList = shoppingListDAO.saveShoppingList(shoppingListDTO);
        listItem.setShoppingListPk(shoppingList.getPk());
        MeasurementUnit measurementUnit = measurementUnitDAO.find(listItem.getMeasurementUnitPk());
        listItemDAO.saveListItem(listItem, shoppingList,measurementUnit);
    }
    
    /**
     * Deletes a ListItem.
     * @param listItem
     * @throws Exception 
     */
    public void deleteListItem(ListItemDTO listItem) throws Exception
    {
        listItemDAO.deleteListItem(listItem);
    }
    
    /*ShoppingList entity related methods*/
    
    /**
     * Saves the shopping referenced to by the DTO received as parameter.
     * @param shoppingListDTO
     * @throws Exception 
     */
    public void saveShoppingList(ShoppingListDTO shoppingListDTO) throws Exception
    {
        shoppingListDAO.saveShoppingList(shoppingListDTO);
    }
    
    /**
     * @param range
     * @return a list of ShoppingListDTO containing all the shopping lists in the database based on the given range
     */
    public List<ShoppingListDTO> getShoppingLists(int[] range)
    {
        List<ShoppingListDTO> dtoList = new ArrayList<>();
        for(ShoppingList entity : shoppingListDAO.findRange(range))
        {
            ShoppingListDTO dto = new ShoppingListDTO(entity);
            dtoList.add(dto);
        }
        return dtoList;
    }
    /**
     * 
     * @param shoppingListPk
     * @return A list of ListItemDTO representing the items in the shopping list
     * which primary key is received as parameter.
     * @throws Exception 
     */
    public List<ListItemDTO> getShoppingListItems(Integer shoppingListPk) throws Exception
    {
        List<ListItem> entities = listItemDAO.getItemsByShoppingList(shoppingListPk);
        List<ListItemDTO> dtos = new ArrayList<>();
        for(ListItem entity : entities)
        {
            dtos.add(new ListItemDTO(entity));
        }
        return dtos;
    }
    
    /**
     * Deletes the shopping list and all its items.
     * @param shoppingListDTO
     * @throws Exception 
     */
    public void deleteShoppingList(ShoppingListDTO shoppingListDTO) throws Exception
    {
        listItemDAO.deleteItemsFromShoppingList(shoppingListDTO.getPk());
        shoppingListDAO.deleteShoppingList(shoppingListDTO);
    }
    
    /**
     * @return the total count of all shopping lists.
     * @throws Exception 
     */
    public int getShoppingListCount() throws Exception
    {
        return shoppingListDAO.count();
    }
}
