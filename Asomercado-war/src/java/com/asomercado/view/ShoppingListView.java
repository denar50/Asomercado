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
 * View class to interact with shopping lists
 * @author Edgar Santos
 */
@ManagedBean(name = "ShoppingListView")
@SessionScoped
public class ShoppingListView extends BaseView{
    /*Controller injection*/
    @EJB
    private ShoppingListController shoppingListController;
    
    private ShoppingListDTO currentShoppingList;
    private List<ListItemDTO> currentListItems;
    private Map<Integer, MeasurementUnitDTO> measurementUnitsMap;
    private List<MeasurementUnitDTO> measurementUnitsList;
    private ListItemDTO currentListItem;
    private ListItemDTO currentListItemBeforeEdit;
    private boolean isCurrentListItemInEditMode = false;
    private int[] shoppingListPaginationRange;
    private int currentPage;
    private final int maxShoppingListPerPage = 8;
    private List<Integer> shoppingListTablePages;
    private List<ShoppingListDTO> shoppingLists;
    private Integer shoppingListTablePagesCount;
    /**
     * Creates a new instance of NewJSFManagedBean
     */
    public ShoppingListView() {
        shoppingListPaginationRange = new int[]{0, maxShoppingListPerPage-1};
        currentPage = 1;
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
    public void refreshShoppingListTablePage(int page)
    {
        currentPage = page;
        shoppingLists = null;
        shoppingListTablePages = null;
        shoppingListTablePagesCount = null;
    }
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
                shoppingListPaginationRange[0] = (currentPage - 1)* maxShoppingListPerPage;
                shoppingListPaginationRange[1] = shoppingListPaginationRange[0] + maxShoppingListPerPage - 1;
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
    public void updateShoppingListName(ValueChangeEvent event)
    {
        currentShoppingList.setName((String) event.getNewValue());
        updateShoppingList();
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
        
        refreshShoppingListTablePage(getCurrentPage());
    }
    
    public void resetView()
    {
        currentListItem = null;
        currentListItemBeforeEdit = null;
        currentListItems = null;
        currentShoppingList = null;
        refreshShoppingListTablePage(currentPage);
    }
    
    public String goToShoppingLists()
    {
        resetView();
        refreshShoppingListTablePage(currentPage);
        return Routes.SHOW_SHOPPING_LIST;
    }
    private Integer getShoppingListTablePagesCount() 
    {
        if(shoppingListTablePagesCount == null)
        {
            try
            {
                float totalCount = (float) shoppingListController.getShoppingListCount();  
                return ((Double)Math.ceil(totalCount / maxShoppingListPerPage)).intValue(); 
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
    private void refreshShoppingListTablePagesList(int pages)
    {
        shoppingListTablePages = new ArrayList<>();
        for(int i = 1; i<=pages; i++)
        {
            shoppingListTablePages.add(i);
        }
    }

    public List<Integer> getShoppingListTablePages() {
        if(shoppingListTablePages == null)
        {
            refreshShoppingListTablePagesList(getShoppingListTablePagesCount());
        }
        return shoppingListTablePages;
    }

    public int getCurrentPage() {
        return currentPage;
    }      
    
}
