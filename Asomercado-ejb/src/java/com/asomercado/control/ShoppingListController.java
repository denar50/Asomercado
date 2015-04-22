/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asomercado.control;

import com.asomercado.dao.ShoppingListDAO;
import com.asomercado.dao.ListItemDAO;
import com.asomercado.dao.MeasurementUnitDAO;
import com.asomercado.dto.MeasurementUnitDTO;
import com.asomercado.model.MeasurementUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author USUARIO1
 */
@Stateless
@LocalBean
public class ShoppingListController {
    @EJB
    private MeasurementUnitDAO measurementUnitDAO;
    @EJB
    private ListItemDAO listItemDAO;
    @EJB
    private ShoppingListDAO shoppingListDAO;
    
    public MeasurementUnitDTO getFirstMeasurementUnit()
    {
        return new MeasurementUnitDTO(measurementUnitDAO.findAll().get(0));
    }
    
    public MeasurementUnitDTO getMeasurementUnit(Integer pk)
    {
        return new MeasurementUnitDTO(measurementUnitDAO.find(pk));
    }
    
    public List<MeasurementUnitDTO> getMeasurementUnits()
    {
        List<MeasurementUnitDTO> measurementUnits = new ArrayList<>();
        for(MeasurementUnit entity : measurementUnitDAO.findAll())
        {
            measurementUnits.add(new MeasurementUnitDTO(entity));
        }
        return measurementUnits;
    }

}
