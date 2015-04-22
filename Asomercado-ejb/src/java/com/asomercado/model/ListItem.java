/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asomercado.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USUARIO1
 */
@Entity
@Table(name = "list_item")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ListItem.findAll", query = "SELECT l FROM ListItem l"),
    @NamedQuery(name = "ListItem.findByPk", query = "SELECT l FROM ListItem l WHERE l.pk = :pk"),
    @NamedQuery(name = "ListItem.findByAmount", query = "SELECT l FROM ListItem l WHERE l.amount = :amount"),
    @NamedQuery(name = "ListItem.findByDescription", query = "SELECT l FROM ListItem l WHERE l.description = :description")})
public class ListItem implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk")
    private Integer pk;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount")
    private float amount;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "unit_fk", referencedColumnName = "pk")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MeasurementUnit measurementUnit;
    @JoinColumn(name = "list_fk", referencedColumnName = "pk")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ShoppingList shoppingList;

    public ListItem() {
    }

    public ListItem(Integer pk) {
        this.pk = pk;
    }

    public ListItem(Integer pk, float amount, String description) {
        this.pk = pk;
        this.amount = amount;
        this.description = description;
    }

    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MeasurementUnit getMeasurementUnit() {
        return measurementUnit;
    }

    public void setMeasurementUnit(MeasurementUnit measurementUnit) {
        this.measurementUnit = measurementUnit;
    }

    public ShoppingList getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pk != null ? pk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ListItem)) {
            return false;
        }
        ListItem other = (ListItem) object;
        if ((this.pk == null && other.pk != null) || (this.pk != null && !this.pk.equals(other.pk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asomercado.model.ListItem[ pk=" + pk + " ]";
    }
    
}
