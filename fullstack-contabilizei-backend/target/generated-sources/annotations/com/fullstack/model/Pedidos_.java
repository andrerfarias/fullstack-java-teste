package com.fullstack.model;

import com.fullstack.model.Clientes;
import com.fullstack.model.PedidoProdutos;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-09T19:59:40")
@StaticMetamodel(Pedidos.class)
public class Pedidos_ { 

    public static volatile SingularAttribute<Pedidos, Clientes> cliente;
    public static volatile CollectionAttribute<Pedidos, PedidoProdutos> pedidoProdutosCollection;
    public static volatile SingularAttribute<Pedidos, Double> valorTotal;
    public static volatile SingularAttribute<Pedidos, Long> id;
    public static volatile SingularAttribute<Pedidos, Date> dataEmissao;

}