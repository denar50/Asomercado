package com.asomercado.dto;

import java.io.Serializable;

/**
 * parent class of all DTO in this application. It (must) contains common logic
 * @author Edgar Santos
 */
public class BaseDTO implements Serializable{
    
    protected Integer pk;
    protected boolean modified;
    
    /**
     * Constructor. Sets the modified property to false by default.
     */
    protected BaseDTO()
    {
        modified = false;
    }
    
    /**
     * 
     * @return the primary key
     */
    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    /**
     * 
     * @return whether the DTO property modified HAS BEEN SET to true or not.
     */
    public boolean isModified() {
        return modified;
    }

    public void setModified(boolean modified) {
        this.modified = modified;
    }
    
    
    
}
