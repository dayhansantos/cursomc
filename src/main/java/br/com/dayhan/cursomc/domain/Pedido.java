package br.com.dayhan.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
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

    public Pedido() {
    }

    public Pedido(final Integer id, final Date instante, final Endereco enderecoDeEntrega) {
        this.id = id;
        this.instante = instante;
        this.enderecoDeEntrega = enderecoDeEntrega;
    }

    public double getValorTotal() {
    	double soma = 0.0;
    	for (ItemPedido itemPedido : itens) {
			soma += itemPedido.getSubTotal();
		}

    	return soma;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public Date getInstante() {
        return this.instante;
    }

    public void setInstante(final Date instante) {
        this.instante = instante;
    }

    public Pagamento getPagamento() {
        return this.pagamento;
    }

    public void setPagamento(final Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(final Cliente cliente) {
        this.cliente = cliente;
    }

    public Endereco getEnderecoDeEntrega() {
        return this.enderecoDeEntrega;
    }

    public void setEnderecoDeEntrega(final Endereco enderecoDeEntrega) {
        this.enderecoDeEntrega = enderecoDeEntrega;
    }

    public Set<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(Set<ItemPedido> itens) {
        this.itens = itens;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Pedido)) {
            return false;
        }
        final Pedido pedido = (Pedido) o;
        return Objects.equals(id, pedido.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", instante='" + getInstante() + "'" + ", pagamento='" + getPagamento()
                + "'" + ", cliente='" + getCliente() + "'" + ", enderecoDeEntrega='" + getEnderecoDeEntrega() + "'"
                + "}";
    }

    public void addItemPedido(final ItemPedido ...itemPedidos) {
    	for (ItemPedido itemPedido : itemPedidos) {
    		itemPedido.getId().setPedido(this);
    		this.itens.add(itemPedido);
		}
    }

}