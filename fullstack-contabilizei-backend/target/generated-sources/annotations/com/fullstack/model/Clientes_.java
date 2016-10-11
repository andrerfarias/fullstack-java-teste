package com.fullstack.model;

import com.fullstack.model.Pedidos;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-09T19:59:40")
@StaticMetamodel(Clientes.class)
public class Clientes_ { 

    public static volatile SingularAttribute<Clientes, String> tipoDocumento;
    public static volatile SingularAttribute<Clientes, Integer> foneDdd;
    public static volatile SingularAttribute<Clientes, Long> documento;
    public static volatile SingularAttribute<Clientes, String> nome;
    public static volatile SingularAttribute<Clientes, Long> id;
    public static volatile SingularAttribute<Clientes, String> email;
    public static volatile SingularAttribute<Clientes, Integer> foneNumero;
    public static volatile CollectionAttribute<Clientes, Pedidos> pedidosCollection;

}