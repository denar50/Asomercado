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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
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
    
    public void saveListItems(List<ListItemDTO> dtos, ShoppingList shoppingList,Map<Integer, MeasurementUnit> measurementUnits) throws Exception
    {
        EntityTransaction transaction = null;
        try {
            transaction = getEntityManager().getTransaction();
            transaction.begin();

            for(ListItemDTO dto : dtos)
            {
                ListItem entity = new ListItem();
                entity.setAmount(dto.getAmount());
                entity.setDescription(dto.getDescription());
                entity.setMeasurementUnit(measurementUnits.get(dto.getMeasurementUnitPk()));
                entity.setShoppingList(shoppingList);
                create(entity);
                dto.setPk(entity.getPk());
                dto.setModified(false);
            }

            transaction.commit();
        }
        catch (RuntimeException e) {
            if ( transaction != null && transaction.isActive() )
            {
                transaction.rollback();
            }
            throw e; 
        }
        finally {
            getEntityManager().close();
        }
    }

    
}
