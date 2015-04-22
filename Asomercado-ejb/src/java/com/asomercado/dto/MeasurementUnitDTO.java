/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asomercado.dto;

import com.asomercado.model.MeasurementUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Edgar
 */
public class MeasurementUnitDTO extends BaseDTO{
    private String description;
    
    public MeasurementUnitDTO()
    {
    }
    
    public MeasurementUnitDTO(MeasurementUnit measurementUnit)
    {
        this.pk = measurementUnit.getPk();
        this.description = measurementUnit.getDescription();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public static Map<Integer, MeasurementUnitDTO> getDTOMapFromDTOList(List<MeasurementUnitDTO> measurementUnits)
    {
        Map<Integer, MeasurementUnitDTO> measurementUnitsDTOList = new HashMap<>();
        for(MeasurementUnitDTO dto : measurementUnits)
        {
            measurementUnitsDTOList.put(dto.getPk(), dto);
        }
        
        return measurementUnitsDTOList;
    }
}
