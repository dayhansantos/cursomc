package br.com.dayhan.cursomc.domain;

import javax.persistence.Entity;

import br.com.dayhan.cursomc.domain.enums.EstadoPagamento;

/**
 * PagamentoComCartao
 */
@Entity
public class PagamentoComCartao extends Pagamento {

    private Integer numeroDeParcelas;

    public PagamentoComCartao() {
    }

    public PagamentoComCartao(final Integer id, final EstadoPagamento estado, final Pedido pedido,
            final Integer numeroDeParcelas) {
        super(id, estado, pedido);
        this.numeroDeParcelas = numeroDeParcelas;
    }

    public Integer getNumeroDeParcelas() {
        return this.numeroDeParcelas;
    }

    public void setNumeroDeParcelas(final Integer numeroDeParcelas) {
        this.numeroDeParcelas = numeroDeParcelas;
    }

    public PagamentoComCartao numeroDeParcelas(final Integer numeroDeParcelas) {
        this.numeroDeParcelas = numeroDeParcelas;
        return this;
    }

    @Override
    public String toString() {
        return "{" + " numeroDeParcelas='" + getNumeroDeParcelas() + "'" + "}";
    }

}