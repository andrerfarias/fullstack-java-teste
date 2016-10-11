package com.fullstack.model;

import com.fullstack.model.PedidoProdutos;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-09T19:59:40")
@StaticMetamodel(Produtos.class)
public class Produtos_ { 

    public static volatile CollectionAttribute<Produtos, PedidoProdutos> pedidoProdutosCollection;
    public static volatile SingularAttribute<Produtos, String> nome;
    public static volatile SingularAttribute<Produtos, Long> id;
    public static volatile SingularAttribute<Produtos, String> descricao;
    public static volatile SingularAttribute<Produtos, Double> valorUnitario;

}