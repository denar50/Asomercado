package com.asomercado.model;

import com.asomercado.model.ListItem;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-04-21T15:42:24")
@StaticMetamodel(Unit.class)
public class Unit_ { 

    public static volatile SingularAttribute<Unit, String> description;
    public static volatile ListAttribute<Unit, ListItem> listItemList;
    public static volatile SingularAttribute<Unit, Integer> pk;

}