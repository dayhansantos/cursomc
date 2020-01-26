package br.com.dayhan.cursomc.domain;

import java.util.Date;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date instante;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
    private Pagamento pagamento;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "endereco_de_entrega_id")
    private Endereco enderecoDeEntrega;

    public Pedido() {
    }

    public Pedido(final Integer id, final Date instante, final Cliente cliente, final Endereco enderecoDeEntrega) {
        this.id = id;
        this.instante = instante;
        this.cliente = cliente;
        this.enderecoDeEntrega = enderecoDeEntrega;
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

    @Override
    public boolean equals(final Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Pedido)) {
            return false;
        }
        final Pedido pedido = (Pedido) o;
        return Objects.equals(id, pedido.id) && Objects.equals(instante, pedido.instante)
                && Objects.equals(pagamento, pedido.pagamento) && Objects.equals(cliente, pedido.cliente)
                && Objects.equals(enderecoDeEntrega, pedido.enderecoDeEntrega);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, instante, pagamento, cliente, enderecoDeEntrega);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", instante='" + getInstante() + "'" + ", pagamento='" + getPagamento()
                + "'" + ", cliente='" + getCliente() + "'" + ", enderecoDeEntrega='" + getEnderecoDeEntrega() + "'"
                + "}";
    }

}