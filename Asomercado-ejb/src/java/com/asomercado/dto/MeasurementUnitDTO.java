/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asomercado.dto;

import com.asomercado.model.MeasurementUnit;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Edgar
 */
public class MeasurementUnitDTO {
    private Integer pk;
    private String description;
    
    public MeasurementUnitDTO()
    {
        
    }
    
    public MeasurementUnitDTO(MeasurementUnit measurementUnit)
    {
        this.pk = measurementUnit.getPk();
        this.description = measurementUnit.getDescription();
    }

    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public static List<MeasurementUnitDTO> getDTOFromEntityList(List<MeasurementUnit> measurementUnits)
    {
        List<MeasurementUnitDTO> measurementUnitsDTOList = new ArrayList<MeasurementUnitDTO>();
        for(MeasurementUnit measurementUnit : measurementUnits)
        {
            measurementUnitsDTOList.add(new MeasurementUnitDTO(measurementUnit));
        }
        
        return measurementUnitsDTOList;
    }
}
