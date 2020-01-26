package br.com.dayhan.cursomc.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.dayhan.cursomc.domain.enums.EstadoPagamento;

/**
 * PagamentoComBoleto
 */
@Entity
public class PagamentoComBoleto extends Pagamento {

	@JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataVencimento;

	@JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataPagamento;

    public PagamentoComBoleto() {
    }

    public PagamentoComBoleto(final Integer id, final EstadoPagamento estado, final Pedido pedido,
            final Date dataVencimento, final Date dataPagamento) {
        super(id, estado, pedido);
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
    }

    public Date getDataVencimento() {
        return this.dataVencimento;
    }

    public void setDataVencimento(final Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Date getDataPagamento() {
        return this.dataPagamento;
    }

    public void setDataPagamento(final Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    @Override
    public String toString() {
        return "{" + " dataVencimento='" + getDataVencimento() + "'" + ", dataPagamento='" + getDataPagamento() + "'"
                + "}";
    }

}