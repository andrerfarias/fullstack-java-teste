/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fullstack.service;

import com.fullstack.job.PedidoJob;
import com.fullstack.model.PedidoProdutos;
import com.fullstack.model.Pedidos;
import com.owlike.genson.Genson;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Andre
 */
@Path("/pedido")
public class PedidoService {
    @Context
    private UriInfo context;
    //CONSTANTES DE RETORNO
    private final Response NOT_FOUND = Response.status(Response.Status.NOT_FOUND).entity("").build();
    private final Response BAD_REQUEST = Response.status(Response.Status.BAD_REQUEST).entity("").build();
    private final Response OK = Response.status(Response.Status.OK).entity("").build();
    //CONSTRUTOR
    public PedidoService() {}
    /**
     * Busca todos os pedidos criados
     * @author André Farias
     * @since 08/10/2016
     * @return uma lista de pedidos representados por com.fullstack.model.Pedidos
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPedidos(){
        PedidoJob pedidoJob = new PedidoJob();
        List<Pedidos> pedidos = pedidoJob.getAll();
        return ((pedidos != null && !pedidos.isEmpty()) ? Response.ok(pedidos).build() : NOT_FOUND );
    }
    /**
     * Busca o pedido informado, de acordo com o ID
     * @author André Farias
     * @since 08/10/2016
     * @param id
     * @return o pedido representado por com.fullstack.model.Pedidos
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPedido(@PathParam("id") Long id){
        PedidoJob pedidoJob = new PedidoJob();
        Pedidos pedido = pedidoJob.getById(id);
        if(pedido == null){ return NOT_FOUND; }
        return Response.ok(pedido).build();
    }
    /**
     * Altera o status do pedido para cancelado
     * @param id
     * @return 200 OK se o pedido for atualizado ou 404 NOT FOUND
     */
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePedido(@PathParam("id") Long id){
        PedidoJob pedidoJob = new PedidoJob();
        return ((pedidoJob.cancelarPedido(id)) ? Response.ok().build() : NOT_FOUND );
    }

    /**
     * Cria um novo pedido, espera-se que seja recebido um Payload no seguinte formato:
     * {
     *  "cliente_id" : id do cliente do pedido
     * }
     * @author André Farias
     * @param jsonPayload
     * @since 08/10/2016
     * @return Representação do Pedido criado e status HTTP 201 CREATED
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postPedido(String jsonPayload){
        Genson genson = new Genson();
        HashMap pedidoPayload = genson.deserialize(jsonPayload, HashMap.class);
        //SE NÃO EXISTIR ID DO CLIENTE NO PAYLOAD SERÁ RETORNADO BAD REQUEST
        if(pedidoPayload.get("cliente_id") == null) {return BAD_REQUEST; }
        //REALIZA A INCLUSÃO DO PEDIDO NO BANCO DE DADOS
        PedidoJob pedidoJob = new PedidoJob();
        Pedidos pedido = pedidoJob.criarPedido(Long.parseLong(pedidoPayload.get("cliente_id").toString()));
        //RETORNA CÓDIGO 201 CREATED COM LOCATION PATH INDICANDO O CAMINHO DO RECURSO CRIADO E O JSON DO PEDIDO
        return Response.created(URI.create(context.getPath()+"/"+pedido.getId())).entity(pedido).build();
    }
    
    /**
     * Atualiza um pedido existente, espera-se que seja recebido um Payload no seguinte formato:
     * {
     *  "cliente_id" : id do cliente do pedido
     * }
     * Obs: O valor só será alterado quando um item for adicionado ou removido do pedido.
     * @author André Farias
     * @param id
     * @param jsonPayload
     * @since 08/10/2016
     * @return Representação do Pedido criado e status HTTP 200 OK
     */
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response putPedido(@PathParam("id") Long id, String jsonPayload){
        //VERIFICA SE O PEDIDO INFORMADO EXISTE, SE NÃO EXISTIR RETORNA 404 NOT FOUND
        PedidoJob pedidoJob = new PedidoJob();
        Pedidos pedido = pedidoJob.getById(id);
        //RETORNA 404 NOT FOUND SE NÃO EXISTIR PEDIDO COM O ID INFORMADO
        if(pedido == null){ return NOT_FOUND; }
        //DESERIALIZA O PAYLOAD EM UM HashMap
        Genson genson = new Genson();
        HashMap pedidoPayload = genson.deserialize(jsonPayload, HashMap.class);
        //SE NÃO EXISTIR ID DO CLIENTE OU SATUS NO PAYLOAD SERÁ RETORNADO BAD REQUEST
        if(pedidoPayload.get("cliente_id") == null) {return BAD_REQUEST; }
        //REALIZA A ALTERAÇÃO DO PEDIDO NO BANCO DE DADOS
        pedido = pedidoJob.atualizarPedido(pedido, Long.parseLong(pedidoPayload.get("cliente_id").toString()));
        //RETORNA CÓDIGO 200 OK E O JSON DO PEDIDO
        return Response.ok(pedido).build();
    }
    
    /**
     * Inclui um item ao pedido , espera-se que seja recebido um Payload no seguinte formato:
     * {
     *  "produto_id" : id do produto que deverá ser incluído,
     *  "quantidade" : quantidade do produto que será incluída na compra
     * }
     * O valor unitário e o valor total serão atualizados de acordo com o produto e a quantidade informada
     * @author André Farias
     * @since 08/10/2016
     * @param id
     * @param jsonPayload
     * @return o item o pedido em formato JSON representado pela entidade com.fulllstack.model.PedidoProdutos
     */
    @POST
    @Path("/{id}/produto")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postPedidoProduto(@PathParam("id") Long id, String jsonPayload){
        //VERIFICA SE O PEDIDO INFORMADO EXISTE, SE NÃO EXISTIR RETORNA 404 NOT FOUND
        PedidoJob pedidoJob = new PedidoJob();
        Pedidos pedido = pedidoJob.getById(id);
        //RETORNA 404 NOT FOUND SE NÃO EXISTIR PEDIDO COM O ID INFORMADO
        if(pedido == null){ return NOT_FOUND; }
        //DESERIALIZA O PAYLOAD EM UM HashMap
        Genson genson = new Genson();
        HashMap produtoPayload = genson.deserialize(jsonPayload, HashMap.class);
        //SE NÃO EXISTIR ID DO PRODUTO NO PAYLOAD SERÁ RETORNADO BAD REQUEST
        if(produtoPayload.get("produto_id") == null) {return BAD_REQUEST; }
        //ARMAZENA O ID DO PRODUTO
        Long produtoId = Long.parseLong(produtoPayload.get("produto_id").toString());
        //SE A QUANTIDADE NÃO FOR INFORMADA ASSUME A INCLUSÃO DE SOMENTE 1 PRODUTO
        int quantidade = produtoPayload.get("quantidade") == null ? 1 : Integer.parseInt(produtoPayload.get("quantidade").toString());
        PedidoProdutos produto = pedidoJob.adicionarProduto(pedido, produtoId, quantidade);
        return ((produto != null ) ? Response.created(URI.create(context.getPath()+"/"+produto.getId())).entity(produto).build() : BAD_REQUEST );
    }
    
    /**
     * Remove um produto de um pedido
     * @author André Farias
     * @since 08/10/2016
     * @param pedidoProdutoId
     * @return HTTP STATUS CODE 200 
     */
    @DELETE
    @Path("/produto/{pedidoProdutoId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePedidoProduto(@PathParam("pedidoProdutoId") Long pedidoProdutoId){
        PedidoJob pedidoJob = new PedidoJob();
        Boolean status = pedidoJob.removerProduto(pedidoProdutoId);
        //SE O PRODUTO INFORMADO NÃO EXISTIR RETORNA 404 NOT FOUND
        return ((status) ? OK : NOT_FOUND);
    }
}
