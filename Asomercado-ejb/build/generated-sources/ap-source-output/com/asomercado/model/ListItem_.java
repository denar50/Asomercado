package com.asomercado.model;

import com.asomercado.model.ShoppingList;
import com.asomercado.model.Unit;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-04-21T15:42:24")
@StaticMetamodel(ListItem.class)
public class ListItem_ { 

    public static volatile SingularAttribute<ListItem, Float> amount;
    public static volatile SingularAttribute<ListItem, String> description;
    public static volatile SingularAttribute<ListItem, ShoppingList> listFk;
    public static volatile SingularAttribute<ListItem, Integer> pk;
    public static volatile SingularAttribute<ListItem, Unit> unitFk;

}