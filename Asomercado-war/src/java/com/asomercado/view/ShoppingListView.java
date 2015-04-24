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
import javax.faces.event.ValueChangeEvent;

/**
 * View class to interact with shopping lists.
 * This view bean serves as control for the pages "listShow.xhtml" and "listCreateEdit.xhtml".
 * @author Edgar Santos
 */
@ManagedBean(name = "ShoppingListView")
@SessionScoped
public class ShoppingListView extends BaseView{
    /*Controller injection*/
    @EJB
    private ShoppingListController shoppingListController;
    private final int MAX_SHOPPING_LIST_PER_PAGE = 8;
    
    private ShoppingListDTO currentShoppingList;
    private List<ListItemDTO> currentListItems;
    
    /*Collections*/
    private List<Integer> shoppingListTablePages;
    private List<ShoppingListDTO> shoppingLists;
    private Map<Integer, MeasurementUnitDTO> measurementUnitsMap;
    private List<MeasurementUnitDTO> measurementUnitsList;
    
    /*Variable declaration*/
    private ListItemDTO currentListItem;
    private ListItemDTO currentListItemBeforeEdit;
    private boolean isCurrentListItemInEditMode = false;
    private int[] shoppingListPaginationRange;
    private int currentPage;
    private Integer shoppingListTablePagesCount;
    /**
     * Creates a new instance of NewJSFManagedBean
     */
    public ShoppingListView() {
        shoppingListPaginationRange = new int[]{0, MAX_SHOPPING_LIST_PER_PAGE-1};
        currentPage = 1;
    }
    /**
     * Sets up the view in order to create a new shopping list
     * @return 
     */
    public String goCreateNewList()
    {
        currentShoppingList = new ShoppingListDTO();
        currentListItems = new ArrayList<>();
        return Routes.CREATE_EDIT_LIST;
    }
    
    /**
     * Sets up the view in order to edit an existing shopping list
     * @return 
     */
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
            //TODO Exception handling
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 
     * @return current shopping list being edited/created
     */
    public ShoppingListDTO getCurrentShoppingList() {
        return currentShoppingList;
    }

    /**
     * 
     * @param currentShoppingList 
     */
    public void setCurrentListItemList(ShoppingListDTO currentShoppingList) {
        this.currentShoppingList = currentShoppingList;
    }
    
    /**
     * 
     * @return the list items of the current shopping list
     */
    public List<ListItemDTO> getCurrentListItems()
    {
        return currentListItems;
    }

    /**
     * 
     * @return the current list item being edited
     */
    public ListItemDTO getCurrentListItem() {
        if(currentListItem == null)
        {
            currentListItem = new ListItemDTO();
            currentListItem.setMeasurementUnitPk(getMeasurementUnitsList().get(0).getPk());
        }
        return currentListItem;
    }

    /**
     * 
     * @param currentListItem 
     */
    public void setCurrentListItem(ListItemDTO currentListItem) {
        this.currentListItem = currentListItem;
    }
    
    /**
     * sets an item of the current shopping list into edit mode.
     * The item is cloned in case the user cleans the form without saving it.
     * @param listItem 
     */
    public void editItem(ListItemDTO listItem)
    {
        currentListItems.remove(listItem);
        currentListItem = listItem;
        currentListItemBeforeEdit = listItem.clone();
        isCurrentListItemInEditMode = true;
    }
    
    /**
     * deletes an specific item received as parameter.
     * @param item 
     */
    public void deleteItem(ListItemDTO item)
    {
        try
        {
            shoppingListController.deleteListItem(item);
            currentListItems.remove(item);
        }catch(Exception e)
        {
            //TODO Exception handling
            e.printStackTrace();
        }
        
    }
    
    /**
     * Adds the current list item (being edited) to the shopping list. 
     * But first this item is saved to the database.
     */
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
            //TODO Exception handling
            e.printStackTrace();
            currentListItems.remove(currentListItem);
        }
        
        
    }
    
    /**
     * Resets the add/edit item form. 
     * If an item was being edited, the object that was cloned from it is sent back to the shopping list without updating its changes.
     */
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
    
    /**
     * Gets a measurement unit from the database by its primary key
     * @param pk
     * @return 
     */
    public MeasurementUnitDTO getMeasurementUnitDTO(Integer pk)
    {
        return shoppingListController.getMeasurementUnit(pk);
    }

    /**
     * @return a map with all the measure units in the database
     */
    public Map<Integer, MeasurementUnitDTO> getMeasurementUnitsMap() {
        if(measurementUnitsMap == null)
        {
            measurementUnitsMap = MeasurementUnitDTO.getDTOMapFromDTOList(getMeasurementUnitsList());
        }
        return measurementUnitsMap;
    }

    /**
     * 
     * @param measurementUnitsMap 
     */
    public void setMeasurementUnitsMap(Map<Integer, MeasurementUnitDTO> measurementUnitsMap) {
        this.measurementUnitsMap = measurementUnitsMap;
    }
    
    /**
     * @param item
     * @return the amount of an item formatted like this "amount + measure unit". Eg. "Rice 5 Kilograms"
     */
    public String getFormattedAmount(ListItemDTO item)
    {
        return Util.toXDecimals(item.getAmount(), 2) + " " + getMeasurementUnitsMap().get(item.getMeasurementUnitPk()).getDescription();
    }

    /**
     * 
     * @return A list with all the measurement units
     */
    public List<MeasurementUnitDTO> getMeasurementUnitsList() {
        if(measurementUnitsList == null)
        {
           measurementUnitsList = shoppingListController.getMeasurementUnits();
        }
        return measurementUnitsList;
    }

    /**
     * 
     * @param measurementUnitsList 
     */
    public void setMeasurementUnitsList(List<MeasurementUnitDTO> measurementUnitsList) {
        this.measurementUnitsList = measurementUnitsList;
    }
    
    /**
     * Refreshes the table of the shopping lists witht he current page lists.
     * @param page 
     */
    public void refreshShoppingListTablePage(int page)
    {
        currentPage = page;
        shoppingLists = null;
        shoppingListTablePages = null;
        shoppingListTablePagesCount = null;
    }
    
    /**
     * Retrieves the shopping lists based on a range. 
     * @return 
     */
    public List<ShoppingListDTO> getShoppingLists()
    {
        if(shoppingLists == null)
        {
            try
            {
                int totalPages = getShoppingListTablePagesCount();
                if(currentPage > totalPages)
                {
                    currentPage = totalPages;
                }
                refreshShoppingListTablePagesList(totalPages);
                shoppingListPaginationRange[0] = (currentPage - 1)* MAX_SHOPPING_LIST_PER_PAGE;
                shoppingListPaginationRange[1] = shoppingListPaginationRange[0] + MAX_SHOPPING_LIST_PER_PAGE - 1;
                shoppingLists = shoppingListController.getShoppingLists(shoppingListPaginationRange);
            }
            catch(Exception e)
            {
                //TODO Exception handling
                e.printStackTrace();
                return new ArrayList<ShoppingListDTO>();
            }
            
        }
        
        return shoppingLists;
    }
    
    /**
     * On change event handler that is fired whenever the user types a new shopping list name
     * @param event 
     */
    public void updateShoppingListName(ValueChangeEvent event)
    {
        currentShoppingList.setName((String) event.getNewValue());
        updateShoppingList();
    }
    /**
     * Sames the record of the current shopping list
     */
    public void updateShoppingList()
    {
        try
        {
            shoppingListController.saveShoppingList(currentShoppingList);
        }
        catch(Exception e)
        {
            //TODO Exception handling
            e.printStackTrace();
        }
    }
    
    /**
     * Deletes a shopping list from the database.
     * @param shoppingList 
     */
    public void deleteShoppingList(ShoppingListDTO shoppingList)
    {
        try
        {
            shoppingListController.deleteShoppingList(shoppingList);
        }
        catch(Exception e)
        {
            //TODO Exception handler
            e.printStackTrace();
        }
        
        refreshShoppingListTablePage(getCurrentPage());
    }
    
    /**
     * Resets the view so that the user can either create a new shopping list or edit an existing one.
     */
    public void resetView()
    {
        currentListItem = null;
        currentListItemBeforeEdit = null;
        currentListItems = null;
        currentShoppingList = null;
        refreshShoppingListTablePage(currentPage);
    }
    
    /**
     * Takes the user to the shopping lists list page
     * @return 
     */
    public String goToShoppingLists()
    {
        resetView();
        refreshShoppingListTablePage(currentPage);
        return Routes.SHOW_SHOPPING_LIST;
    }
    
    /**
     * Gets the number of pages of the total shopping listing count in the database. 
     * @return 
     */
    private Integer getShoppingListTablePagesCount() 
    {
        if(shoppingListTablePagesCount == null)
        {
            try
            {
                float totalCount = (float) shoppingListController.getShoppingListCount();  
                return ((Double)Math.ceil(totalCount / MAX_SHOPPING_LIST_PER_PAGE)).intValue(); 
            }
            catch(Exception e)
            {
                //TODO Exception handling
                e.printStackTrace();
                return 0;
            }
        }
        return shoppingListTablePagesCount;
        
    }
    
    /**
     * Refresh the shopping list table links in the view
     * @param pages 
     */
    private void refreshShoppingListTablePagesList(int pages)
    {
        shoppingListTablePages = new ArrayList<>();
        for(int i = 1; i<=pages; i++)
        {
            shoppingListTablePages.add(i);
        }
    }

    /**
     * 
     * @return the list with the pages for the shopping list table
     */
    public List<Integer> getShoppingListTablePages() {
        if(shoppingListTablePages == null)
        {
            refreshShoppingListTablePagesList(getShoppingListTablePagesCount());
        }
        return shoppingListTablePages;
    }

    /**
     * @return the current page of the shopping list paginator
     */
    public int getCurrentPage() {
        return currentPage;
    }      
    
}
