package br.com.dayhan.cursomc.domain;

import br.com.dayhan.cursomc.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import java.util.Date;
import java.util.Objects;

/**
 * PagamentoComBoleto
 */
@Entity
public class PagamentoComBoleto extends Pagamento {

    private static final long serialVersionUID = 8820675495157014999L;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PagamentoComBoleto)) return false;
        if (!super.equals(o)) return false;
        PagamentoComBoleto that = (PagamentoComBoleto) o;
        return Objects.equals(dataVencimento, that.dataVencimento) &&
                Objects.equals(dataPagamento, that.dataPagamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), dataVencimento, dataPagamento);
    }
}