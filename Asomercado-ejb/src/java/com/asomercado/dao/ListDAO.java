/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asomercado.dao;

import com.asomercado.model.ShoppingList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author USUARIO1
 */
@Stateless
public class ListDAO extends AbstractFacade<ShoppingList> {
    @PersistenceContext(unitName = "Asomercado-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ListDAO() {
        super(ShoppingList.class);
    }
    
}
