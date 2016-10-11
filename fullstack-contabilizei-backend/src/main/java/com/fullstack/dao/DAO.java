package com.fullstack.dao;

import java.util.List;

/**
 * @author Andre
 * @since 08/10/2016
 */
public interface DAO {
    public Object getById(Long id);
    public Object incluir(Object entity);
    public Object atualizar(Object entity);
    
}
