/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asomercado.view.validators;

import com.asomercado.util.Util;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * This is the Base class for all validators created in this project. It has common logic.
 * @author Edgar Santos
 */
public abstract class ValidatorBase implements Validator{
    protected List<FacesMessage> errors = new ArrayList<>();
    @Override
    public abstract void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException;
    
    public void addErrorMessage(String errorMessageKey)
    {
        errors.add(new FacesMessage(Util.msg.getMessage(errorMessageKey)));
    }
    
    public void processErrors()
    {
        if(!errors.isEmpty())
        {
            throw new ValidatorException(errors);
        }
    }
    
}
