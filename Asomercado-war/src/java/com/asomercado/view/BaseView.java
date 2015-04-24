package com.asomercado.view;

import java.util.Map;
import javax.faces.context.FacesContext;

/**
 *The Base class for all views.
 * 
 * @author Edgar Santos
 */
public abstract class BaseView {
    
    /**
     * Returns a parameter contained in the requestParameterMap of the external context.
     * @param param
     * @return 
     */
    public String getRequestParameter(String param)
    {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
        return paramMap.get(param);
    }
}
