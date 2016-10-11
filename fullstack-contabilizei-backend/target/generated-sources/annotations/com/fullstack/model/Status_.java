package com.fullstack.model;

import com.fullstack.model.PedidoProdutos;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-09T19:59:40")
@StaticMetamodel(Status.class)
public class Status_ { 

    public static volatile CollectionAttribute<Status, PedidoProdutos> pedidoProdutosCollection;
    public static volatile SingularAttribute<Status, String> nome;
    public static volatile SingularAttribute<Status, Long> id;

}