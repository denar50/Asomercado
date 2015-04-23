/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asomercado.dto;

import java.io.Serializable;

/**
 *
 * @author Edgar
 */
public class BaseDTO implements Serializable{
    
    protected Integer pk;
    protected boolean modified;
    
    protected BaseDTO()
    {
        modified = false;
    }
    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    public boolean isModified() {
        return modified;
    }

    public void setModified(boolean modified) {
        this.modified = modified;
    }
    
    
    
}
