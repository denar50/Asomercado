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
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author USUARIO1
 */
@FacesValidator("amountValidator")
public class AmountValidator implements Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object valueObject) throws ValidatorException {
        
        Float value;
        List<FacesMessage> errors = new ArrayList<>();
        
        //Value must be a float 
        try
        {
            value = (Float) valueObject; 
        }
        catch(Exception e)
        {
            addErrorMessage(errors, "list.create.edit.validate.amount.invalid.number.error");
            processErrors(errors);
            return;
        }
        
        
        //Value must be greater or equal to 0.01
        if(value < 0.01)
        {
            addErrorMessage(errors, "list.create.edit.validate.amount.greater.than.error");
        }
        
        
        /*
        //Value must have at most 2 decimals
        if(Util.numberDecimalsCount(valueString) > 2)
        {
            addErrorMessage(errors, "");
        }
        */
        
        processErrors(errors);
    }
    
    public void addErrorMessage(List<FacesMessage> errorList, String errorMessageKey)
    {
        errorList.add(new FacesMessage(Util.msg.getMessage(errorMessageKey)));
    }
    
    public void processErrors(List<FacesMessage> errors)
    {
        if(!errors.isEmpty())
        {
            throw new ValidatorException(errors);
        }
    }
    
}
