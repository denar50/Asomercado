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
            ShoppingList shoppingList = new ShoppingList();
            Date timeNow = Calendar.getInstance().getTime();
            shoppingList.setCreatedAt(timeNow);
            shoppingList.setUpdatedAt(timeNow);
            create(shoppingList);
            if(Util.isEmptyString(shoppingListDTO.getName()))
            {
                StringBuilder name = new StringBuilder();
                name.append(Util.msg.getMessage("shopping.list.default.name"));
                name.append("-");
                name.append(shoppingList.getPk().intValue());
                shoppingList.setName(name.toString());
            }
            else
            {
                shoppingList.setName(shoppingListDTO.getName());
            }

            edit(shoppingList);
            shoppingListDTO.setPk(shoppingList.getPk());
            return shoppingList;
    }
    
}
