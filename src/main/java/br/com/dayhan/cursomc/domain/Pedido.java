package br.com.dayhan.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pedido implements Serializable {
    private static final long serialVersionUID = -5382998819212780618L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date instante;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
    private Pagamento pagamento;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "endereco_de_entrega_id")
    private Endereco enderecoDeEntrega;

    @OneToMany(mappedBy = "id.pedido", cascade = CascadeType.PERSIST)
    private Set<ItemPedido> itens = new HashSet<>();

    public Pedido(final Integer id, final Date instante, final Endereco enderecoDeEntrega) {
        this.id = id;
        this.instante = instante;
        this.enderecoDeEntrega = enderecoDeEntrega;
    }

    public double getValorTotal() {
    	double soma = 0D;
    	for (ItemPedido itemPedido : itens) {
			soma += itemPedido.getSubTotal();
		}
    	return soma;
    }

    public void addItemPedido(final ItemPedido ...itemPedidos) {
    	for (ItemPedido itemPedido : itemPedidos) {
    		itemPedido.getId().setPedido(this);
    		this.itens.add(itemPedido);
		}
    }

}