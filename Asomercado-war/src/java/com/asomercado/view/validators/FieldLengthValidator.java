/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asomercado.view.validators;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;

/**
 * Validates the amount of a list item.
 * Validations:
 * - the length of the value is up to 250 or less
 * @author Edgar Santos
 */
@FacesValidator("fieldLengthValidator")
public class FieldLengthValidator extends ValidatorBase{

    @Override
    public void validate(FacesContext context, UIComponent component, Object valueObject) throws ValidatorException {
        String value = (String) valueObject;
        
        if(value.length() > 250)
        {
            addErrorMessage("list.create.edit.validate.text.too.long.error");
        }
        
        processErrors();
    }
    
}
