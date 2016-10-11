/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fullstack.job;

import com.fullstack.dao.ClienteDAO;
import com.fullstack.dao.DAO;
import com.fullstack.model.Clientes;
import java.util.List;

/**
 *
 * @author Andre
 */
public class ClienteJob implements JobInterface {
    public ClienteJob(){}  
    /**
     * Recupera o cliente do banco de dados de acordo com o ID informado
     * @param id
     * @since 08/10/2016
     * @return um cliente representado pela entidade com.fullstack.model.Clientes
     */
    @Override
    public Clientes getById(Long id){
        DAO clienteDAO = new ClienteDAO();
        return (Clientes) clienteDAO.getById(id);
    }
    /**
     * Recupera os clientes que se enquadram nos parâmetros informados 
     * <ul>
     *  <li> documento => o documento do Cliente (CPF ou CNPJ)</li>
     *  <li> email => o e-mail do cliente</li>
     * </ul>
     * @param documento
     * @param email
     * @return uma lista de clientes representados pela entidade com.fullstack.model.Clientes ou <strong>NULL</strong> caso nenhum seja encontrado
     */
    public List<Clientes> getClientes(Long documento, String email){
        ClienteDAO clienteDAO = new ClienteDAO();
        if (clienteDAO instanceof DAO){
            return clienteDAO.getClientes(documento, email);
        }else{
            return null;
        }
    }
}
