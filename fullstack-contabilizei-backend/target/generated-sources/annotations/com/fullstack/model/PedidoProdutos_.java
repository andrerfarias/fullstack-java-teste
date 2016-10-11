package com.fullstack.model;

import com.fullstack.model.Pedidos;
import com.fullstack.model.Produtos;
import com.fullstack.model.Status;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-09T19:59:40")
@StaticMetamodel(PedidoProdutos.class)
public class PedidoProdutos_ { 

    public static volatile SingularAttribute<PedidoProdutos, Produtos> produtoId;
    public static volatile SingularAttribute<PedidoProdutos, Pedidos> pedidoId;
    public static volatile SingularAttribute<PedidoProdutos, Status> statusId;
    public static volatile SingularAttribute<PedidoProdutos, Double> valorTotal;
    public static volatile SingularAttribute<PedidoProdutos, Date> dataInclusao;
    public static volatile SingularAttribute<PedidoProdutos, Long> id;
    public static volatile SingularAttribute<PedidoProdutos, Integer> quantidade;
    public static volatile SingularAttribute<PedidoProdutos, Double> valorUnitario;

}