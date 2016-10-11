/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fullstack.model;

import com.owlike.genson.annotation.JsonProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author Andre
 */
@Entity
@Table(name = "pedidos")
@XmlRootElement
@Cacheable(false)
public class Pedidos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "data_emissao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataEmissao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_total")
    private Double valorTotal;
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Clientes cliente;
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Status status;
    
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "pedidoId", fetch = FetchType.EAGER)
    private Collection<PedidoProdutos> pedidoProdutosCollection;

    public Pedidos() {
    }

    public Pedidos(Long id) {
        this.id = id;
    }

    public Pedidos(Long id, double valorTotal) {
        this.id = id;
        this.valorTotal = valorTotal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    @JsonProperty("cliente")
    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    @JsonProperty("produtos")
    public Collection<PedidoProdutos> getPedidoProdutosCollection() {
        return pedidoProdutosCollection;
    }

    public void setPedidoProdutosCollection(Collection<PedidoProdutos> pedidoProdutosCollection) {
        this.pedidoProdutosCollection = pedidoProdutosCollection;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedidos)) {
            return false;
        }
        Pedidos other = (Pedidos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fullstack.model.Pedidos[ id=" + id + " ]";
    }


  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }
   
  
}
