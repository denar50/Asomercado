/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asomercado.view;

import java.util.Map;
import javax.faces.context.FacesContext;

/**
 *
 * @author denar50
 */
public abstract class BaseView {
    
    public String getRequestParameter(String param)
    {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
        return paramMap.get(param);
    }
}
