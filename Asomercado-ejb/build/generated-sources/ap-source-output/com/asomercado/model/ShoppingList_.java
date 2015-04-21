package com.asomercado.model;

import com.asomercado.model.ListItem;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-04-21T15:42:24")
@StaticMetamodel(ShoppingList.class)
public class ShoppingList_ { 

    public static volatile SingularAttribute<ShoppingList, Date> createdAt;
    public static volatile SingularAttribute<ShoppingList, String> description;
    public static volatile ListAttribute<ShoppingList, ListItem> listItemList;
    public static volatile SingularAttribute<ShoppingList, Integer> pk;
    public static volatile SingularAttribute<ShoppingList, Date> updatedAt;

}