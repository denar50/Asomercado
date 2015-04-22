/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asomercado.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author USUARIO1
 */
@Entity
@Table(name = "list")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ShoppingList.findAll", query = "SELECT l FROM ShoppingList l"),
    @NamedQuery(name = "ShoppingList.findByPk", query = "SELECT l FROM ShoppingList l WHERE l.pk = :pk"),
    @NamedQuery(name = "ShoppingList.findByName", query = "SELECT l FROM ShoppingList l WHERE l.name = :name"),
    @NamedQuery(name = "ShoppingList.findByUpdatedAt", query = "SELECT l FROM ShoppingList l WHERE l.updatedAt = :updatedAt"),
    @NamedQuery(name = "ShoppingList.findByCreatedAt", query = "SELECT l FROM ShoppingList l WHERE l.createdAt = :createdAt")})
public class ShoppingList extends BaseModel {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk")
    private Integer pk;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "updated_at")
    @Temporal(TemporalType.DATE)
    private Date updatedAt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    private Date createdAt;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shoppingList", fetch = FetchType.LAZY)
    private java.util.List<ListItem> listItemList;

    public ShoppingList() {
    }

    public ShoppingList(Integer pk) {
        this.pk = pk;
    }

    public ShoppingList(Integer pk, String name, Date updatedAt, Date createdAt) {
        this.pk = pk;
        this.name = name;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @XmlTransient
    public java.util.List<ListItem> getListItemList() {
        return listItemList;
    }

    public void setListItemList(java.util.List<ListItem> listItemList) {
        this.listItemList = listItemList;
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
        if (!(object instanceof ShoppingList)) {
            return false;
        }
        ShoppingList other = (ShoppingList) object;
        if ((this.pk == null && other.pk != null) || (this.pk != null && !this.pk.equals(other.pk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asomercado.model.List[ pk=" + pk + " ]";
    }
    
}
