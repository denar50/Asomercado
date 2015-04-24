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
 * This DAO processes all the database transactions with the entity ShoppingList
 * @author Edgar Santos
 */
@Stateless
public class ShoppingListDAO extends AbstractFacade<ShoppingList> {
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
    public ShoppingListDAO() {
        super(ShoppingList.class);
    }

    /**
     * Saves the information of a shopping list. If it doesn't exist, it is created.
     * @param shoppingListDTO
     * @return
     * @throws Exception 
     */
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
    
    /**
     * In case there's no dame defined in the name field of the dto received as parameter,
     * a generic name is generated. This generic name is created by apending a generic string and the primary key of the list.
     * @param entity
     * @param dto 
     */
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
    
    /**
     * Deletes a shopping list from the database.
     * @param shoppingList
     * @throws Exception 
     */
    public void deleteShoppingList(ShoppingListDTO shoppingList) throws Exception
    {
        remove(find(shoppingList.getPk()));
    }
}
