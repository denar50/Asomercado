/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author USUARIO1
 */
@Stateless
public class ListItemDAO extends AbstractFacade<ListItem> {
    @PersistenceContext(unitName = "Asomercado-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ListItemDAO() {
        super(ListItem.class);
    }
    
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
    
    public void deleteListItem(ListItemDTO dto) throws Exception
    {
        ListItem entity = find(dto.getPk());
        remove(entity);
    }
    
    public List<ListItem> getItemsByShoppingList(Integer shoppingListPk) throws Exception
    {
        return (List<ListItem>) getEntityManager().createNamedQuery("ListItem.findByShoppingList").setParameter("shoppingListPk", shoppingListPk).getResultList();
    }
    
    public void deleteItemsFromShoppingList(Integer shoppingListPk) throws Exception
    {
        for(ListItem listItem : getItemsByShoppingList(shoppingListPk))
        {
            remove(listItem);
        }
    }
}
