/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fullstack.job;

import com.fullstack.dao.DAO;
import com.fullstack.dao.ProdutoDAO;
import com.fullstack.model.Produtos;
import java.util.List;

/**
 *
 * @author Andre
 */
public class ProdutoJob implements JobInterface{
    /**
     * Busca o produto de acordo com o ID informado
     * @author André Farias
     * @since 09/10/2016
     * @param id
     * @return o representando pelo o objeto com.fullstack.model.Produtos
     */
    @Override
    public Produtos getById(Long id) {
        DAO produtoDAO = new ProdutoDAO();
        return (Produtos) produtoDAO.getById(id);
    }
    /**
     * @author André Farias
     * @since 09/10/2016
     * @return os produtos representados por uma lista de com.fullstack.model.Produtos
     */
    public List<Produtos> getProdutos(){
        ProdutoDAO produtoDAO = new ProdutoDAO();
        return produtoDAO.getAll();
    }
}
