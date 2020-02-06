package br.com.dayhan.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

/**
 * ItemPedido
 */
@Entity
@Data
@NoArgsConstructor
public class ItemPedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
	@JsonIgnore
    private ItemPedidoPK id = new ItemPedidoPK();

    private Double desconto;
    private Integer quantidade;
    private Double preco;

    public ItemPedido(Double desconto, Integer quantidade, Double preco, Produto produto) {
    	this.id.setProduto(produto);
        this.desconto = desconto;
        this.quantidade = quantidade;
        this.preco = preco;
    }
    
    public double getSubTotal() {
    	return (preco - desconto) * quantidade;
    }

	@JsonIgnore
    public Pedido getPedido() {
        return this.id.getPedido();
    }

}