package com.asomercado.dto;

import com.asomercado.model.MeasurementUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Data Transport Object for ListItem entities.
 * @author Edgar Santos
 */
public class MeasurementUnitDTO extends BaseDTO{
    private String description;
    
    /**
     * Constructor
     */
    public MeasurementUnitDTO()
    {
        super();
    }
    
    /**
     * Constructor that extracts all the relevant information of the MeasurementUnit entity object
     * @param measurementUnit 
     */
    public MeasurementUnitDTO(MeasurementUnit measurementUnit)
    {
        super();
        this.pk = measurementUnit.getPk();
        this.description = measurementUnit.getDescription();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * 
     * @param measurementUnits
     * @return a map whose key is the primary key of a measurement unit. Its value is the measurement unit.
     */
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
