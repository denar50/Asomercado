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
 * This DAO processes all the database transactions with the entity MeasurementUnit
 * @author Edgar Santos
 */
@Stateless
public class MeasurementUnitDAO extends AbstractFacade<MeasurementUnit> {
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
    public MeasurementUnitDAO() {
        super(MeasurementUnit.class);
    }
    
    /**
     * 
     * @param list
     * @return A map mapped by primery key of all the measurement units in the database
     */
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
