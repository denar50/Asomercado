package com.asomercado.dao;

import com.asomercado.dto.ListItemDTO;
import com.asomercado.model.ListItem;
import com.asomercado.model.MeasurementUnit;
import com.asomercado.model.ShoppingList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * This DAO processes all the database transactions with the entity ListItem
 * @author Edgar Santos
 */
@Stateless
public class ListItemDAO extends AbstractFacade<ListItem> {
    @PersistenceContext(unitName = "Asomercado-ejbPU")
    private EntityManager em;

    /**
     * 
     * @return the entity manager of the transaction 
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * Constructor
     */
    public ListItemDAO() {
        super(ListItem.class);
    }
    
    /**
     * Saves a listItem whose DTO is received as parameter. If the list doesn't exist, it is created automatically.
     * @param dto
     * @param shoppingList
     * @param measurementUnit
     * @throws Exception 
     */
    public void saveListItem(ListItemDTO dto, ShoppingList shoppingList,MeasurementUnit measurementUnit) throws Exception
    {
        ListItem entity;
        boolean isUpdate = false;
        if(dto.getPk() == null)
        {
            entity = new ListItem();
        }
        else
        {
            entity = find(dto.getPk());
            isUpdate = true;
        }
        entity.setAmount(dto.getAmount());
        entity.setDescription(dto.getDescription());
        entity.setMeasurementUnit(measurementUnit);
        entity.setShoppingList(shoppingList);
        if(isUpdate)
        {
            edit(entity);
        }
        else
        {
            create(entity);
        }

        getEntityManager().flush();
        dto.setPk(entity.getPk());
        dto.setModified(false);
    }
    /**
     * Deletes a list item from the database.
     * @param dto
     * @throws Exception 
     */
    public void deleteListItem(ListItemDTO dto) throws Exception
    {
        ListItem entity = find(dto.getPk());
        remove(entity);
    }
    
    /**
     * @param shoppingListPk
     * @return all the list items related to a shopping list whose primary key is received as parameter
     * @throws Exception 
     */
    public List<ListItem> getItemsByShoppingList(Integer shoppingListPk) throws Exception
    {
        return (List<ListItem>) getEntityManager().createNamedQuery("ListItem.findByShoppingList").setParameter("shoppingListPk", shoppingListPk).getResultList();
    }
    
    /**
     * Deletes from the database all items related to a shopping list whose primary key is received as parameter.
     * @param shoppingListPk
     * @throws Exception 
     */
    public void deleteItemsFromShoppingList(Integer shoppingListPk) throws Exception
    {
        for(ListItem listItem : getItemsByShoppingList(shoppingListPk))
        {
            remove(listItem);
        }
    }
}
