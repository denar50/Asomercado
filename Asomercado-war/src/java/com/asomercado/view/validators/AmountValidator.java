/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asomercado.view.validators;

import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;

/**
 * Validates the amount of a list item.
 * Validations:
 * - the object must be float.
 * - the value must be greater than 0.01
 * - the value must be less than 1'000.000
 * @author Edgar Santos
 */
@FacesValidator("amountValidator")
public class AmountValidator extends ValidatorBase{

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
            addErrorMessage("list.create.edit.validate.amount.invalid.number.error");
            processErrors();
            return;
        }
        
        
        //Value must be greater or equal to 0.01
        if(value < 0.01)
        {
            addErrorMessage("list.create.edit.validate.amount.greater.than.error");
        }
        
        if(value > 1000000)
        {
            addErrorMessage("list.create.edit.validate.amount.too.big.error");
        }
        /*
        //Value must have at most 2 decimals
        if(Util.numberDecimalsCount(valueString) > 2)
        {
            addErrorMessage(errors, "");
        }
        */
        
        processErrors();
    }
    
    
    
}
