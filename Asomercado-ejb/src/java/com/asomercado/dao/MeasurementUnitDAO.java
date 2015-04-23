/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asomercado.dao;

import com.asomercado.dto.ListItemDTO;
import com.asomercado.model.MeasurementUnit;
import com.asomercado.util.Util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author USUARIO1
 */
@Stateless
public class MeasurementUnitDAO extends AbstractFacade<MeasurementUnit> {
    @PersistenceContext(unitName = "Asomercado-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MeasurementUnitDAO() {
        super(MeasurementUnit.class);
    }
    
    public Map<Integer, MeasurementUnit> getMeasurementUnitsForListItemsDto(List<ListItemDTO> listItemsDto)
    {
        String queryString = "SELECT measurementUnit from MeasurementUnit measurementUnit where measurementUnit.pk IN :pks"; 
        Query queryObject = getEntityManager().createQuery(queryString, MeasurementUnit.class);
        List<String> pks = new ArrayList<>();
        for(ListItemDTO dto : listItemsDto)
        {
            pks.add(Util.intToString(dto.getMeasurementUnitPk()));
        }

        queryObject.setParameter("pks", pks);
        List<MeasurementUnit> results = (List<MeasurementUnit>) queryObject.getResultList();
        return getMapFromList(results);
   }
    
   public Map<Integer, MeasurementUnit> getMapFromList(List<MeasurementUnit> list)
   {
       Map<Integer, MeasurementUnit> map = new HashMap<>();
       for(MeasurementUnit entity : list)
       {
           map.put(entity.getPk(), entity);
       }
       return map;
   }
    
}
