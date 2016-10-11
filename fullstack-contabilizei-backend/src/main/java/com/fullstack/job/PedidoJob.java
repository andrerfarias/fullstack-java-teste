/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fullstack.job;

import com.fullstack.dao.DAO;
import com.fullstack.dao.PedidoDAO;
import com.fullstack.dao.PedidoProdutosDAO;
import com.fullstack.dao.ProdutoDAO;
import com.fullstack.dao.StatusDAO;
import com.fullstack.model.Clientes;
import com.fullstack.model.PedidoProdutos;
import com.fullstack.model.Pedidos;
import com.fullstack.model.Produtos;
import com.fullstack.model.Status;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Andre
 */
public class PedidoJob implements JobInterface{
    public PedidoJob(){}
    /**
     * Recupera o pedido do banco de dados de acordo com o ID informado
     * @param id
     * @since 08/10/2016
     * @return um pedido representado pela entidade com.fullstack.model.Pedidos
     */
    @Override
    public Pedidos getById(Long id) {
        DAO pedidoDAO = new PedidoDAO();
        Pedidos pedido = (Pedidos) pedidoDAO.getById(id);
        pedido.setPedidoProdutosCollection(
                pedido.getPedidoProdutosCollection().stream().filter(produto -> produto.getStatus().equals("ativo")).collect(Collectors.toList())
        );
        return pedido;
    }
    /**
     * Busca uma lista com todos os pedidos criados
     * @author André Farias
     * @since 08/10/2016
     * @return Lista de pedidos
     */
    public List<Pedidos> getAll(){
        PedidoDAO pedidoDAO = new PedidoDAO();
        return pedidoDAO.getAll();
    }
    /**
     * Cria um pedido para o cliente informado, se o cliente não existir a operação será cancelada
     * @author André Farias
     * @param clienteId
     * @return a representação do pedido através do objeto com.fullstack.model.Pedidos
     */
    public Pedidos criarPedido(Long clienteId){
        ClienteJob clienteJob = new ClienteJob();
        //VERIFICA IMPLEMENTAÇÃO DA INTERFACE
        if(clienteJob instanceof JobInterface == false){ return null ; }
        //BUSCA O CLIENTE INFORMADO NO BANCO DE DADOS
        Clientes cliente = (Clientes) clienteJob.getById(clienteId);
        //SE NÃO EXISTIR CLIENTE A OPERAÇÃO SERÁ CANCELADA
        if(cliente == null) { return null; }
        DAO statusDAO = new StatusDAO();
        //CASO EXISTA CLIENTE O PEDIDO SERÁ INCLUÍDO
        DAO pedidoDAO = new PedidoDAO();
        Pedidos pedido = new Pedidos();
        pedido.setCliente(cliente);
        pedido.setDataEmissao(new Date());
        pedido.setValorTotal(0.0);
        pedido.setStatus((Status) statusDAO.getById(1L));
        return (Pedidos) pedidoDAO.incluir(pedido);
    }
    /**
     * Atualiza o pedido 
     * @author André Farias
     * @param pedido
     * @param clienteId
     * @return a representação do pedido atualizado através do objeto com.fullstack.model.Pedidos
     */
    public Pedidos atualizarPedido(Pedidos pedido, Long clienteId){
        ClienteJob clienteJob = new ClienteJob();
        //VERIFICA IMPLEMENTAÇÃO DA INTERFACE
        if(clienteJob instanceof JobInterface == false){ return null ; }
        //BUSCA O CLIENTE INFORMADO NO BANCO DE DADOS
        Clientes cliente = (Clientes) clienteJob.getById(clienteId);
        //SE NÃO EXISTIR CLIENTE A OPERAÇÃO SERÁ CANCELADA
        if(cliente == null) { return null; }
        DAO pedidoDAO = new PedidoDAO();
        pedido.setCliente(cliente);
        //ATUALIZA O PEDIDO E RETORNA O OBJETO ATUALIZADO
        return (Pedidos) pedidoDAO.atualizar(pedido);
    }
     /**
     * Cancela o pedido no banco de dados
     * @param pedidoId
     * @return Status do cancelamento TRUE / FALSE
     */
    public Boolean cancelarPedido(Long pedidoId){
      PedidoDAO pedidoDAO = new PedidoDAO();
      DAO statusDAO = new StatusDAO();
      Pedidos pedido = (Pedidos) pedidoDAO.getById(pedidoId);
      if(pedido == null){ return false; }
      pedido.setStatus((Status) statusDAO.getById(3L));
      return (Pedidos) pedidoDAO.atualizar(pedido) != null;
    }

    /**
     * Busca um produto adicionado ao pedido pelo ID do relacionamento 
     * @author André Farias
     * @since 09/10/2016
     * @param pedidoProdutoId
     * @return o relacionamento entre o pedido e o produto represtando pelo objeto com.fullstack.model.PedidoProdutos
     */
    public PedidoProdutos getPedidoProduto(Long pedidoProdutoId){
       PedidoProdutosDAO pedidoProdutoDAO = new PedidoProdutosDAO();
       //VERIFICA IMPLEMENTAÇÃO DA INTERFACE
       if(pedidoProdutoDAO instanceof DAO == false){ return null ; }
       return pedidoProdutoDAO.getById(pedidoProdutoId);
    }
    /**
     * Inclui um produto ao pedido informado
     * @param pedido
     * @param produtoId
     * @param quantidade
     * @return a representação do produto incluido no pedido através do objeto com.fullstack.model.PedidoProdutos
     */
    public PedidoProdutos adicionarProduto(Pedidos pedido, Long produtoId, int quantidade){
        DAO produtoDao = new ProdutoDAO();
        Produtos produto = (Produtos) produtoDao.getById(produtoId);
        //SE NÃO EXISTIR PRODUTO A OPERAÇÃO SERÁ CANCELADA
        if(produto == null) { return null; }
        DAO statusDAO = new StatusDAO();
        PedidoProdutos pedidoProdutos = new PedidoProdutos();
        pedidoProdutos.setPedidoId(pedido);
        pedidoProdutos.setProdutoId(produto);
        pedidoProdutos.setValorUnitario(produto.getValorUnitario());
        pedidoProdutos.setValorTotal(produto.getValorUnitario() * quantidade);
        pedidoProdutos.setQuantidade(quantidade);
        pedidoProdutos.setDataInclusao(new Date());
        pedidoProdutos.setStatusId((Status) statusDAO.getById(1L));
        PedidoProdutosDAO pedidoProdutosDAO = new PedidoProdutosDAO();
        pedidoProdutos = pedidoProdutosDAO.incluir(pedidoProdutos);
        //ATUALIZA O VALOR DO PEDIDO DE ACORDO COM OS ITENS ADICIONADOS
        this.atualizarValor(pedido.getId());
        return pedidoProdutos;
    }
    /**
     * Remove o produto do pedido alterando seu status para cancelado
     * @author André Farias
     * @since 09/10/2016
     * @param pedidoProdutoId
     * @return Boolean representando o status da alteração.
     */
    public Boolean removerProduto(Long pedidoProdutoId){
        PedidoProdutosDAO pedidoProdutosDAO = new PedidoProdutosDAO();
         //VERIFICA IMPLEMENTAÇÃO DA INTERFACE
        if(pedidoProdutosDAO instanceof DAO == false){ return false ; }
        PedidoProdutos pedidoProduto = pedidoProdutosDAO.getById(pedidoProdutoId);
        //VERIFICA SE O PRODUTO INFORMADO ESTÁ ADICIONADO AO PEDIDO E SE ESTA ATIVO
        if(pedidoProduto == null || !pedidoProduto.getStatus().equals("ativo")){ return false; }
        DAO statusDAO = new StatusDAO();
        pedidoProduto.setStatusId((Status) statusDAO.getById(3L));
        Boolean status = (PedidoProdutos) pedidoProdutosDAO.atualizar(pedidoProduto) != null;
        //ATUALIZA O VALOR DO PEDIDO DE ACORDO COM OS ITENS ADICIONADOS
        this.atualizarValor(pedidoProduto.getPedidoId().getId());
        return status;
    }
    /**
     * @author André Farias
     * @since 08/10/2016
     * @param pedidoId
     * @return o pedido com o valor atualizado
     */
    public Pedidos atualizarValor(Long pedidoId){
        Pedidos pedido = this.getById(pedidoId);
        //CALCULA O VALOR TOTAL DE TODOS OS ITENS ATIVO E ATUALIZA NO PEDIDO
        Double valorTotal = pedido.getPedidoProdutosCollection().stream().filter(produto -> produto.getStatus().equals("ativo")).map(prod -> prod.getValorTotal()).mapToDouble(Double::new).sum();
        //ATUALIZA O PEDIDO E RETORNA O OBJETO ATUALIZADO
        DAO pedidoDAO = new PedidoDAO();
        pedido.setValorTotal(valorTotal);
        //RETORNA O PEDIDO ATUALIZADO
        return (Pedidos) pedidoDAO.atualizar(pedido);
    }
}
