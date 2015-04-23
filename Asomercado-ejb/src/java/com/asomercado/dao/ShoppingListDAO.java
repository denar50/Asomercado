/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asomercado.dao;

import com.asomercado.dto.ShoppingListDTO;
import com.asomercado.model.ShoppingList;
import com.asomercado.util.Util;
import java.util.Calendar;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author USUARIO1
 */
@Stateless
public class ShoppingListDAO extends AbstractFacade<ShoppingList> {
    @PersistenceContext(unitName = "Asomercado-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ShoppingListDAO() {
        super(ShoppingList.class);
    }

    public ShoppingList saveShoppingList(ShoppingListDTO shoppingListDTO) throws Exception
    {
        ShoppingList shoppingList;
        Date timeNow = Calendar.getInstance().getTime();
        if(shoppingListDTO.getPk() == null)
        {
            shoppingList = new ShoppingList();
            shoppingList.setCreatedAt(timeNow);
            shoppingList.setUpdatedAt(timeNow);
            shoppingList.setName("--");
            create(shoppingList);
            getEntityManager().flush();
        }
        else
        {
            shoppingList = find(shoppingListDTO.getPk());
        }

        shoppingList.setUpdatedAt(timeNow);

        generateShoppingListName(shoppingList, shoppingListDTO);

        edit(shoppingList);
        shoppingListDTO.setPk(shoppingList.getPk());
        return shoppingList;
    }
    
    private void generateShoppingListName(ShoppingList entity, ShoppingListDTO dto)
    {
        if(Util.isEmptyString(dto.getName()))
        {
            StringBuilder name = new StringBuilder();
            name.append(Util.msg.getMessage("shopping.list.default.name"));
            name.append("-");
            name.append(entity.getPk().intValue());
            entity.setName(name.toString());
        }
        else
        {
            entity.setName(dto.getName());
        }
    }
    
    public void deleteShoppingList(ShoppingListDTO shoppingList) throws Exception
    {
        remove(find(shoppingList.getPk()));
    }
}
